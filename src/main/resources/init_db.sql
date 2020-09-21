CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NOT NULL DEFAULT 0,
  `deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

INSERT INTO `products` (`name`, `price`)
VALUES ('DJI Phantom 4 PRO', '49470.00'),
        ('DJI Mavic 2 PR', '50720.00'),
        ('DJI Mavic PRO Platinum', '42710.00'),
        ('DJI Inspire 2', '142760.00');
