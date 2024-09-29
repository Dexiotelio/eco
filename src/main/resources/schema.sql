create table "Users" {
    id bigint  primary key generated always as identity,
    firstname text not null,
    lastname text not null,
    username text not null unique,
    age int check (age >= 18),
    email text not null unique,
    phones varchar(20)[] not null,
    password text not null,
    gender text check (gender in ('male', 'female', 'other')),
    role text check (role in ("client", 'admin', visitor)) not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now(),
    constraint check_phones_size CHECK (array_length(phones, 1) <= 2)
};

create index idx_users_username on "Users" using btree (username);
create index idx_users_email on "Users" using btree (email);
create index idx_users_age on "Users" using btree (age);

-- establecer roles
create role client;
create role admin;
create role visitor;

-- otorgar permisos
grant select, insert, update on "Users" to client;
grant select on "Users" to visitor;
grant all privileges on "Users" to admin;

create table Address {
    user_id bigint not null references "Users" (id) on delete cascade,
    street text not null,
    street_number text not null,
    apartment_number text,
    neighborhood text not null,
    city text not null,
    state text not null,
    postal_code text not null,
    country text not null,
    created_at timestamp with zone time now(),
    updated_at timestamp with zone time now(),
    constraint unique_address unique (street, street_number, city, state, postal_code, country)
};

create index idx_address_city on Address using btree (city);
create index idx_address_state on Address using btree (state);
create index idx_address_country on Address using btree (country);