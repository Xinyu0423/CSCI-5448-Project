from injector import singleton

from MovieController import MovieController
from MovieProfileController import MovieProfileController
from UserController import UserController

from MovieAdapter import MovieAdapter

from MovieDao import MovieDao
from MovieProfileDao import MovieProfileDao
from UserDao import UserDao

# Configure dependencies for flask injector
# Bind a singleton object of each of the specified classes to be injected when specified
def configure(binder):
    binder.bind(MovieAdapter, to=MovieAdapter, scope=singleton)

    binder.bind(UserDao, to=UserDao, scope=singleton)
    binder.bind(MovieDao, to=MovieDao, scope=singleton)
    binder.bind(MovieProfileDao, to=MovieProfileDao, scope=singleton)

    binder.bind(MovieProfileController,
                to=MovieProfileController, scope=singleton)
    binder.bind(MovieController, to=MovieController, scope=singleton)
    binder.bind(UserController, to=UserController, scope=singleton)
