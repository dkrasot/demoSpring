DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Tweets;

CREATE TABLE Accounts (
  id IDENTITY,
  username VARCHAR(30) UNIQUE NOT NULL,
  password VARCHAR(30) NOT NULL,
  email VARCHAR(30) UNIQUE NOT NULL
  --firstName, lastName, dateOfBirth, address?, country?

);

CREATE TABLE Tweets (
  id IDENTITY,
  text VARCHAR(140) NOT NULL,
  creationDate TIMESTAMP NOT NULL,
  id_user INT NOT NULL,
  FOREIGN KEY (id_user) REFERENCES Accounts(id)
);

INSERT INTO Accounts (username, password, email) VALUES ('user','demo','user@demo.com');
INSERT INTO Accounts (username, password, email) VALUES ('admin','demo','admin@demo.com');
INSERT INTO Accounts (username, password, email) VALUES ('useradmin','demo','useradmin@demo.com');
INSERT INTO Tweets (text, creationDate, id_user) VALUES ('text_msg_1','2015-12-31 11:00:00',1);
INSERT INTO Tweets (text, creationDate, id_user) VALUES ('text_msg_2','2015-12-31 12:00:00',2);
INSERT INTO Tweets (text, creationDate, id_user) VALUES ('text_msg_3','2015-12-31 13:00:00',3);
COMMIT;
-- insert into Users (username, password, first_name, last_name, email)
-- values ('user01', 'pass01', 'Dmytro', 'Krasota', 'dkras@gmail.com');
-- commit;

-- CREATE TABLE Series (
--   s_id IDENTITY,
--   title VARCHAR2(100),
--   is_finished NUMBER(1),--0/1 or BOOLEAN ??
--   next_episode INT
-- );
--
-- CREATE TABLE Episodes (
--   e_id IDENTITY,
--   title VARCHAR2(100),
--   series_id INT NOT NULL,
--   FOREIGN KEY (series_id) REFERENCES Series (s_id)
-- )
  --e_id INT NOT NULL, PRIMARY KEY (e_id), --MySQL
  --FOREIGN KEY (series_id) REFERENCES Series (s_id) --MySQL

  --e_id INT NOT NULL PRIMARY KEY, --SQL server/Oracle/MS Access
  --series_id INT FOREIGN KEY REFERENCES Series(s_id) --SQL server/Oracle/MS Access


-- insert into Users (username, password, first_name, last_name, email)
-- values ('user01', 'pass01', 'Dmytro', 'Krasota', 'dkras@gmail.com');
-- commit;