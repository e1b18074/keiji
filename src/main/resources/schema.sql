CREATE TABLE IF NOT EXISTS comment (
    number IDENTITY,
    user CHAR NOT NULL,
    userComment CHAR NOT NULL,
    good INTEGER default 0
);

CREATE TABLE IF NOT EXISTS comment2 (
    number IDENTITY,
    user CHAR NOT NULL,
    userComment CHAR NOT NULL
);
