import React, { Component } from "react";
import { Button, Col, Container, Form, Image, ListGroup, Row } from "react-bootstrap"

import { trendingMovies } from "../services/trending.js"

class IndexPage extends Component {
    constructor(props) {
        super(props)

        // Default state values
        this.state = {
            query: "",
            searchResult: [],
        }
        this.searchMovie = this.searchMovie.bind(this)
    }

    searchMovie(e) {
        // Navigate user to search page with filled in query when they search
        this.props.history.push(`/search/${encodeURIComponent(this.state.query)}`);
    }

    componentDidMount() {
        // Set trending movies on the front page when page loads
        this.setState({ searchResult: trendingMovies })
    }

    render() {
        return (
            <>
                <h1 className="App-title">Movie Tracker</h1>
                <Container>
                    {/* Search bar, takes user to the search page when submitted */}
                    <Form onSubmit={this.searchMovie} action="/search">
                        <Form.Group>
                            <Form.Control type="search" placeholder="Search for a Movie..."
                                defaultValue={this.state.query}
                                onChange={(e) => this.setState({ query: e.target.value })} />
                            <Button style={{ display: "none" }} defaultChecked={true} variant="secondary"
                                type="submit">
                                Search
                            </Button>
                        </Form.Group>
                    </Form>
                </Container>
                {this.state.searchResult.length &&
                    // Show Trending movies
                    <Container className="mt-2">
                        <h5>Trending Movies</h5>
                        <ListGroup>
                            <ListGroup.Item>
                                <Container>
                                    <Row xs={3} noGutters>
                                        {/* Map movies columns, display cover and title for each */}
                                        {this.state.searchResult.map((movie, index) => (
                                            <Col key={index} style={{ textAlign: "center" }}>
                                                <a href={`/movie/${movie.movieID}`}>
                                                    <Image
                                                        src={movie.poster}
                                                        style={{
                                                            height: '250px',
                                                            alignItems: 'center',
                                                            justifyContent: 'center'
                                                        }}
                                                    />
                                                </a>
                                                <br />
                                                <a href={`/movie/${movie.movieID}`}>
                                                    <b>{movie.title}</b>
                                                </a>
                                                <br />
                                                <label><b>Release Year: </b> {movie.releaseYear}</label>
                                            </Col>
                                        ))}
                                    </Row>
                                </Container>
                            </ListGroup.Item>
                        </ListGroup>
                    </Container>
                }
            </>
        )
    }
}

export default IndexPage;