DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS books;

CREATE TABLE IF NOT EXISTS genres(
  genre_id INT AUTO_INCREMENT,
  genre_name VARCHAR(255),
  PRIMARY KEY (genre_id)
);

CREATE TABLE IF NOT EXISTS authors(
  author_id INT AUTO_INCREMENT,
  author_name VARCHAR(255),
  PRIMARY KEY (author_id)
);

CREATE TABLE IF NOT EXISTS books(
  book_id INT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  author_id INT REFERENCES authors(author_id),
  genre_id INT REFERENCES genres(genre_id),
  PRIMARY KEY (book_id)
);

CREATE TABLE IF NOT EXISTS comments(
  comment_id INT AUTO_INCREMENT,
  comment_text VARCHAR(255),
  book_id INT REFERENCES books(book_id),
  PRIMARY KEY (comment_id)
);




