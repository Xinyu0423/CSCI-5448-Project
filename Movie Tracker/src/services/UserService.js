// Service file for all user-related API calls

// Add a specified movie to a user's list
// PATTERN USAGE: MVC View, Service files are called by UI to update view
export function addToList(data) {
    const fetchConfig = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": data.username,
            "token": data.token,
            "movieId": data.movieId,
            "rating": data.rating,
            "review": data.review,
        })
    };

    return fetch('/user/list', fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error")
        })
}

// Get user infomation
export function getUser(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    };

    return fetch(`/user?username=${encodeURIComponent(data.username)}&token=${encodeURIComponent(data.token)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error")
        })
}

// Get whether a user has a certain movie added to their list
export function getUserMovieAdded(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/user/movie-added?username=${encodeURIComponent(data.username)}&id=${encodeURIComponent(data.id)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error.")
        })
}

// Login API call, checks whether authentication is valid and returns a JWT auth token if it is
export function loginUser(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/user/login?username=${encodeURIComponent(data.username)}&password=${encodeURIComponent(data.password)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Invalid Username or Password.")
        })
}

// User registration API call
export function registerUser(data) {
    const fetchConfig = {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/user/register?username=${encodeURIComponent(data.username)}&email=${encodeURIComponent(data.email)}&password=${encodeURIComponent(data.password)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("User already exists.")
        })
}

// Remove a specified movie from a user's list
export function removeFromList(data) {
    const fetchConfig = {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": data.username,
            "token": data.token,
            "movieId": data.movieId,
        })
    };

    return fetch('/user/list', fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error")
        })
}

// POST call to update a user's login information
export function updateUser(data) {
    const fetchConfig = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": data.username,
            "token": data.token,
            "password": data.password,
            "email": data.email,
        })
    };

    return fetch('/user', fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error")
        })
}