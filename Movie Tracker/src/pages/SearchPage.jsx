import React, { Component } from "react";
import { Alert, Button, Container, Form, ListGroup } from "react-bootstrap"

import MovieSearchItem from "../components/MovieSearchItem.jsx"

import { getMovieSearch } from "../services/MovieService.js"

class SearchPage extends Component {
    constructor(props) {
        super(props)

        // Set default state values
        this.state = {
            query: props.match.params.query || "",
            searchResult: [],
            invalid: false,
        }
        this.searchMovie = this.searchMovie.bind(this)
    }

    searchMovie(e) {
        // Prevent page from refreshing when form is submitted
        e.preventDefault()

        // Reset data from previous search (if applicable)
        this.setState({ invalid: false })
        this.setState({ searchResult: [] })

        // Call API to get movie search results
        getMovieSearch({
            query: this.state.query
        })
            .then(data => {
                // Set search result data
                this.setState({ searchResult: data })
            })
            .catch((e) => this.setState({ invalid: true }));
    }


    componentDidMount() {
        // If a query is specified in the URL, search for it immediately
        const query = this.props.match.params.query;

        if (query) {
            getMovieSearch({
                query
            })
                .then(data => {
                    this.setState({ searchResult: data })
                })
                .catch((e) => this.setState({ invalid: true }));
        }
    }


    render() {
        return (
            <>
                <h3 className="App-title">Movie Search</h3>
                <Container>
                    {this.state.invalid &&
                        // Error message for invalid search
                        <Alert variant="danger" onClose={() => this.setState({ invalid: false })} dismissible>
                            Unable to find movie with name: {this.state.query}
                        </Alert>}
                    {/* Form for search bar */}
                    <Form onSubmit={this.searchMovie}>
                        <Form.Group>
                            <Form.Control type="text" placeholder="Search for a Movie..."
                                defaultValue={this.state.query}
                                onChange={(e) => this.setState({ query: e.target.value, invalid: false })} />
                        </Form.Group>
                        <Button variant="secondary" type="submit">
                            Search
                        </Button>
                    </Form>
                </Container>
                {this.state.searchResult.length > 0 &&
                    // Show results
                    <Container className="mt-2">
                        <h5>Search Results</h5>
                        <ListGroup>
                            <ListGroup>
                                {/* Display each result as a MovieSearchItem object */}
                                {this.state.searchResult.map((result, index) =>
                                    <MovieSearchItem data={result} key={index} />)}
                            </ListGroup>
                        </ListGroup>
                    </Container>
                }
            </>
        )
    }
}

export default SearchPage;