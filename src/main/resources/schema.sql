CREATE TABLE IF NOT EXISTS users
(
    id       INT generated by default as identity primary key,
    email    VARCHAR NOT NUll,
    login    VARCHAR NOT NUll,
    username VARCHAR          DEFAULT 'noname',
    birthday DATE    NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS films
(
    id           INT generated by default as identity primary key,
    name         VARCHAR NOT NUll,
    description  TEXT    NOT NULL DEFAULT 'no description',
    release_date DATE    NOT NULL DEFAULT CURRENT_DATE,
    duration     LONG             DEFAULT 0
);

CREATE TABLE IF NOT EXISTS friends
(
    user_id   INT REFERENCES users (id) NOT NUll,
    friend_id INT REFERENCES users (id) NOT NUll,
    status    VARCHAR                   NOT NULL,
    PRIMARY KEY (user_id, friend_id)
);

ALTER TABLE friends
    ADD CONSTRAINT check_status
        CHECK (status IN ('PENDING', 'ACCEPTED', 'REJECTED'));


CREATE TABLE IF NOT EXISTS likes
(
    id      INT generated by default as identity primary key,
    film_id INT REFERENCES films (id) NOT NUll,
    user_id INT REFERENCES users (id) NOT NUll,
    CONSTRAINT unique_likes UNIQUE (user_id, film_id)
);

CREATE TABLE IF NOT EXISTS genre
(
    id    INT generated by default as identity primary key,
    title VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS film_genre
(
    film_id  INT REFERENCES films (id) NOT NUll,
    genre_id INT REFERENCES genre (id) NOT NUll,
    PRIMARY KEY (film_id, genre_id)
);

CREATE TABLE IF NOT EXISTS mpa
(
    id    INT generated by default as identity primary key,
    title varchar NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS film_mpa
(
    film_id INT REFERENCES films (id) NOT NUll,
    mpa_id  INT REFERENCES mpa (id)   NOT NULL,
    PRIMARY KEY (film_id, mpa_id)
);

CREATE INDEX idx_friends_user ON friends (user_id);
CREATE INDEX idx_friends_friend ON friends (friend_id);
CREATE INDEX idx_likes_film ON likes (film_id);
CREATE INDEX idx_likes_user ON likes (user_id);
CREATE INDEX idx_film_genre_film ON film_genre (film_id);
CREATE INDEX idx_film_genre_genre ON film_genre (genre_id);
CREATE INDEX idx_film_mpa_film_id ON film_mpa (film_id);
CREATE INDEX idx_film_mpa_mpa ON film_mpa (mpa_id);