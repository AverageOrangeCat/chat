CREATE TABLE credentials (
    "credential_id" SERIAL PRIMARY KEY, 
    "usertag" VARCHAR (64) UNIQUE NOT NULL, 
    "username" VARCHAR (64) NOT NULL, 
    "password_salt" VARCHAR (16) NOT NULL,
    "password_hash" VARCHAR (64) NOT NULL
);
