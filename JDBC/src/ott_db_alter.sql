-- Add foreign key from Programme to Genres
ALTER TABLE Programme
ADD CONSTRAINT fk_programme_genre
FOREIGN KEY (genre_id) REFERENCES Genres (genre_id)
ON DELETE SET NULL;

-- Add foreign key from Programme_Cast to Programme
ALTER TABLE Programme_Cast
ADD CONSTRAINT fk_programme_cast_programme
FOREIGN KEY (programme_id) REFERENCES Programme (programme_id)
ON DELETE CASCADE;

-- Add foreign key from Programme_Cast to Cast
ALTER TABLE Programme_Cast
ADD CONSTRAINT fk_programme_cast_cast
FOREIGN KEY (cast_id) REFERENCES Cast_Details (cast_id)
ON DELETE CASCADE;

-- Add foreign key from Episodes to Programme
ALTER TABLE Episodes
ADD CONSTRAINT fk_episodes_programme
FOREIGN KEY (programme_id) REFERENCES Programme (programme_id)
ON DELETE CASCADE;