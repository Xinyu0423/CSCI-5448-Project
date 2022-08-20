from injector import inject

from MovieProfileController import MovieProfileController

from UserDao import UserDao

# PATTERN USAGE: MVC Controller
class UserController:
    @inject
    def __init__(self, userDao: UserDao, movieProfileController: MovieProfileController):
        self.userDao = userDao
        self.movieProfileController = movieProfileController

    # Register a new user
    def registerUser(self, username, password, email):
        # Check if user exists
        if self.userDao.checkUserExists(username):
            return False
        # Register user
        return self.userDao.registerUser(username, password, email)

    # Check if a user with the given username exists
    def checkUserExists(self, username):
        return self.userDao.checkUserExists(username)

    # Check if the specified password is correct for the given username
    def checkUserMatch(self, username, password):
        return self.userDao.getUserMatch(username, password)

    # Get user info
    def getUserInfo(self, username):
        user = self.userDao.getUserInfo(username)
        return user

    # Get user Id from username
    def getUserId(self, username):
        return self.userDao.getIdFromUsername(username)

    # Check if user has movie added
    def isUserMovieAdded(self, username, movieId):
        return self.userDao.getUserMovieAdded(username, movieId)

    # Add movie to user list
    def addToList(self, username, movieId, userRating, userReview):
        # Check if movie has already been added
        if self.isUserMovieAdded(username, movieId):
            print("You have added the movie before")
            return False
        else:
            # Add movie
            self.userDao.addMovie(username, movieId, userRating, userReview)
            # Update movie profile
            self.movieProfileController.updateMovieProfile(username)
            return True

    # Remove movie from user list
    def removeFromList(self, username, movieId):
        # Remove movie
        success = self.userDao.removeMovie(username, movieId)
        # Update movie profile with changes
        self.movieProfileController.updateMovieProfile(username)
        return success
    
    # Update user login information
    def updateUserInfo(self, updateInfo):
        success = True

        # If new password, update the password
        if updateInfo['newPassword'] is not None:
            success = self.userDao.updatePassword(
                updateInfo['username'], updateInfo['newPassword'])
        # If new email, update the email
        if updateInfo['newEmail'] is not None:
            success = self.userDao.updateEmail(
                updateInfo['username'], updateInfo['newEmail'])
        return success