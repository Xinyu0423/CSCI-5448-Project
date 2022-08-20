# User Object
class User:
    # Variables on user object
    userID = int
    username = str
    email = str
    passwordHash = str

    def __init__(self, userID, username, email, password):
        self.email = email
        self.username = username
        self.passwordHash = password
        self.userID = userID

    # Get values of variables
    def getUsername(self):
        return self.username

    def getEmail(self):
        return self.email

    def getPasswordHash(self):
        return self.passwordHash

    def getID(self):
        return self.userID

    # Serialize, return User as dictionary for easy JSON conversion in the API
    def serialize(self):
        return self.__dict__
