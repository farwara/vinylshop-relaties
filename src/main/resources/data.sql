INSERT INTO genres (name)
VALUES ('Rock'),
       ('Pop'),
       ('Jazz');

INSERT INTO publishers (name)
VALUES ('Universal Music'),
       ('Sony Music'),
       ('Warner Music');

INSERT INTO artists (name, biography)
VALUES ('Queen', 'British rock band formed in London.'),
       ('Michael Jackson', 'American singer and songwriter.'),
       ('Miles Davis', 'American jazz trumpeter.');

INSERT INTO albums (title, release_year, genre_id, publisher_id)
VALUES ('A Night at the Opera', 1975, 1, 1),
       ('Thriller', 1982, 2, 2),
       ('Kind of Blue', 1959, 3, 3);

INSERT INTO stock (condition, price, album_id)
VALUES ('Good', 19.95, 1),
       ('New', 24.99, 2),
       ('Used', 14.50, 3);

INSERT INTO albums_artists (album_id, artist_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);