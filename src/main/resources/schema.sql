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

CREATE TABLE IF NOT EXISTS userInfo (
  number IDENTITY,
  id CHAR NOT NULL,
  name CHAR NOT NULL,
  password CHAR NOT NULL,
  role CHAR DEFAULT 'ROLE_USER',
  enabled boolean DEFAULT true
);
