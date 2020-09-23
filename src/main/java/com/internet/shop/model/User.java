package com.internet.shop.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private Set<Role> roles;
}
