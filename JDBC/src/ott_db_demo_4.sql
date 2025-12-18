/*
4.
    Name: Manage programmes
    Actor: Service provider
    Action: Adds new programmes with details or deletes them using programme ID.
*/
-- Adding a new programme
INSERT INTO Programme (programme_name, programme_type, genre_id, release_date, duration) VALUES ('The Lost Treasure', 'Movie', 9, '2023-12-01', 120);

-- Deleting a programme by ID
DELETE FROM Programme WHERE programme_id = 10;

-- Updating a programme's genre
UPDATE Programme SET genre_id = 3 WHERE programme_id = 1;

-- Updating a programme's release date
UPDATE Programme SET release_date = '2023-11-15' WHERE programme_id = 2;