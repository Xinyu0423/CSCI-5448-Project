import React from "react"
import { Button, Table } from "react-bootstrap"
import * as Icon from "react-bootstrap-icons"
import { orderBy } from "lodash"

// Sort list data based on the provided key
const sortedData = (data, sort) => {
    if (sort === "alphabetical") {
        return orderBy(data, 'title', 'asc')
    }
    return orderBy(data, ['userRating', 'title'], ['desc', 'asc'])
}

// Displays a user's movie list on their profile
// showMovieReview - Callback function to show a modal
// sort - defines the order which to sort the movie data
const UserMovieList = ({ data, showMovieReview, sort }) => {
    return (
        <Table bordered hover>
            <thead>
                <tr>
                    <th>Movie</th>
                    <th>Rating</th>
                    <th>Review</th>
                </tr>
            </thead>
            <tbody>
                {sortedData(data, sort).map((movie) => (
                    <tr key={movie.movieID}>
                        <td>
                            {/* Link to movie detail page */}
                            <a href={`/movie/${movie.movieID}`}> {movie.title} </a>
                        </td>
                        <td>
                            {/* User's Movie Rating */}
                            {movie.userRating} <Icon.StarFill />
                        </td>
                        <td>
                            {/* Button to show a user's review */}
                            <Button variant="outline-dark" onClick={() => showMovieReview(movie)}>Review</Button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </Table>
    )
}

export default UserMovieList