from MovieController import MovieController
from MovieProfileController import MovieProfileController
from UserController import UserController

from MovieAdapter import MovieAdapter

from MovieDao import MovieDao
from MovieProfileDao import MovieProfileDao
from UserDao import UserDao


# Test user registration
def RegisterUserTestCase():
    print("running registeration test case")
    username='qiuyang'
    password='12345' 
    email='qiwa8995@colorado.edu'
    print("The username is going to be registered:",username)
    print("The email address is going to be registered:",email)
    myUserController=UserController(UserDao(),MovieProfileController(MovieProfileDao()))
    print('\n')
    myUserController.registerUser(username,password,email)
    print("expected result:", username, email)
    print("Actual output:")
    print(myUserController.getUserInfo(username).getUsername())
    print(myUserController.getUserInfo(username).getEmail())
    print('\n')

# Test adding a movie to the database
def AddMovieTestCase():
    print("running add movie test case")
    movieid='tt3896198'
    rating=3.5
    review='great'
    username='qiuyang'
    print("Review is going to be added:",review)
    print("Rating is going to be added",rating)
    myUserController=UserController(UserDao(),MovieProfileController(MovieProfileDao()))
    myUserController.addToList(username,movieid,rating,review)
    print('\n')
    print("expected result: rating, review, movieid, username")
    myMovieProfileController=MovieProfileController(MovieProfileDao())
    print(myMovieProfileController.getUserReviews(username))
    print('\n')

# Test getting the leaderboard
def LeaderBoardTestCase():
    print("running leaderboard test case")
    mymovieprofile=MovieProfileController(MovieProfileDao())
    print('\n')
    print("expected result: total movie watched time,total movie count,list of average rating, username")
    print(mymovieprofile.getLeaderBoard())
    print('\n')

# Test getting movie profile
def GetUserMovieProfileTestCase():
    print("running get user movie profile test case")
    username='qiuyang'
    myMovieProfileController=MovieProfileController(MovieProfileDao())
    print('\n')
    print("expected result: total watched time, movie count, movie genres, list of added movie titles  ")
    print(myMovieProfileController.getMovieProfile(username).getMovieProfileInfo())
    print('\n')

# Run tests
def main():
    RegisterUserTestCase()
    print('\n')
    AddMovieTestCase()
    print('\n')
    LeaderBoardTestCase()
    print('\n')
    GetUserMovieProfileTestCase()



main()