-- enums
CREATE TYPE gender_enum as ENUM ('male', 'female', 'other');
CREATE TYPE role_enum as ENUM ('client', 'admin', 'visitor');

CREATE TABLE "Users" (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    age INT CHECK (age >= 18 AND age <= 120),
    email VARCHAR(255) NOT NULL UNIQUE,
    phones VARCHAR(20)[] NOT NULL,
    password TEXT NOT NULL,
    gender gender_enum NOT NULL,
    role role_enum NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT check_phones_size CHECK (array_length(phones, 1) <= 2)
    CONSTRAINT check_email_format CHECK (validate_email_format(email)),
    CONSTRAINT check_phone_format CHECK (array_length(phones)
    AND every(validate_phone_format(phone) FOR phone IN phones))
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
    street VARCHAR(255) NOT NULL,
    street_number VARCHAR(20) NOT NULL,
    apartment_number VARCHAR(10),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT unique_address UNIQUE (street, street_number, city, state, postal_code, country)
);

CREATE INDEX idx_address_city ON "Address" USING btree (city);
CREATE INDEX idx_address_state ON "Address" USING btree (state);
CREATE INDEX idx_address_country ON "Address" USING btree (country);
CREATE INDEX idx_address ON "Address" USING btree (street, street_number, neighborhood, city, state, postal_code, country);

CREATE TABLE User_session (
    session_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL REFERENCES "Users" (id) ON DELETE CASCADE,
    token VARCHAR(512) NOT NULL,
    refresh_token VARCHAR(512) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    ip_address INET,
    user_agent VARCHAR(520),
    device_type TEXT CHECK (device_type IN ('mobile', 'desktop', 'table', 'other'))
    expiration TIMESTAMP WITH TIME ZONE DEFAULT NOW() + INTERVAL '1 hour',
    created_by BIGINT REFERENCES "Users" (id),
    created_at TIMESTAMP WITH ZONE DEFAULT NOW(),
    last_accessed TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    two_factor_enabled BOOLEAN DEFAULT FALSE,
    two_factor_code VARCHAR(10),
    two_factor_expires TIMESTAMP WITH TIME ZONE,
    CONSTRAINT unique_session UNIQUE (user_id, token)
)

CREATE INDEX idx_user_id ON User_session USING btree (user_id);
CREATE INDEX idx_token ON User_session USING btree (token);
CREATE INDEX idx_expiration ON User_session USING btree (expiration);
CREATE INDEX idx_last_accessed ON User_session USING btree (last_accessed);
CREATE INDEX idx_user_last_accessed ON User_session USING btree (user_id, last_accessed);

-- triggers function for timestamp
CREATE OR REPLACE FUNCTION update_timestamp_fnc()
RETURN TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_users_update_at
BEFORE UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();

CREATE TRIGGER update_timestamp_fnc
BEFORE UPDATE ON "Address"
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();

-- Función para validar formato de email
CREATE OR REPLACE FUNCTION validate_email_format(email TEXT)
RETURNS BOOLEAN AS $$
BEGIN
    RETURN email ~ '^[a-z0-9!#$%&''*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&''*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$';
END;
$$ LANGUAGE plpgsql;

-- Función para validar email en trigger
CREATE OR REPLACE FUNCTION validar_email()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT validate_email_format(NEW.email) THEN
        RAISE EXCEPTION 'Invalid email format.';
    END IF;

    IF EXISTS (SELECT 1 FROM "Users" WHERE email = NEW.email) THEN
        RAISE EXCEPTION 'Email already in use.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_email
BEFORE INSERT OR UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION validar_email();
