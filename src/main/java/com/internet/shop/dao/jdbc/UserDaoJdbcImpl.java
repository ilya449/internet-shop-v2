package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.UserDao;
import com.internet.shop.exception.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String query = "SELECT * FROM users WHERE deleted = FALSE AND login = ?";
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract user with login: " + login, e);
        }
        return getUserWithRoles(user);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users (name, login, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add user " + user, e);
        }
        addUserRoles(user.getId(), user.getRoles());
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        String query = "SELECT * FROM users WHERE deleted = FALSE AND id = ?";
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract user with id: " + id, e);
        }
        return getUserWithRoles(user);
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users WHERE deleted = FALSE";
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = extractUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract users from DB", e);
        }
        for (User user : users) {
            user.setRoles(getUserRoles(user.getId()));
        }
        return users;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, login = ?, password = ? WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update user: " + user, e);
        }
        deleteUserRoles(user.getId());
        addUserRoles(user.getId(), user.getRoles());
        return user;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET deleted = TRUE WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user with id: " + id, e);
        }
    }

    private boolean deleteUserRoles(Long id) {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete roles for user with ID: " + id, e);
        }
    }

    private Optional<User> getUserWithRoles(User user) {
        if (user == null) {
            return Optional.empty();
        }
        user.setRoles(getUserRoles(user.getId()));
        return Optional.of(user);
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("user_id"), resultSet.getString("name"),
                resultSet.getString("login"), resultSet.getString("password"), new HashSet<>());
    }

    private void addUserRoles(Long id, Set<Role> roles) {
        String query = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, (SELECT roles.role_id FROM roles WHERE role_name = ?));";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            for (Role role : roles) {
                statement.setLong(1, id);
                statement.setString(2, role.getRoleName().name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update role(s) for user with ID: " + id, e);
        }
    }

    private Set<Role> getUserRoles(Long id) {
        String query = "SELECT roles.role_id, role_name FROM roles JOIN users_roles "
                + "ON users_roles.role_id = roles.role_id where user.user_id = ?";
        Set<Role> roleSet = new HashSet<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                roleSet.add(role);
            }
            return roleSet;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't extract roles for user with id: " + id, e);
        }
    }
}
