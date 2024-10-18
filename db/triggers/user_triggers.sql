-- Trigger para validar el email antes de insertar o actualizar
CREATE TRIGGER check_email
BEFORE INSERT OR UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION validate_email();

-- Trigger para validar teléfonos antes de insertar o actualizar
CREATE TRIGGER check_phone_format
BEFORE INSERT OR UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION validate_phones();

-- Trigger para limitar la cantidad de números de teléfono
CREATE TRIGGER check_phone_limit
BEFORE INSERT OR UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION validate_phone_limit();

-- Trigger para actualizar el timestamp en "Users"
CREATE TRIGGER update_users_update_at
BEFORE UPDATE ON "Users"
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();