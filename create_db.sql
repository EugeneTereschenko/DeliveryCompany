DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS admin_users;


DROP TABLE IF EXISTS ratedeliver;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS carts;

create table if not exists users (
                                     id integer not null auto_increment,
                                     email varchar(20),
                                     encrypted_password varchar(20),
                                     reset_password_token varchar(20),
                                     reset_password_sent_at datetime,
                                     remember_created_at datetime,
                                     sign_in_count int,
                                     current_sign_in_at datetime,
                                     name varchar(20),
                                     uid varchar(20),
                                     image text,
                                     primary key (id)
);

create table if not exists admin_users (
                                           id integer not null auto_increment,
                                           email varchar(300),
                                           encrypted_password varchar(200),
                                           reset_password_token varchar(200),
                                           reset_password_sent_at datetime,
                                           remember_created_at datetime,
                                           created_at datetime,
                                           update_at datetime,
                                           primary key (id),
                                           unique (
                                                   email
                                               )
);


create table if not exists ratedeliver (
                                     id integer not null auto_increment,
                                     weight DOUBLE,
                                     distancefrom integer,
                                     distanceto integer,
                                     cost double,
                                     created_at datetime,
                                     update_at datetime,
                                     primary key (id)
);



create table if not exists cards (
                                     card_number varchar(200),
                                     name varchar(200),
                                     image varchar(200),
                                     cvv integer,
                                     expiration_month_year varchar(200),
                                     user_id int,
                                     created_at datetime,
                                     update_at datetime,
                                     foreign key (user_id)
                                         references users (id) on delete cascade
);



create table if not exists addresses (
                                         id integer not null auto_increment,
                                         address_type varchar(300),
                                         first_name varchar(300),
                                         last_name varchar(300),
                                         address varchar(300),
                                         city varchar(300),
                                         zip int,
                                         country varchar(300),
                                         phone varchar(300),
                                         user_id int,
                                         checkbox_id int,
                                         delivery_id int,
                                         created_at datetime,
                                         update_at datetime,
                                         primary key (id),
                                         foreign key (user_id)
                                             references users (id) on delete cascade,
                                         unique (
                                                 phone
                                             )
);

create table if not exists carts (
                                     id integer not null auto_increment,
                                     total_price int,
                                     created_at datetime,
                                     update_at datetime,
                                     user_id int,
                                     shipping_price int,
                                     coupon int,
                                     checkout_step varchar(200),
                                     cityFrom varchar(200),
                                     cityTo varchar(200),
                                     typeDeliver varchar(200),
                                     count int,
                                     weight int,
                                     length int,
                                     width int,
                                     height int,
                                     primary key (id),
                                     foreign key (user_id)
                                         references users (id) on delete cascade
);