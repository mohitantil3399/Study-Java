CREATE DATABASE IF NOT EXISTS startingsql;
USE startingsql;
CREATE TABLE IF NOT EXISTS Users(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(30) NOT NULL ,
    email VARCHAR(50) UNIQUE,
    gender ENUM('Male','Female','Others') Not Null,
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM Users;#selects all the cols , can say :SELECT email, name , etc. FROM table_name
-- RENAME TABLE Users TO learners;

-- To add more columns :
ALTER TABLE Users ADD COLUMN is_active BOOLEAN DEFAULT true;
-- can even delete this
ALTER TABLE Users DROP COLUMN is_active;
-- MODIFY A COLUMN TYPE using modify , name of constraint and then new data type
ALTER TABLE Users MODIFY COLUMN name VARCHAR(50);

-- to change the order of the columns
    ALTER TABLE Users MODIFY COLUMN name VARCHAR(50) FIRST ;-- This moves the name column to the first
    ALTER TABLE Users MODIFY COLUMN email VARCHAR(50) AFTER date_of_birth ;

-- to delete use : DROP DATABASE query followed by name
INSERT INTO Users (id, name, email, gender, date_of_birth, created_at) VALUES(DEFAULT,'Hello','hello321@gmail.com','Male','2006-01-25',DEFAULT);
INSERT INTO Users (id, name, email, gender, date_of_birth, created_at) VALUES(DEFAULT,'Hina','hina321@gmail.com','Female','2008-11-02',DEFAULT);
INSERT INTO Users (id, name, email, gender, date_of_birth, created_at) VALUES(DEFAULT,'Baba','baba#21@gmail.com','Male','2006-01-25',DEFAULT);
INSERT INTO Users (id, name, email, gender, date_of_birth, created_at) VALUES(DEFAULT,'Bhai','bhaiya#21@gmail.com','Male','2009-01-05',DEFAULT);
INSERT INTO Users (id, name, email, gender, date_of_birth, created_at) VALUES(DEFAULT,'Babu Rav','babu#21@gmail.com','Male','2004-08-25',DEFAULT);

SELECT * FROM Users;
-- multi line insertion using single query
INSERT INTO Users(id,name,gender,created_at)VALUES (DEFAULT,'Naman','Male',DEFAULT),
                                                   (DEFAULT,'Megha','Female',DEFAULT),
                                                   (DEFAULT,'Monika','Female',DEFAULT),
                                                   (DEFAULT,'Manu','Female',DEFAULT),
                                                   (DEFAULT,'Matter','Male',DEFAULT)

