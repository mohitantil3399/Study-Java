use startingsql;
SELECT name, created_at FROM Users;

-- data quering with constraints
SELECT name, gender FROM Users WHERE gender ='Male';

SELECT name, gender FROM Users WHERE name ='Naman';

SELECT name, gender FROM Users WHERE id<>5;-- not equal to

SELECT name, date_of_birth FROM Users WHERE date_of_birth>'2006-1-1';

SELECT * FROM Users WHERE email IS NULL;

SELECT * FROM Users WHERE date_of_birth BETWEEN '2005-1-1'AND '2006-2-1';

SELECT * FROM Users WHERE gender ='Female' and id >6;
SELECT * FROM Users WHERE gender ='Female' or id >6;

SELECT * FROM Users WHERE gender ='Female' and id >6 order by name ASC;

SELECT * FROM Users WHERE (gender ='Female' or id <6 )order by name DESC;

SELECT * FROM Users WHERE (gender ='Female' or id <6 )order by name DESC limit 3;-- gives a limited number of outputs

ALTER TABLE Users MODIFY  COLUMN id INT FIRST ;

SELECT * FROM Users ORDER BY id ASC ;