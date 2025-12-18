/*
3.
	Name: Search episodes by attributes
	Actor: Service consumer
	Action: Searches for episodes filtered by programme name, season number, or episode number.
*/

-- Searching for episodes of the programme 'Galactic Wars'
SELECT e.season_number, e.episode_number, e.episode_name, e.release_date, e.duration
FROM Episodes as e
JOIN Programme as p ON e.programme_id = p.programme_id
WHERE p.programme_name = 'Galactic Wars';

-- Searching for all episodes in season 1 of 'Galatic Wars'
SELECT e.episode_number, e.episode_name, e.release_date, e.duration
FROM Episodes as e
JOIN Programme as p ON e.programme_id = p.programme_id
WHERE e.season_number = 1 AND p.programme_name = 'Galactic Wars';