CREATE TABLE user(
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  username VARCHAR(128) NOT NULL ,
  password VARCHAR(128) NOT NULL ,
  role VARCHAR(45) NOT NULL ,
  PRIMARY KEY (user_id)
);
CREATE TABLE movie(
  movie_id INTEGER NOT NULL AUTO_INCREMENT,
  title VARCHAR(128),
  category VARCHAR(128),
  year INTEGER,
  description TEXT,
  image TEXT,
  PRIMARY KEY (movie_id)
);
-- CREATE TABLE movie_rating(
--   rating_id INTEGER NOT NULL AUTO_INCREMENT,
--   rating DOUBLE,
--   movie_id INTEGER,
--   user_id INTEGER,
--   PRIMARY KEY (rating_id),
--   FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
--   FOREIGN KEY (user_id) REFERENCES user(user_id)
-- );
