// Utility functions related to User Authentication

// Check if user is logged in
export function isLoggedIn() {
    return localStorage.getItem("movietracker_access_token") !== null && localStorage.getItem("movietracker_access_token") !== undefined;
}

// Get username from local browser storage
export function getUsername() {
    return localStorage.getItem("movietracker_username");
}

// Get JWT auth token from local browser storage 
export function getAccessToken() {
    return localStorage.getItem("movietracker_access_token");
}

// Set username value in local browser storage
export function setUsername(username) {
    localStorage.setItem('movietracker_username', username)
}

// Set JWT auth token in local browser storage
export function setAccessToken(token) {
    localStorage.setItem('movietracker_access_token', token)
}

// Clear Movie Tracker data from local storage
export function deleteTokens() {
    localStorage.removeItem("movietracker_access_token");
    localStorage.removeItem("movietracker_username");
}