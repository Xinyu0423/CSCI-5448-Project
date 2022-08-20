# CSCI 5448 Semester Project: Movie Tracker

## Project Team
* Xinyu Jiang
* Qiuyang Wang
* Vladimir Zhdanov

## Instructions to Run
Before running the application, you will need to have the following packages installed:
* [NodeJS](https://nodejs.org/en/) installed along with its package manager `npm` 
* React (`npm install react`)
* Python 3, along with its package manager `pip`.
* Flask (`pip install flask`)
* [Yarn](https://classic.yarnpkg.com/en/docs/install)

The following instructions are written for Ubuntu-based system. If you are on Windows, some of these commands may vary.

*First Time Setup:*
1. You will need to create a python virtual environment. In the main Movie Tracker directory, run `python3 -m venv venv`, then `source venv/bin/activate`.
2. In the virtual environment, then install all needed packages with `cd backend`, `pip install -r requirements.txt`.
3. You will need to set the flask environment variables with `export FLASK_APP=api.py` and `export FLASK_ENV=development`.
4. At this point, you should be able to run the flask server by running the following in the backend directory: `flask run`.
5. From the main Movie Tracker directory, run `yarn` to install all needed frontend dependencies.
6. From the backend directory, run `python3 connect_db.py` to initialize the SQLite database.

*To Run the Application:*
1. In a terminal window, enter the python virtual environment created above and run `cd backend`, `flask run`. This should start up a flask server.
2. In a separate terminal window, go to the main Movie Tracker directory and run `yarn start` to start the frontend development server.
3. Navigate to `http://localhost:3000/` in your web browser to see the Movie Tracker main page.

*Notes for Graders:*
* Usage of patterns is noted under comments in the code labelled as `PATTERN USAGE`.
* The demonstration video can be found [here](https://drive.google.com/file/d/1FwtBpvNOGVkDjBGiU7YztI-VLHfw-uhQ/view?usp=sharing).
* The final report can be found at `Final Report.pdf` in the main Movie Tracker directory.
* Thanks for all your help and hard work this semester! We really appreciate the feedback and help that you've provided for the class.

## Demo Video
The demo video filesize was too large to upload directly to this Github repo. The final video can be accessed though [this Google Drive Link](https://drive.google.com/file/d/1FwtBpvNOGVkDjBGiU7YztI-VLHfw-uhQ/view?usp=sharing).

## Third-Party Resources
For this project, we used several external sources:
* The base React app was bootstrapped from Facebook’s [Create React App](https://github.com/facebook/create-react-app) repository. This allows for a user to create a simple React app with no build configuration.
* Using this base app, we followed [this tutorial by Miguel Grinberg](https://blog.miguelgrinberg.com/post/how-to-create-a-react--flask-project) to get a Flask server running and interacting with a React frontend. A lot of the build files and configurations in our repository are from this tutorial.
* We used the [OMDb API](https://www.omdbapi.com/) as an external API to fetch movie details and movie search information.

All of the remaining code in the project is original code. All of the backend code (DAOs, controllers, the movie API adapter), all of the Flask API logic, and all of the frontend pages and components were created by us for the purpose of this project.

