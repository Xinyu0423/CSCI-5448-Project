import urllib.request
import json

from Movie import Movie

# Movie Adapter -> Adapt OMDb API to work with our application
# PATTERN USAGE: Adapter
class MovieAdapter:
    def __init__(self, apikey='&apikey=1bcdcafb', serviceurl='http://www.omdbapi.com/?'):
        self.apikey = apikey
        self.serviceurl = serviceurl

    # Get movie by ID
    def getMovie(self, id):
        url = self.serviceurl + urllib.parse.urlencode({'i': id}) + self.apikey
        print(f"Retrieving the data of {id} now… ")
        uh = urllib.request.urlopen(url)
        data = uh.read()
        json_data = json.loads(data)

        # Return movie object
        new_movie = Movie(json_data)
        return new_movie

    #  Find movie by name
    def findMovie(self, name):
        url = self.serviceurl + \
            urllib.parse.urlencode({'t': name}) + self.apikey
        print(f"Retrieving the data of {name} now… ")
        uh = urllib.request.urlopen(url)
        data = uh.read()
        json_data = json.loads(data)

        # Return movie object
        new_movie = Movie(json_data)
        return new_movie

    # Search for a movie by name
    def searchMovie(self, name):
        url = self.serviceurl + \
            urllib.parse.urlencode({'s': name}) + self.apikey
        print(f"Retrieving the data of {name} now… ")
        uh = urllib.request.urlopen(url)
        data = uh.read()
        json_data = json.loads(data)
        temp = json_data['Search']
        movieList = list()

        # Return list of movie objects
        for item in temp:
            if str(item['Type']).upper() == 'MOVIE':
                new_movie = Movie(item)
                movieList.append(new_movie)
        return movieList
