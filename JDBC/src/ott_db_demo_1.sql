/*
1.
    Name: Search programmes by attribut
    Actor: Service consumer
    Action: Explores programmes filtered by genre, release date, or duration.
*/

-- Searching for programmes having genre 'Horror'
SELECT *
FROM Programme, Genres
WHERE Genres.genre_id = Programme.genre_id
AND Genres.genre_name = 'Horror';

-- Searching for programmes released after 2022-01-01
SELECT *
FROM Programme
WHERE release_date > '2022-01-01';

-- Searching for programmes with a duration of less than 90 minutes
SELECT *
FROM Programme
WHERE duration < 90;