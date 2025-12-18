/*
2.
	Name: Search cast members by name
	Actor: Service consumer
	Action: Searches for cast members by their names.
*/

-- Searching for cast members with the name 'Aarav Kapoor'
SELECT *
FROM Cast
WHERE cast_name = 'Aarav Kapoor';

-- Searching for cast members who have worked in multiple programmes
SELECT Cast_Details.cast_name, COUNT(Programme_Cast.programme_id) AS programme_count
FROM Cast_Details
JOIN Programme_Cast ON Cast_Details.cast_id = Programme_Cast.cast_id
JOIN Programme ON Programme_Cast.programme_id = Programme.programme_id
GROUP BY Cast_Details.cast_id
HAVING COUNT(Programme_Cast.programme_id) > 1;

-- Searching for cast members born after 1990-01-01
SELECT *
FROM Cast_Details
WHERE date_of_birth > '1990-01-01';

-- Searching for cast members of a specific programme
SELECT Cast_Details.cast_name
FROM Cast_Details
JOIN Programme_Cast ON Cast_Details.cast_id = Programme_Cast.cast_id
JOIN Programme ON Programme_Cast.programme_id = Programme.programme_id
WHERE Programme.programme_name = 'Galactic Wars';