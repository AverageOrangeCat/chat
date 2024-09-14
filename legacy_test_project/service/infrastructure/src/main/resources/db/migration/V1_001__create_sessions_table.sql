CREATE TABLE sessions (
    "session_id" SERIAL PRIMARY KEY, 
    "account_id" INT NOT NULL,
    "access_token" VARCHAR(256) NOT NULL,
    "expiration_date" TIMESTAMP NOT NULL,
    CONSTRAINT fk_account
        FOREIGN KEY("account_id") 
            REFERENCES accounts("account_id")
            ON DELETE CASCADE
);
