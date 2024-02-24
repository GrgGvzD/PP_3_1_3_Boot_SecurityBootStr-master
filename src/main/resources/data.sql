drop table if exists role, user, user_roles;

create table if not exists user (id bigint not null primary key auto_increment,
    first_name varchar(255) not null, last_name varchar(255) not null,
    login varchar(255) not null, password varchar(255) not null);

create table if not exists role (id int not null primary key auto_increment, role varchar(255) not null);

create table if not exists user_roles (user_id bigint, foreign key (user_id) references user(id),
    role_id int, foreign key (user_id) references user(id));

insert into user (first_name, last_name,  login, password)
values ('Anastasiya', 'Boikova', 'anastas', '$2y$10$T9z..wVB6q0woNLIfTO.R.8FRHa4ZXfauhDbS6K62KBRMcdO6N9tW');
insert into user (first_name, last_name,  login, password)
values ('Vladislav', 'Petrov', 'vlad', '$2y$10$dSvnZ6FfWMcqwCJffk1.3uqB1EIeIoL3J.NPtGamKxfOAFwcwmTvq');
insert into user (first_name, last_name,  login, password)
values ('Stanislav', 'Smirnov', 'stas', '$2y$10$43fr6piVHAG20PzaYWvIzeOhhAP7VkT015lCyA91Ax0Yfd6j1vL7W');
insert into user (first_name, last_name,  login, password)
values ('Veronika', 'Nikishina',  'vernik', '$2y$10$6L9/zWnaP2obgJ5SUjGIOuPzViIOGq/0TcCRhwvuBty4QX2PycgZG');
insert into role (role) values ('ADMIN'), ('USER');
insert into user_roles set user_id=(select id from user where login = 'anastas'),
    role_id = (select id from role where role = 'ADMIN');
insert into user_roles set user_id=(select id from user where login = 'vernik'),
    role_id = (select id from role where role = 'USER');
insert into user_roles set user_id=(select id from user where login = 'vlad'),
    role_id = (select id from role where role = 'ADMIN');
insert into user_roles set user_id=(select id from user where login = 'stas'),
    role_id = (select id from role where role = 'USER');
