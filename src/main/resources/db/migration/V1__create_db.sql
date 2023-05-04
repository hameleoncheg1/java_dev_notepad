CREATE TABLE users (
                         USER_ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         USERNAME VARCHAR(255) NOT NULL,
                         PASSWORD VARCHAR(255) NOT NULL,
                         ROLE VARCHAR(255) NOT NULL,
                         ENABLED INT DEFAULT NULL,
                         EMAIL VARCHAR(255) NOT NULL unique
);

CREATE TABLE note(
                     ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                     title VARCHAR (255),
                     content VARCHAR (2048),
                     access VARCHAR (2048) CHECK (access IN ('private', 'public')),
                     email VARCHAR(255) NOT NULL,
                     FOREIGN KEY (email) REFERENCES users (email) ON DELETE CASCADE
);