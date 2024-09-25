CREATE TABLE credentials (
    "credential_id" SERIAL PRIMARY KEY, 
    "usertag" VARCHAR (64) UNIQUE NOT NULL, 
    "username" VARCHAR (64) NOT NULL, 
    "password" VARCHAR (256) NOT NULL
);
