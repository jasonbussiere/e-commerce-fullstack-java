use `ecommerce-assassins_db`;

drop table if exists cart_items;
drop table if exists cart;
drop table if exists videogames;
drop table if exists users;

alter database `ecommerce-assassins_db`
    character set utf8mb4
    collate utf8mb4_unicode_ci;

create table videogames
(
    gameId char(36) primary key ,
    title varchar(255) unique not null,
    genre varchar(255),
    platform varchar(255),
    releaseDate date,
    description text,
    unitPrice decimal(10,2),
    imageURL varchar(255),
    wikiUrl varchar(255)
);

create table users
(
    userId       char(36) primary key ,
    firstName     varchar(255) not null ,
    lastName     varchar(255) not null ,
    email        varchar(255) unique not null,
    passwordHash varchar(255) not null
);

create table cart
(
    cartId char(36) primary key ,
    userId char(36) unique not null ,
    totalPrice decimal(10, 2),

    constraint fk_cart_userId
        foreign key (userId)
            references users(userId)
            on delete cascade
);

create table cart_items
(
    cartItemId char(36) primary key ,
    cartId char(36) not null,
    gameId char(36) not null,
    platform varchar(255) not null,
    total decimal(10, 2),

    constraint uu_cart_items
        unique(cartId, gameId),

    constraint fk_cart__items_gameId
        foreign key (gameId)
            references videogames(gameId),

    constraint fk_cart__items_cartId
        foreign key (cartId)
            references cart(cartId)
            on delete cascade
);
