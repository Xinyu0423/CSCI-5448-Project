
import sqlite3
from Movie import Movie

# PATTERN USAGE: DAO
class MovieDao:

    def __init__(self, database = "movietracker.sqlite"):
        # Connect to database
        print("connecting to database")
        self.connection=sqlite3.connect(database, check_same_thread=False)
        self.c=self.connection.cursor()

    # Destructor, close the connection
    def __del__(self):
        print("disconnect from database")
        self.connection.close()

    # Check if a movie exists by ID
    def checkMovieAddedInID(self, id):
        try:
            self.c.execute("SELECT * FROM Movie WHERE movie_id=?", (id,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        movieData = self.c.fetchall()

        # If movie with ID exists, return True
        if len(movieData) != 0:
            print("Movie is already in our database")
            return True
        else:
            return False

    # Check if a movie exists by name
    def checkMovieAddedInName(self, name):
        try:
            self.c.execute("SELECT * FROM Movie WHERE title LIKE ?", (name,))
        except sqlite3.Error as err:
            print("db error", err)
            return None

        # If movie with name exists, return True
        movieData = self.c.fetchall()
        if len(movieData) != 0:
            print("Movie is already in our database")
            return True
        else:
            return False

    # Add a movie to the database
    def addMovie(self, myMovie):
        try:
            self.c.execute("INSERT INTO Movie VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", (
                myMovie.getID(), myMovie.getTitle(), myMovie.getRuntime(),
                myMovie.getYear(), myMovie.getPoster(), myMovie.getReleaseDate(),
                myMovie.getGenre(), myMovie.getMovieInfo(), myMovie.getCast(), myMovie.getPlot(),
                myMovie.getimdbVotes(),myMovie.getRated(),myMovie.getDirector(),myMovie.getWriter(),
                myMovie.getLanguage(),myMovie.getCountry(),myMovie.getMetascore(),myMovie.getimdbRating()))
        except sqlite3.Error as err:
            print("moviedao insert error", err)
            return None

        self.connection.commit()
        return True

    # Get a movie from the database by ID
    def retrieveMovieByID(self, id):
        try:
            self.c.execute("SELECT * FROM Movie WHERE movie_id=?", (id,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        
        # Get movie data and put it into a dict
        movieData = self.c.fetchall()
        temp=dict()
        temp['imdbID']=movieData[0][0]
        temp['Title']=movieData[0][1]
        temp['Runtime'] = movieData[0][2]
        temp['Year'] = movieData[0][3]
        temp['Poster'] = movieData[0][4]
        temp['Released'] = movieData[0][5]
        temp['Genre'] = movieData[0][6]
        temp['Ratings'] = movieData[0][7]
        temp['Actors'] = movieData[0][8]
        temp['Plot'] = movieData[0][9]
        temp['imdbVotes']=movieData[0][10]
        temp['Rated']=movieData[0][11]
        temp['Director']=movieData[0][12]
        temp['Writer']=movieData[0][13]
        temp['Language']=movieData[0][14]
        temp['Country']=movieData[0][15]
        temp['Metascore']=movieData[0][16]
        temp['imdbRating']=movieData[0][17]

        # Create movie object with data and return it
        myMovie = Movie(temp)
        return myMovie

    # Get a movie from the database by name
    def retrieveMovieByName(self, name):
        fixed_name = "%" + name + "%"
        try:
            self.c.execute("SELECT * FROM Movie WHERE title LIKE ?", (fixed_name,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        movieData = self.c.fetchall()
        
        result=list()
        for i in movieData:
            # Get movie data and put it into a dict
            temp=dict()
            temp['imdbID']=i[0]
            temp['Title']=i[1]
            temp['Runtime'] = i[2]
            temp['Year'] = i[3]
            temp['Poster'] = i[4]
            temp['Released'] = i[5]
            temp['Genre'] = i[6]
            temp['Ratings'] = i[7]
            temp['Actors'] = i[8]
            temp['Plot'] = i[9]
            temp['imdbVotes']=i[10]
            temp['Rated']=i[11]
            temp['Director']=i[12]
            temp['Writer']=i[13]
            temp['Language']=i[14]
            temp['Country']=i[15]
            temp['Metascore']=i[16]
            temp['imdbRating']=i[17]
            # Add movie object to returned list
            myMovie = Movie(temp)
            result.append(myMovie)

        # Return list of movies
        return result
