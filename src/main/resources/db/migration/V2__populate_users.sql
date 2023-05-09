INSERT INTO USERS (EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('syrovatkaaroslav@gmail.com',
        '{bcrypt}$2a$04$hRkj6yZS.DpUMKShtf93nedoiZhGHvKACwJ8AswlyHQ9ePV8DziwG',
-- '{noop}user',
'ROLE_USER', 1);

INSERT INTO USERS (EMAIL, PASSWORD, ROLE, ENABLED)
VALUES ('hameleoncheg@gmail.com',
'{noop}admin',
'ROLE_ADMIN', 1);