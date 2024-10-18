-- Función para validar el formato de teléfono
CREATE OR REPLACE FUNCTION validate_phone_format(phone TEXT)
RETURNS BOOLEAN AS $$
BEGIN
    RETURN phone ~ '^[+]?(\d{1,4}[- ]?)?(\(?\d{1,3}?\)?[- ]?)?\d{3,4}[- ]?\d{4}$';
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_phones()
RETURNS TRIGGER AS $$
DECLARE
    phone TEXT;
BEGIN
    FOREACH phone IN ARRAY NEW.phones LOOP
        IF NOT validate_phone_format(phone) THEN
            RAISE EXCEPTION 'Invalid phone format for phone number: %', phone;
        END IF;
    END LOOP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_phone_limit()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM userPhones WHERE user_id = NEW.user_id) >= 2 THEN
        RAISE EXCEPTION 'A user can have a maximum of two phone numbers.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
