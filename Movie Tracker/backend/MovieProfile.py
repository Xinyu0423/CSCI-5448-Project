# Movie Profile Object
class MovieProfile:
    # Variables for movie profile
    averageRating = float
    totalRuntime = int
    totalMovieCount = int
    genreCount = dict
    movies = list

    def __init__(self, averageRating, totalRuntime, movieCount, genres, movieList):
        self.averageRating = averageRating
        self.totalRuntime = totalRuntime
        self.totalMovieCount = movieCount
        self.genreCount = genres
        self.movies = movieList

    # Various getters for movie profile object variables
    def getTotalWatchTime(self):
        return self.totalRuntime

    def getTotalMovieWatched(self):
        return self.totalMovieCount

    def getGenreCount(self):
        return self.genreCount

    def getMovies(self):
        return self.movies

    def getMovieProfileInfo(self):
        temp = list()
        temp.append(self.getTotalWatchTime())
        temp.append(self.getTotalMovieWatched())
        temp.append(self.getGenreCount())
        templist = list()

        for item in self.getMovies():
            templist.append(item.getTitle())
        temp.append(templist)
        return temp

    # Serialize, return Movie as dictionary for easy JSON conversion in the API
    def serialize(self):
        data = self.__dict__

        # Need to serialize each of the movies in the movie list as well
        if data.get('movies'):
            data['movies'] = [m.serialize() for m in data['movies']]

        return data
