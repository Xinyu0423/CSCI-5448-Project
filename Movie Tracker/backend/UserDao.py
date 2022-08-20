import hashlib
import sqlite3

from User import User

# PATTERN USAGE: DAO
class UserDao:
    def __init__(self, database='movietracker.sqlite'):
        # Connect to database and set row mapper
        print("connecting to database")
        self.connection = sqlite3.connect(database, check_same_thread=False)
        self.connection.row_factory = sqlite3.Row
        self.c = self.connection.cursor()

    # Destructor, close the connection
    def __del__(self):
        print("disconnect from database")
        self.connection.close()

    def registerUser(self, username, password, email):
        # Hash the password
        passwordHash = hashlib.md5(password.encode()).hexdigest()

        try:
            self.c.execute("SELECT * FROM User")
        except sqlite3.Error as err:
            print("db error", err)
            return False
        rawdata = self.c.fetchall()

        # If no users exist, specify ID
        if (len(rawdata) == 0):
            userid = 1001
            try:
                self.c.execute("INSERT INTO User (user_id,username,email, password) VALUES (?,?,?,?)",
                               (userid, username, email, passwordHash))
            except sqlite3.Error as err:
                print("db error", err)
                return False
        # Else, let the primary key auto-increment
        else:
            try:
                self.c.execute("INSERT INTO User (username,email,password) VALUES(?,?,?)",
                               (username, email, passwordHash))
            except sqlite3.Error as err:
                print("db error", err)
                return False

        self.connection.commit()
        return True

    # Check if user exists
    def checkUserExists(self, username):
        try:
            self.c.execute(
                "SELECT user_id FROM User WHERE username=?", (username,))
        except sqlite3.Error as err:
            print("db error", err)
            return False
        users = self.c.fetchall()
        # User exists if there is at least one result (because of uniqueness there will only be one)
        return len(users) > 0

    # Check if username and password match
    def getUserMatch(self, username, password):
        # Hash the password
        password_hash = hashlib.md5(password.encode()).hexdigest()

        try:
            self.c.execute("SELECT user_id FROM User WHERE username=? AND password=?",
                           (username, password_hash))
        except sqlite3.Error as err:
            print("db error", err)

        users = self.c.fetchall()
        # Match exists if there is one user with this criteria
        return len(users) > 0

    # Get user information
    def getUserInfo(self, username):
        try:
            self.c.execute(
                "SELECT * FROM User WHERE User.username=?", (username,))
        except sqlite3.Error as err:
            print("db error", err)
        thisUserInfo = dict(self.c.fetchone())
        # Return a user object
        thisUser = User(thisUserInfo.get("user_id"), thisUserInfo.get(
            "username"), thisUserInfo.get("email"), thisUserInfo.get("password"))
        return thisUser

    # Get ID from username
    def getIdFromUsername(self, username):
        try:
            self.c.execute(
                "SELECT * FROM User WHERE username=?", (username,))
        except sqlite3.Error as err:
            print("db error", err)
        user = self.c.fetchone()
        return dict(user).get('user_id')

    # Add new movie to the user's list
    def addMovie(self, username, movieID, userRating, userReview):
        self.c.execute("INSERT INTO UserMovie values(?,?,?,?)",
                       (userRating, userReview, movieID, username))
        self.connection.commit()
        return True

    # Check if user has already added a movie
    def getUserMovieAdded(self, username, movie_id):
        self.c.execute(
            "SELECT movie_id FROM UserMovie WHERE UserMovie.username=? AND UserMovie.movie_id=?", (username, movie_id,))
        added = self.c.fetchall()

        return len(added) > 0

    # Update a user's password
    def updatePassword(self, username, newPassword):
        # Hash the password
        passwordHash = hashlib.md5(newPassword.encode()).hexdigest()
        try:
            self.c.execute(
                "UPDATE User SET password=? WHERE username=?", (passwordHash, username))
            self.connection.commit()
        except sqlite3.Error as err:
            print("update password error", err)
            return False
        return True

    # Update a user's email
    def updateEmail(self, username, newEmail):
        try:
            self.c.execute(
                "UPDATE User SET email=? WHERE username=?", (newEmail, username))
            self.connection.commit()
        except sqlite3.Error as err:
            return False
        return True

    # Remove a movie from the user's list
    def removeMovie(self, username, movieId):
        try:
            self.c.execute(
                "DELETE FROM UserMovie WHERE username=? and movie_id=?", (username, movieId))
            self.connection.commit()
            return True
        except sqlite3.Error as err:
            print("db error", err)
        return False
