from flask import Flask, jsonify, request
from flask_jwt_extended import JWTManager, jwt_required, create_access_token, get_jwt_identity, decode_token
from flask_injector import FlaskInjector
from injector import inject

from MovieController import MovieController
from MovieProfileController import MovieProfileController
from UserController import UserController

from dependencies import configure

# Declare the instance of the flask app
app = Flask(__name__, static_folder='../build', static_url_path='/')

# Configure secret key and jwt manager for flask app
app.config['JWT_SECRET_KEY'] = 'movie-tracker-is-cool'
jwt = JWTManager(app)


# Error endpoint, return main page
@app.errorhandler(404)
def not_found(e):
    return app.send_static_file('index.html')


# Return index file, main page
@app.route('/')
def index():
    return app.send_static_file('index.html')


# User login route
@app.route('/user/login', methods=['GET'])
@inject
def login(userController: UserController):
    # Get username and password from arguments
    username = request.args.get('username')
    password = request.args.get('password')

    # Check that the password is correct for the specified username
    user_match = userController.checkUserMatch(username, password)

    # Return authentication token if password is correct
    if user_match:
        token = create_access_token(identity=username)
        return jsonify({'username': username, 'token': token})
    # Return error if invalid login
    else:
        return jsonify({'message': 'Invalid username or password.'}), 404


# User Registration route
@app.route('/user/register', methods=['PUT'])
@inject
def register(userController: UserController):
    # Get username, password, and email from arguments
    username = request.args.get('username')
    email = request.args.get('email')
    password = request.args.get('password')

    # Check if the user exists
    added = userController.checkUserExists(username)

    # Return error if user already exists
    if added:
        return jsonify({'message': 'User already exists.'}), 404
    else:
        # Register the user
        userController.registerUser(username, password, email)
        # Return an authentication token for the new user
        token = create_access_token(identity=username)
        return jsonify({'username': username, 'token': token})


# Get user info route
@app.route('/user', methods=['GET'])
@inject
def get_user(userController: UserController):
    # Get username and password from arguments
    username = request.args.get('username')
    token = request.args.get('token')

    # Verify that the requesting user is allowed to view this information
    if decode_token(token, allow_expired=True).get('identity') != username:
        return jsonify({'message': 'Invalid Authentication'}), 403
    # Return user info
    return jsonify(userController.getUserInfo(username).serialize())


# Update user info route
@app.route('/user', methods=['POST'])
@inject
def update_user(userController: UserController):
    # Get data from the request
    data = request.json
    username = data.get('username')
    token = data.get('token')
    email = data.get('email')
    password = data.get('password')

    # Check token to ensure user is updating themselves
    if decode_token(token, allow_expired=True).get('identity') != username:
        return jsonify({'message': 'Invalid Authentication'}), 403

    # Update the user's information
    success = userController.updateUserInfo({
        'username': username,
        'newEmail': email,
        'newPassword': password,
    })

    # Return error if unable to update
    if not success:
        return jsonify({'message': 'Unable update user'}), 500
    # Return success message
    return jsonify({'message': 'User successfully updated'})


# Route to check if user has movie added to their list
@app.route('/user/movie-added', methods=['GET'])
@inject
def movie_added(userController: UserController):
    # Get username and movie id from arguments
    username = request.args.get('username')
    movieId = request.args.get('id')

    # Return whether the user has the specified movie added
    return jsonify(added=userController.isUserMovieAdded(username, movieId))


# Route to add movie to a user's list
@app.route('/user/list', methods=['PUT'])
@inject
def add_to_list(userController: UserController):
    # Get info from request data
    data = request.json
    username = data.get('username')
    token = data.get('token')
    movieId = data.get('movieId')
    rating = data.get('rating')
    review = data.get('review')

    # Check that the user is adding the movie to their own list through the token
    if decode_token(token, allow_expired=True).get('identity') != username:
        return jsonify({'message': 'Invalid Authentication'}), 403

    # Add the movie to the user's list
    success = userController.addToList(username, movieId, rating, review)

    # Return error if unsuccessful
    if not success:
        return jsonify({'message': 'Unable to add movie to list'}), 500
    # Return success message
    return jsonify({'message': 'Movie successfully added'})


# Route to delete a movie from a user's list
@app.route('/user/list', methods=['DELETE'])
@inject
def remove_from_list(userController: UserController):
    # Get info from request data
    data = request.json
    username = data.get('username')
    token = data.get('token')
    movieId = data.get('movieId')

    # Check that the user is removing the movie from their own list through the token
    if decode_token(token, allow_expired=True).get('identity') != username:
        return jsonify({'message': 'Invalid Authentication'}), 403
    
    # Remove the movie from the user's list
    success = userController.removeFromList(username, movieId)

    # Return error if not successful
    if not success:
        return jsonify({'message': 'Unable to remove movie from list'}), 500
    # Return success message
    return jsonify({'message': 'Movie successfully removed'})


# Get movie details route
@app.route('/movie/detail/<id>', methods=['GET'])
@inject
def get_movie(id, movieController: MovieController):
    # Get the movie by id
    thisMovie = movieController.getMovie(id)

    # Return the serialized movie data to the frontend
    return jsonify(thisMovie.serialize())


# Search for movie route
@app.route('/movie/search/<query>', methods=['GET'])
@inject
def search_movie(query, movieController: MovieController):
    # Get searched movie list from query
    movieList = movieController.searchMovie(query)
    
    # Serialize every movie in the list and return it to the user
    return jsonify([m.serialize() for m in movieList])


# Get movie profile route
@app.route('/profile/<username>/detail', methods=['GET'])
@inject
def get_list(username, movieProfileController: MovieProfileController):
    # Get profile for specified user
    userProfile = movieProfileController.getMovieProfile(username)

    # Return serialized profile
    return jsonify(userProfile.serialize())


# Get profile leaderboard route
@app.route('/profile/leaderboard', methods=['GET'])
@inject
def get_leader_board(movieProfileController: MovieProfileController):
    # Get profile leaderboard and return it to the user
    return jsonify(movieProfileController.getLeaderBoard())


# Configure flask injector, inject singletons of every specified dependency into the correct objects
FlaskInjector(app=app, modules=[configure])

# Run the app
if __name__ == "__main__":
    app.run()
