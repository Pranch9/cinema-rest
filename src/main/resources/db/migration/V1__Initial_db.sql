create table if not exists cinemas
(
    id          uuid not null primary key,
    cinema_name varchar(255)
);

create table if not exists cinema_halls
(
    id           uuid not null primary key,
    hall_name    varchar(255),
    rows_num     integer,
    place_number integer,
    cinema_id    uuid not null references cinemas (id),
    constraint cinema_halls_cinema_id_key unique (cinema_id)
);

create table if not exists movies
(
    id          uuid not null primary key,
    title       varchar(255),
    movie_genre varchar(255)
);

create table if not exists users
(
    id            uuid         not null primary key,
    username      varchar(255) not null,
    mail          varchar(255),
    password      varchar(255) not null,
    creation_date date         not null
);

create table if not exists seats
(
    id             uuid    not null primary key,
    row_num        integer not null,
    place          integer not null,
    cinema_hall_id uuid    not null references cinema_halls (id),
    constraint seats_cinema_hall_id_key unique (cinema_hall_id)
);

create table if not exists sessions
(
    id           uuid not null primary key,
    movie_id     uuid not null references movies (id),
    constraint sessions_movie_id_key unique (movie_id),
    cinema_id    uuid not null references cinemas (id),
    constraint sessions_cinema_id_key unique (cinema_id),
    watchingDate date
);

create table if not exists tickets
(
    id         uuid not null primary key,
    session_id uuid not null references sessions (id),
    constraint tickets_session_id_key unique (session_id),
    seat_id    uuid not null references seats (id),
    constraint tickets_seat_id_key unique (seat_id),
    user_id    uuid not null references users (id),
    constraint tickets_user_id_key unique (user_id),
    status     varchar(255),
    price      decimal
);