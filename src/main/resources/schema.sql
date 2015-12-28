DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Messages;

CREATE TABLE Accounts (
  id IDENTITY,
  username VARCHAR(30) UNIQUE NOT NULL,
  password VARCHAR(30) NOT NULL,
  email VARCHAR(30) UNIQUE NOT NULL
  --firstName, lastName, dateOfBirth, address?, country?

);

CREATE TABLE Messages (
  id IDENTITY,
  text VARCHAR(140) NOT NULL,
  create_date TIMESTAMP NOT NULL
);

CREATE TABLE Series (
  s_id IDENTITY,
  title VARCHAR2(100),
  is_finished NUMBER(1),--0/1 or BOOLEAN ??
  next_episode
);

CREATE TABLE Episodes (
  e_id IDENTITY,
  title VARCHAR2(100),
  series_id INT NOT NULL,
  FOREIGN KEY (series_id) REFERENCES Series (s_id)
  --e_id INT NOT NULL, PRIMARY KEY (e_id), --MySQL
  --FOREIGN KEY (series_id) REFERENCES Series (s_id) --MySQL

  --e_id INT NOT NULL PRIMARY KEY, --SQL server/Oracle/MS Access
  --series_id INT FOREIGN KEY REFERENCES Series(s_id) --SQL server/Oracle/MS Access
)

-- insert into Users (username, password, first_name, last_name, email)
-- values ('user01', 'pass01', 'Dmytro', 'Krasota', 'dkras@gmail.com');
-- commit;