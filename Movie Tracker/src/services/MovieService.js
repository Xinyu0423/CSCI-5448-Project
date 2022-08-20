// Service file for all movie-related API calls

// Get details for a provided movie ID
// PATTERN USAGE: MVC View, Service files are called by UI to update view
export function getMovieDetails(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/movie/detail/${encodeURIComponent(data.id)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Error.")
        })
}

// Search for a movie based on the provided query
export function getMovieSearch(data) {
    const fetchConfig = {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    };

    return fetch(`/movie/search/${encodeURIComponent(data.query)}`, fetchConfig)
        .then(res => {
            if (res.ok) return res.json()
            throw new Error("Search Error.")
        })
}