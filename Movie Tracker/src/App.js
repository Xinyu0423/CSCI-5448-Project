import React, { Component } from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'

import NavBar from './components/NavBar.jsx'

import LeaderboardPage from './pages/LeaderboardPage.jsx'
import LoginPage from './pages/LoginPage.jsx'
import MovieDetailPage from './pages/MovieDetailPage.jsx'
import ProfilePage from './pages/ProfilePage.jsx'
import RegisterPage from './pages/RegisterPage.jsx'
import SearchPage from './pages/SearchPage.jsx'
import IndexPage from './pages/IndexPage.jsx'
import UserEditPage from './pages/UserEditPage.jsx'

import './css/App.css'

// Main App Router, links to all the various pages in the application
class App extends Component {
    render() {
        return (
            <div className="App">
                <Router>
                    <NavBar />
                    <Switch>
                        <Route exact path="/" component={IndexPage} />
                        <Route path='/leaderboard' component={LeaderboardPage} />
                        <Route path='/login' component={LoginPage} />
                        <Route path='/movie/:id' component={MovieDetailPage} />
                        <Route path='/profile/:username' component={ProfilePage} />
                        <Route path='/register' component={RegisterPage} />
                        <Route path='/search/:query?' component={SearchPage} />
                        <Route path='/user/edit' component={UserEditPage} />
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default App;
