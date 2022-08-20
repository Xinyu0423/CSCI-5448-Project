import sqlite3
from sqlite3 import Error

# Create a connection to the SQLite database


def create_connection(path):
    connection = None
    try:
        connection = sqlite3.connect(path)
        print("connection to sqlite db successful")
    except Error as e:
        print(f"the error'{e}' occurred")
    return connection

# Execute a query on the SQLite database


def execute_query(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        connection.commit()
        print("query executed successfully")
    except Error as e:
        print(f"the error'{e}' occurred")


# Create db file
connection = create_connection("movietracker.sqlite")

# Enable foreign keys
connection.execute("PRAGMA foreign_keys = 1")

# Create User db table
create_User_table = """  
CREATE TABLE IF NOT EXISTS User(
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    email TEXT,
    password TEXT
);
"""
execute_query(connection, create_User_table)

# Create Movie db table
create_Movie_table = """
CREATE TABLE IF NOT EXISTS Movie(
    movie_id TEXT UNIQUE,
    title TEXT,
    runtime TEXT,
    releaseyear TEXT,
    poster TEXT,
    releasedate TEXT,
    genre TEXT,
    movie_info TEXT,
    casts TEXT,
    plot TEXT,
    imdb_votes TEXT,
    rated TEXT,
    director TEXT,
    writer TEXT,
    language TEXT,
    country TEXT,
    metascore TEXT,
    imdb_rating REAL
);
"""
execute_query(connection, create_Movie_table)

# Create UserMovie db table
create_UserMovie_table = """
CREATE TABLE IF NOT EXISTS UserMovie(
    rating REAL,
    review TEXT,
    movie_id TEXT,
    username TEXT,
    FOREIGN KEY(movie_id) REFERENCES Movie(movie_id),
    FOREIGN KEY(username) REFERENCES User(username)
    
);
"""
execute_query(connection, create_UserMovie_table)

# Create MovieProfile db table
create_MovieProfile_table = """
CREATE TABLE IF NOT EXISTS MovieProfile(
    total_movie_length INTEGER,
    total_movie_count INTEGER,
    average_rating REAL,
    username TEXT UNIQUE,
    FOREIGN KEY(username) REFERENCES User(username)

    
);
"""
execute_query(connection, create_MovieProfile_table)
