/*
5.
    Name: Add episodes to a programme
    Actor: Service provider
    Action: Adds newly released episodes (say weekly) to an existing programme and updates information about the programme.
*/

-- Adding a new episode to 'Galactic Wars'

INSERT INTO Episodes (programme_id, episode_name, season_number, episode_number, release_date, duration) VALUES (1, 'The New Dawn', 2, 2, '2024-11-01', 44);

UPDATE Programme SET duration = duration + 44 WHERE programme_id = 1;

-- If an error occurs during the above operations, the transaction should be rolled back to maintain data integrity.

-- Exceptional Flow: Example if an invalid programme_id is used
-- This insert will fail if programme_id 9999 does not exist
INSERT INTO Episodes (programme_id, episode_name, season_number, episode_number, release_date, duration) VALUES (9999, 'Phantom Episode', 3, 1, '2024-12-01', 50);