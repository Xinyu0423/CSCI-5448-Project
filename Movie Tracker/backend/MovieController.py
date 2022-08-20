from injector import inject

from MovieAdapter import MovieAdapter
from MovieDao import MovieDao

# PATTERN USAGE: MVC Controller
class MovieController:
    @inject
    def __init__(self, movieDao: MovieDao, movieAdapter: MovieAdapter):
        self.movieDao = movieDao
        self.movieAdapter = movieAdapter

    # Get movie by ID
    def getMovie(self, id):
        # If the movie is already in our database
        if self.movieDao.checkMovieAddedInID(id):
            # Get the movie and return it
            myMovie = self.movieDao.retrieveMovieByID(id)
            return myMovie
        else:
            # Get the movie from OMDb and return it
            myMovie = self.movieAdapter.getMovie(id)
            # Add the new movie to the list
            self.movieDao.addMovie(myMovie)
            return myMovie

    # Search for a movie by name
    def searchMovie(self, name):
        # Get movie list from OMDb and return it
        myMovieList = self.movieAdapter.searchMovie(name)
        return myMovieList

    # Find a movie by name
    def findMovie(self, name):
        added = self.movieDao.checkMovieAddedInName(name)
        # If movie already in our database
        if added: 
            # Get movie from database and return it
            myMovie = self.movieDao.retrieveMovieByName(name)
            return myMovie
        else:
            # Get movie from OMDb and return it
            myMovie = self.movieAdapter.findMovie(name)
            return myMovie
