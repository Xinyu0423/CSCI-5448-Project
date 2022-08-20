// Service file for all profile-related API calls

// Get Movie Profile for provided username
// PATTERN USAGE: MVC View, Service files are called by UI to update view
export function getProfileDetail(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/profile/${encodeURIComponent(data.username)}/detail`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error.")
        })
}

// Get a list of all user profiles to be used in the leaderboard page
export function getProfileLeaderboard() {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/profile/leaderboard`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error.")
        })
}