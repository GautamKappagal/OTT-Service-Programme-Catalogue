-- Create the 'Genres' table to store different genres of the programmes
CREATE TABLE Genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,	-- Unique ID for each genre
    genre_name ENUM('Drama', 'Comedy', 'Thriller', 'Action', 'Romance', 'Sci-Fi', 'Horror', 'Fantasy', 'Mystery', 'Adventure') NOT NULL UNIQUE	-- Predefined genres
);

-- Create the 'Programme' table to store information about programmes
CREATE TABLE Programme (
    programme_id INT AUTO_INCREMENT PRIMARY KEY,	-- Unique ID for each programme
    programme_name VARCHAR(255) NOT NULL UNIQUE,	-- Name of the programme
    programme_type ENUM('Movie', 'TV Show', 'Documentary') NOT NULL,	-- Type of the programme
    genre_id INT,	-- Foreign key referencing the Genres table
    release_date DATE,	-- Release date of the programme
    duration INT	-- Duration in minutes (optional)
);

-- Create the 'Cast' table to store information about the cast
CREATE TABLE Cast_Details (
    cast_id INT AUTO_INCREMENT PRIMARY KEY,	-- Unique ID for each cast member
    cast_name VARCHAR(255) NOT NULL UNIQUE,	-- Name of the cast member
    date_of_birth DATE	-- Date of birth of the cast member (optional)
);

-- Create the 'Programme_Cast' table to store the many-to-many relationship between programmes and cast members
CREATE TABLE Programme_Cast (
    programme_id INT,	-- Foreign key referencing the Programme table
    cast_id INT,	-- Foreign key referencing the Cast table
    role VARCHAR(255),	-- Role of the cast member in the programme (optional)
    PRIMARY KEY (programme_id, cast_id)	-- Composite primary key for the many-to-many relationship
);

-- Create the 'Episodes' table for programmes with episodes (like TV shows or series)
CREATE TABLE Episodes (
    episode_id INT AUTO_INCREMENT PRIMARY KEY,	-- Unique ID for each episode
    programme_id INT,	-- Foreign key referencing the Programme table
    episode_name VARCHAR(255) NOT NULL,	-- Name of the episode
    season_number INT,	-- Season number (for series with multiple seasons)
    episode_number INT,	-- Episode number within the season
    release_date DATE,	-- Release date of the episode
    duration INT	-- Duration in minutes
);