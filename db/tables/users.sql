-- Creación de la tabla "Users"
CREATE TABLE "Users" (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    age INT CHECK (age >= 18 AND age <= 120),
    email VARCHAR(255) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    gender gender_enum NOT NULL,
    role role_enum NOT NULL,
    phones VARCHAR(20)[2],
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT check_email_format CHECK (validate_email_format(email)),
    CONSTRAINT check_firstname_length CHECK (char_length(firstname) >= 2 AND char_length(firstname) <= 100),
    CONSTRAINT check_lastname_length CHECK (char_length(lastname) >= 2 AND char_length(lastname) <= 100),
    CONSTRAINT check_username_format CHECK (username ~ '^[a-zA-Z0-9_]+$'),
    CONSTRAINT unique_user UNIQUE (username, email),
    CONSTRAINT check_phone_format CHECK (
        validate_phone_format(phones[1]) AND
        (array_length(phones, 1) < 2 OR validate_phone_format(phones[2]))
    )
);

CREATE INDEX idx_users_username ON "Users" USING btree (username);
CREATE INDEX idx_users_email ON "Users" USING btree (email);
CREATE INDEX idx_users_age ON "Users" USING btree (age);
