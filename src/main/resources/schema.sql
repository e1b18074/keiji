CREATE TABLE IF NOT EXISTS thread (
    threadNumber INT AUTO_INCREMENT PRIMARY KEY,
    user CHAR NOT NULL,
    title CHAR NOT NULL,
    date CHAR NOT NULL,
    good INT default 0
);

CREATE TABLE IF NOT EXISTS comment (
    number INT AUTO_INCREMENT PRIMARY KEY,
    user CHAR NOT NULL,
    userComment CHAR NOT NULL,
    date CHAR NOT NULL,
    threadNumber INT NOT NULL,
    good INT default 0
);

CREATE TABLE IF NOT EXISTS userInfo (
  number INT AUTO_INCREMENT,
  name CHAR NOT NULL PRIMARY KEY,
  password CHAR NOT NULL,
  role CHAR DEFAULT 'ROLE_USER',
  date CHAR,
  enabled boolean DEFAULT true
);
