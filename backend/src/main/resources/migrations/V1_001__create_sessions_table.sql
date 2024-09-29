CREATE TABLE sessions (
    "session_id" SERIAL PRIMARY KEY, 
    "bear_token" VARCHAR (64) UNIQUE NOT NULL, 
    "usertag" VARCHAR (64) NOT NULL
);
