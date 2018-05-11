DROP TABLE IF EXISTS users
CREATE TABLE users (id int(11) NOT NULL AUTO_INCREMENT,name varchar(255) DEFAULT NULL,surname varchar(255) DEFAULT NULL,birthdate date DEFAULT NULL,  PRIMARY KEY (`id`))
DROP TABLE IF EXISTS friendships
CREATE TABLE friendships (userid1 int(11) NOT NULL,userid2 int(11) NOT NULL,timestamp timestamp DEFAULT CURRENT_TIMESTAMP)