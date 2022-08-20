import sqlite3
import re

from Movie import Movie

# PATTERN USAGE: DAO
class MovieProfileDao:
    def __init__(self, database="movietracker.sqlite"):
        # Connect to database
        print("connecting to database")
        self.connection = sqlite3.connect(database, check_same_thread=False)
        self.c = self.connection.cursor()

    # Destructor, close connection
    def __del__(self):
        print("disconnect from database")
        self.connection.close()

    # Get a user's movie list from the database
    def getMovieList(self, username):
        try:
            self.c.execute(
                "SELECT * FROM Movie INNER JOIN UserMovie ON UserMovie.movie_id=Movie.movie_id WHERE UserMovie.username=?",
                (username,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        
        # Get all movie data
        movieData = self.c.fetchall()
        if len(movieData) == 0:
            print("nothing is here")
            return None

        movieList = []
        for movie in movieData:
            # Create new Movie object for each movie on user's list
            temp = dict()
            temp['imdbID'] = movie[0]
            temp['Title'] = movie[1]
            temp['Runtime'] = movie[2]
            temp['Year'] = movie[3]
            temp['Poster'] = movie[4]
            temp['Released'] = movie[5]
            temp['Genre'] = movie[6]
            temp['Ratings'] = movie[7]
            temp['Actors'] = movie[8]
            temp['Plot'] = movie[9]
            temp['imdbVotes'] = movie[10]
            temp['Rated'] = movie[11]
            temp['Director'] = movie[12]
            temp['Writer'] = movie[13]
            temp['Language'] = movie[14]
            temp['Country'] = movie[15]
            temp['Metascore'] = movie[16]
            temp['imdbRating'] = movie[17]
            temp['userRating'] = movie[18]
            temp['userReview'] = movie[19]
            movieList.append(Movie(temp))
        return movieList  # return movie object list

    # Update a user's movie profile
    def updateMovieProfile(self, username):
        try:
            self.c.execute(
                "SELECT runtime FROM Movie INNER JOIN UserMovie ON UserMovie.movie_id=Movie.movie_id WHERE UserMovie.username=?",
                (username,))
        except sqlite3.Error as err:
            print("get runtime error", err)
            return None
        # Get movie data
        rawdata = self.c.fetchall()

        # Calculate total runtime
        totalRuntime = 0
        for i in range(len(rawdata)):
            temp = ''.join(rawdata[i])
            min, _ = temp.split(' ')
            totalRuntime = totalRuntime + int(min)
        try:
            self.c.execute(
                "SELECT movie_id FROM UserMovie WHERE UserMovie.username=?", (username,))
        except sqlite3.Error as err:
            print("count movies error", err)
            return None
        rawdata = self.c.fetchall()

        # Calculate total movie count
        totalCount = len(rawdata)

        try:
            self.c.execute(
                "SELECT rating FROM UserMovie WHERE UserMovie.username=?", (username,))
        except sqlite3.Error as err:
            print("get ratings error", err)
            return None
        rawdata = self.c.fetchall()

        # Calculate average rating
        score = 0
        if (totalCount == 0):
            print("You have not added any movie")
            return False
        for item in rawdata:
            score = score + float(item[0])
        averageRating = score / float(totalCount)  # average rating

        # Update user profile with new values
        self.c.execute(
            "SELECT * FROM MovieProfile WHERE username=?", (username,))
        flag = self.c.fetchall()
        if len(flag) == 0:
            self.c.execute(
                "INSERT INTO MovieProfile (total_movie_length,total_movie_count,average_rating, username)VALUES(?,?,?,?)",
                (totalRuntime, totalCount, averageRating, username,))
        else:
            self.c.execute(
                "UPDATE MovieProfile SET total_movie_length=?, total_movie_count=?,average_rating=? WHERE MovieProfile.username=?",
                (totalRuntime, totalCount, averageRating, username,))

        self.connection.commit()

        return True


    def getTotalWatchtime(self, username):
        try:
            self.c.execute(
                "SELECT total_movie_length FROM MovieProfile WHERE username=?", (username,))
        except sqlite3.Error as err:
            print("db err", err)
            return None
        rawtotalRuntime = self.c.fetchall()

        totalRuntime = int(rawtotalRuntime[0][0])
        return totalRuntime

    def getMoviesWatched(self, username):
        try:
            self.c.execute(
                "SELECT total_movie_count FROM MovieProfile WHERE MovieProfile.username=?", (username,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        rawtotalCount = self.c.fetchall()
        self.connection.commit()

        totalCount = int(rawtotalCount[0][0])

        return totalCount

    def getAverageRating(self, username):
        try:
            self.c.execute(
                "SELECT average_rating FROM MovieProfile WHERE MovieProfile.username=?", (username,))
        except sqlite3.Error as err:
            print("db error", err)
            return None
        rawtotalCount = self.c.fetchall()
        self.connection.commit()

        averageRating = float(rawtotalCount[0][0])

        return averageRating

    def getGenres(self, username):
        try:
            self.c.execute(
                "SELECT genre FROM Movie INNER JOIN UserMovie ON UserMovie.movie_id=Movie.movie_id WHERE UserMovie.username=?",
                (username,))

        except sqlite3.Error as err:
            print("db error", err)
            return None
        rawGenres = self.c.fetchall()
        self.connection.commit()

        genrelist = list()
        for item in rawGenres:
            tempstr = str(item)
            temp = re.sub('[^-A-Za-z0-9]+', ' ', tempstr)
            temp = temp.split(" ")
            for i in range(1, len(temp) - 1):
                genrelist.append(temp[i])

        return genrelist

    def getLeaderBoardInfo(self):
        try:
            self.c.execute("SELECT * FROM MovieProfile")
        except sqlite3.Error as err:
            print("db error", err)
            return None
        profiles = self.c.fetchall()
        return profiles

    def getUserMovie(self,username):
        try:
            self.c.execute("SELECT rating,review,movie_id,username FROM UserMovie WHERE username=?",(username,))
        except sqlite3.Error as err:
            print("db error 123",err)
            return None
        reviews=self.c.fetchall()
        return reviews