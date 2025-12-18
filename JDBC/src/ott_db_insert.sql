-- Insert into Genres table
INSERT INTO Genres (genre_name) VALUES
('Drama'), ('Comedy'), ('Thriller'), ('Action'), ('Romance'), ('Sci-Fi'), ('Horror'), ('Fantasy'), ('Mystery'), ('Adventure');

-- Insert into Programme table (genre_id must match valid genre in Genres table)
INSERT INTO Programme (programme_name, programme_type, genre_id, release_date, duration) VALUES
('Galactic Wars', 'TV Show', 6, '2023-11-01', 130),
('Love Downtown', 'Movie', 5, '2022-06-15', 95),
('The Hidden Clue', 'TV Show', 10, '2023-03-22', 48),
('Laughing Stock', 'TV Show', 2, '2021-09-10', 40),
('Fear Mansion', 'Movie', 7, '2022-10-31', 100);

-- Insert into Cast table
INSERT INTO Cast_Details (cast_name, date_of_birth) VALUES
('Aarav Kapoor', '1985-04-12'),
('Maya Iyer', '1990-08-07'),
('Zoya Khan', '1995-01-22'),
('Dev Malhotra', '1980-12-05'),
('Rhea Banerjee', '1988-07-18');

-- Insert into Programme_Cast table (progr amme_id and cast_id must match valid ids in Programme and Cast tables)
INSERT INTO Programme_Cast (programme_id, cast_id, role) VALUES
(1, 1, 'Captain Rex'),
(1, 2, 'Commander Leia'),
(2, 5, 'Ananya'),
(3, 3, 'Detective Zara'),
(4, 4, 'Host'),
(5, 3, 'Ghost Whisperer');

-- Insert into Episodes table (programme_id must match valid ids in Programme table)
INSERT INTO Episodes (programme_id, episode_name, season_number, episode_number, release_date, duration) VALUES
(1, 'The Spark', 1, 1, '2023-11-01', 42),
(1, 'Empire''s Fall', 1, 2, '2023-11-08', 45),
(3, 'Lost Letter', 1, 1, '2023-03-22', 48),
(4, 'Pilot Laughs', 1, 1, '2021-09-10', 40),
(1, 'Destruction', 2, 1, '2024-08-02', 43);