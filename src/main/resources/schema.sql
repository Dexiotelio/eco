CREATE TABLE "Users" (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    firstname TEXT NOT NULL,
    lastname TEXT NOT NULL,
    username TEXT NOT NULL UNIQUE,
    age INT CHECK (age >= 18),
    email TEXT NOT NULL UNIQUE,
    phones VARCHAR(20)[] NOT NULL,
    password TEXT NOT NULL,
    gender TEXT CHECK (gender IN ('male', 'female', 'other')),
    role TEXT CHECK (role IN ('client', 'admin', 'visitor')) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT check_phones_size CHECK (array_length(phones, 1) <= 2)
);

CREATE INDEX idx_users_username ON "Users" USING btree (username);
CREATE INDEX idx_users_email ON "Users" USING btree (email);
CREATE INDEX idx_users_age ON "Users" USING btree (age);

-- Establecer roles
CREATE ROLE client;
CREATE ROLE admin;
CREATE ROLE visitor;

-- Otorgar permisos
GRANT SELECT, INSERT, UPDATE ON "Users" TO client;
GRANT SELECT ON "Users" TO visitor;
GRANT ALL PRIVILEGES ON "Users" TO admin;

CREATE TABLE "Address" (
    user_id BIGINT NOT NULL REFERENCES "Users" (id) ON DELETE CASCADE,
    street TEXT NOT NULL,
    street_number TEXT NOT NULL,
    apartment_number TEXT,
    neighborhood TEXT NOT NULL,
    city TEXT NOT NULL,
    state TEXT NOT NULL,
    postal_code TEXT NOT NULL,
    country TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT unique_address UNIQUE (street, street_number, city, state, postal_code, country)
);

CREATE INDEX idx_address_city ON "Address" USING btree (city);
CREATE INDEX idx_address_state ON "Address" USING btree (state);
CREATE INDEX idx_address_country ON "Address" USING btree (country);
CREATE INDEX idx_address ON "Address" USING btree (street, street_number, neighborhood, city, state, postal_code, country);

