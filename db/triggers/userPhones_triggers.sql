CREATE TRIGGER check_phone_format
BEFORE INSERT OR UPDATE ON userPhones
FOR EACH ROW
EXECUTE FUNCTION validation_phone_format_trigger();

CREATE TRIGGER check_phone_limit
BEFORE INSERT ON userPhones
FOR EACH ROW
EXECUTE FUNCTION validate_phone_limit();
