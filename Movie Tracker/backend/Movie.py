import json

# Movie Object class
class Movie:
    # Variables for a Movie Object
    movieID = str  # same as IMDb ID
    title = str
    runtime = str
    releaseYear = str
    poster = str
    releaseDate = str
    genre = str
    movieInfo = str
    casts = str
    plot = str
    imdb_votes = int
    rated = str
    director = str
    writer = str
    language = str
    country = str
    metascore = int
    imdb_rating = float

    # User specific variables, used for the Movie Profile
    userRating = int
    userReview = str

    # Constructor takes in dictionary of values, sets those which exist to their respective variables
    def __init__(self, movieData):
        self.title = movieData.get('Title')
        self.runtime = movieData.get('Runtime')
        self.releaseYear = movieData.get('Year')
        self.poster = movieData.get('Poster')
        self.releaseDate = movieData.get('Released')
        self.genre = movieData.get('Genre')
        self.movieInfo = json.dumps(movieData.get('Ratings'))
        self.casts = movieData.get('Actors')
        self.plot = movieData.get('Plot')
        self.movieID = movieData.get('imdbID')
        self.imdb_votes = movieData.get('imdbVotes')
        self.rated = movieData.get('Rated')
        self.director = movieData.get('Director')
        self.writer = movieData.get('Writer')
        self.language = movieData.get('Language')
        self.country = movieData.get('Country')
        self.metascore = movieData.get('Metascore')
        self.imdb_rating = movieData.get('imdbRating')
        self.userRating = movieData.get('userRating')
        self.userReview = movieData.get('userReview')

    # Various getters for all of the movie object variables
    def getTitle(self):
        return self.title

    def getRuntime(self):
        return self.runtime

    def getYear(self):
        return self.releaseYear

    def getPoster(self):
        return self.poster

    def getReleaseDate(self):
        return self.releaseDate

    def getGenre(self):
        return self.genre

    def getMovieInfo(self):
        return self.movieInfo

    def getPlot(self):
        return self.plot

    def getID(self):
        return self.movieID

    def getCast(self):
        return self.casts

    def getimdbVotes(self):
        return self.imdb_votes

    def getRated(self):
        return self.rated

    def getDirector(self):
        return self.director

    def getWriter(self):
        return self.writer

    def getLanguage(self):
        return self.language

    def getCountry(self):
        return self.country

    def getMetascore(self):
        return self.metascore

    def getimdbRating(self):
        return self.imdb_rating

    # Serialize, return Movie as dictionary for easy JSON conversion in the API
    def serialize(self):
        return self.__dict__
