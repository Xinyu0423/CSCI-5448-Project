from collections import Counter
from injector import inject

from MovieProfile import MovieProfile
from MovieProfileDao import MovieProfileDao

# PATTERN USAGE: MVC Controller
class MovieProfileController:
    @inject
    def __init__(self, movieProfileDao: MovieProfileDao):
        self.movieProfileDao = movieProfileDao

    # Get a movie profile for the provided username
    def getMovieProfile(self, username):
        # Get total watch time
        myWatchTime = self.movieProfileDao.getTotalWatchtime(username)

        # Get total movie count
        myMovieCount = self.movieProfileDao.getMoviesWatched(username)

        # Get genre list
        myGenres = Counter(self.movieProfileDao.getGenres(username))

        # Get movie list
        myMovies = self.getMovieList(username)

        # Get average rating
        averageRating = self.movieProfileDao.getAverageRating(username)

        # Return movie profile
        myProfile = MovieProfile(averageRating, myWatchTime, myMovieCount, myGenres, myMovies)
        return myProfile

    # Get a user's movie list
    def getMovieList(self, username):
        return self.movieProfileDao.getMovieList(username)

    # Update a user's movie profile (called when movies are added/removed from their list)
    def updateMovieProfile(self, username):
        return self.movieProfileDao.updateMovieProfile(username)

    # Get user profile leaderboard
    def getLeaderBoard(self):
        return self.movieProfileDao.getLeaderBoardInfo()

    # Get all user reviews
    def getUserReviews(self,username):
        return self.movieProfileDao.getUserMovie(username)