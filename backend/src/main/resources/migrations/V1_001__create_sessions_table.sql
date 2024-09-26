CREATE TABLE sessions (
    "session_id" SERIAL PRIMARY KEY, 
    "bear_token" BYTEA UNIQUE NOT NULL, 
    "usertag" VARCHAR (64) NOT NULL
);
