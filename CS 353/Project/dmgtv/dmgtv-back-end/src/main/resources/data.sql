CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TABLE IF EXISTS movie CASCADE ;
DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS friend;
DROP TABLE IF EXISTS buy;
DROP TABLE IF EXISTS rent;
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS wish;
CREATE TABLE movie(
  id UUID PRIMARY KEY,
  title VARCHAR(64) NOT NULL,
  production_Year INTEGER NOT NULL,
  rating NUMERIC(2,1) NOT NULL,
  price_per_month INTEGER NOT NULL,
  price_to_buy INTEGER NOT NULL,
  age_restricted BOOLEAN NOT NULL,
  imdb_rating NUMERIC(2,1) NOT NULL,
  like_count INTEGER
);

CREATE TABLE users(
    id UUID PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(16) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL
);

CREATE TABLE review(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    movie_id UUID NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    rating NUMERIC(2,1) NOT NULL,
    comment TEXT,
    UNIQUE (user_id, movie_id)
);

CREATE TABLE request(
    id UUID PRIMARY KEY,
    movie_name VARCHAR(64) NOT NULL,
    production_year NUMERIC(4,0) NOT NULL
);

CREATE TABLE rent(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    movie_id UUID NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    start_date DATE NOT NULL,
    end_date DATE,
    UNIQUE (user_id, movie_id, start_date)
);

CREATE TABLE buy(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    movie_id UUID NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    purchase_date DATE NOT NULL,
    UNIQUE (user_id, movie_id)
);

CREATE TABLE friend(
    id UUID PRIMARY KEY,
    first_username VARCHAR(20) NOT NULL REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE,
    second_username VARCHAR(20) NOT NULL REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (first_username, second_username)
);

CREATE TABLE wish(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    movie_id UUID NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    wish_date DATE NOT NULL,
    UNIQUE (user_id, movie_id)
);


-- Default data
--users
INSERT INTO users VALUES (uuid_generate_v4(), 'mertbarkiner', 'password1', 'Mert Barkın Er', '2001-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'gokberkbeydemir', 'password2', 'Gökberk Beydemir', '2001-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'dorukkantarci', 'password3', 'Doruk Kantarcı', '2002-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'melisatun', 'password4', 'Melis Atun', '2000-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'berkeucar', 'password5', 'Berke Uçar', '2002-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'efeerturk', 'password6', 'Efe Erturk', '1997-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'emirmeliherdem', 'password7', 'Emir Melih Erdem', '2022-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'efebeydogan', 'password8', 'Efe Beydogan', '2001-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'ardaonal', 'password9', 'Arda Onal', '2001-01-01');
INSERT INTO users VALUES (uuid_generate_v4(), 'erenpolat', 'password10', 'Eren Polat', '2001-01-01');

--movies
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Shawshank Redemption', 1994, 4, 10, 10, false, 9.3, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Godfather', 1972, 5, 10, 10, false, 9.2, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Godfather: Part II', 1974, 0, 10, 10, false, 9.0, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Dark Knight', 2008, 0, 10, 10, false, 9.0, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Batman', 2022, 3, 10, 100, true, 8, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'Parasite', 2019, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lion King', 2019, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Dark Knight Rises', 2012, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Return of the King', 2003, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Fellowship of the Ring', 2001, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Two Towers', 2002, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Hobbit: An Unexpected Journey', 2012, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Hobbit: The Desolation of Smaug', 2013, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Hobbit: The Battle of the Five Armies', 2012, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'The Lord of the Rings: The Hobbit: The Return of the King', 2012, 0, 8, 80, false, 8.5, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'Begin Again', 2013, 0, 4, 40, false, 7.4, 0);
INSERT INTO movie VALUES (uuid_generate_v4(), 'La La Land', 2016, 0, 7, 70, false, 8, 0);

--reviews
INSERT INTO review VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Shawshank Redemption'), 4, 'The Shawshank Redemption is a great movie. I loved it.');
INSERT INTO review VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Batman'), 5, 'Best movie of the year!');
INSERT INTO review VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Godfather'), 5, 'Best movie ever!');
INSERT INTO review VALUES (uuid_generate_v4(), (select id from users where username = 'efebeydogan'), (select id from movie where title = 'The Batman'), 3, 'I did not like it.');
INSERT INTO review VALUES (uuid_generate_v4(), (select id from users where username = 'ardaonal'), (select id from movie where title = 'The Batman'), 1, 'I hated it!');

--friends
INSERT INTO friend VALUES (uuid_generate_v4(), 'mertbarkiner', 'gokberkbeydemir');
INSERT INTO friend VALUES (uuid_generate_v4(), 'mertbarkiner', 'melisatun');
INSERT INTO friend VALUES (uuid_generate_v4(), 'gokberkbeydemir', 'mertbarkiner');
INSERT INTO friend VALUES (uuid_generate_v4(), 'melisatun', 'mertbarkiner');
INSERT INTO friend VALUES (uuid_generate_v4(), 'melisatun', 'gokberkbeydemir');
INSERT INTO friend VALUES (uuid_generate_v4(), 'gokberkbeydemir', 'melisatun');
INSERT INTO friend VALUES (uuid_generate_v4(), 'mertbarkiner', 'dorukkantarci');
INSERT INTO friend VALUES (uuid_generate_v4(), 'dorukkantarci', 'mertbarkiner');
INSERT INTO friend VALUES (uuid_generate_v4(), 'dorukkantarci', 'melisatun');
INSERT INTO friend VALUES (uuid_generate_v4(), 'melisatun', 'dorukkantarci');
INSERT INTO friend VALUES (uuid_generate_v4(), 'dorukkantarci', 'gokberkbeydemir');
INSERT INTO friend VALUES (uuid_generate_v4(), 'gokberkbeydemir', 'dorukkantarci');

--movie requests
INSERT INTO request VALUES (uuid_generate_v4(), 'The Princess Bride', 1987);
INSERT INTO request VALUES (uuid_generate_v4(), 'Schindler''s List', 1993);
INSERT INTO request VALUES (uuid_generate_v4(), '12 Angry Men', 1957);
INSERT INTO request VALUES (uuid_generate_v4(), 'Pulp Fiction', 1994);

--rent
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Godfather'), '2019-05-05', '2019-06-05');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Shawshank Redemption'), '2020-08-05', '2021-01-01');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2021-02-03', '2021-03-04');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Dark Knight'), '2021-04-05', '2021-05-06');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Godfather'), '2021-06-07', '2021-07-08');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2021-08-09', '2021-09-10');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Dark Knight'), '2021-10-11', '2021-11-12');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Godfather'), '2021-12-13', '2022-01-14');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2022-02-15', null);
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Dark Knight'), '2022-04-17', '2022-05-16');
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Godfather'), '2022-05-19', null);
INSERT INTO rent VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2022-08-21', null);

--buy
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Godfather'), '2019-05-05');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Shawshank Redemption'), '2020-08-05');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2021-02-03');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Dark Knight'), '2021-04-05');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Godfather'), '2021-06-07');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2021-08-09');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Dark Knight'), '2021-10-11');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Godfather'), '2021-12-13');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2022-02-15');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Dark Knight'), '2022-04-17');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Godfather'), '2022-05-16');
INSERT INTO buy VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Lord of the Rings: The Return of the King'), '2020-08-21');

--wish
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'The Lion King'), '2020-08-05');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'mertbarkiner'), (select id from movie where title = 'Parasite'), '2021-02-03');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Lion King'), '2021-04-05');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'Parasite'), '2021-06-07');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'gokberkbeydemir'), (select id from movie where title = 'The Shawshank Redemption'), '2021-08-09');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Godfather: Part II'), '2021-10-11');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Lion King'), '2021-12-13');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'melisatun'), (select id from movie where title = 'The Shawshank Redemption'), '2022-02-15');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Shawshank Redemption'), '2022-04-17');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'Parasite'), '2022-05-16');
INSERT INTO wish VALUES (uuid_generate_v4(), (select id from users where username = 'dorukkantarci'), (select id from movie where title = 'The Lion King'), '2020-08-21');
