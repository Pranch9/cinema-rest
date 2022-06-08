create table if not exists addresses
(
    id           uuid not null primary key,
    street       varchar(255),
    house_number varchar(255),
    city         varchar(255),
    zip_code      integer
);


create table if not exists cinemas
(
    id          uuid not null primary key,
    address_id  uuid not null references addresses (id),
    constraint cinemas_address_id_key unique (address_id),
    cinema_name varchar(255) unique
);

create table if not exists cinema_halls
(
    id           uuid not null primary key,
    hall_name    varchar(255),
    rows_number  integer,
    place_number integer,
    cinema_id    uuid not null,
    constraint cinema_halls_cinema_id_fkey foreign key (cinema_id) references cinemas (id)
);

create table if not exists movies
(
    id          uuid not null primary key,
    title       varchar(255) unique,
    movie_genre varchar(255)
);

create table if not exists users
(
    id            uuid         not null primary key,
    username      varchar(255) not null unique,
    mail          varchar(255) not null unique,
    password      varchar(255) not null,
    creation_date date         not null
);

create table if not exists seats
(
    id             uuid    not null primary key,
    row_number     integer not null,
    place          integer not null,
    cinema_hall_id uuid    not null,
    constraint seats_cinema_hall_id_fkey foreign key (cinema_hall_id) references cinema_halls (id)
);

create table if not exists sessions
(
    id             uuid not null primary key,
    movie_id       uuid not null,
    constraint sessions_movie_id_fkey foreign key (movie_id) references movies (id),
    cinema_hall_id uuid not null,
    constraint sessions_cinema_hall_id_fkey foreign key (cinema_hall_id) references cinema_halls (id),
    session_date   date
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