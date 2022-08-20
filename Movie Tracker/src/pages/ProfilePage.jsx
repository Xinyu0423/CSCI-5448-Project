import React, { Component } from "react";

import { ButtonGroup, Button, Container, Col, ListGroup, Modal, Row } from "react-bootstrap"

import GenreChart from '../components/GenreChart.jsx'
import UserMovieList from '../components/UserMovieList.jsx'

import { getProfileDetail } from "../services/ProfileService.js"

class ProfilePage extends Component {
    constructor(props) {
        super(props);

        // Default state values
        this.state = {
            currentMovie: {},
            movieProfile: {},
            profileUsername: props.match.params.username || "",
            showMovieReview: false,
            sortGenre: "alphabetical",
            sortMovieList: "alphabetical"
        }

        this.getProfile = this.getProfile.bind(this)
    }

    componentDidMount() {
        // Get profile on page load
        this.getProfile()
    }

    getProfile() {
        // Call API to get user profile information
        getProfileDetail({
            username: this.state.profileUsername
        })
            .then(data => {
                // Update state with profile information
                this.setState({ movieProfile: data })
            })
            .catch(e => {
                console.log(e)
            });
    }

    // Show movie review modal for the specified movie
    showMovieReview = (data) => {
        this.setState({ currentMovie: data, showMovieReview: true })
    }

    // Callback function to hide the movie modal
    hideMovieReview = () => {
        this.setState({ showMovieReview: false })
    }

    render() {
        return (
            <Container>
                <h3 className="App-title">{this.state.profileUsername}</h3>
                <Container className="mt-2" fluid>
                    {/* Overall Statistic Labels for Movie Count, Hours Watched, and Movie Rating */}
                    <ListGroup horizontal style={{ display: 'flex', justifyContent: 'center' }}>
                        <ListGroup.Item>
                            <h3 className="App-title">{this.state.movieProfile.totalMovieCount || 0}</h3>
                            <h6 className="App-title">TOTAL MOVIE COUNT</h6>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <h3 className="App-title">{Math.round(this.state.movieProfile.totalRuntime / 60 * 100) / 100 || 0}</h3>
                            <h6 className="App-title">TOTAL HOURS WATCHED</h6>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <h3 className="App-title">{Math.round(this.state.movieProfile.averageRating * 100) / 100 || 0}</h3>
                            <h6 className="App-title">AVERAGE MOVIE RATING</h6>
                        </ListGroup.Item>
                    </ListGroup>
                </Container>

                <Container className="mt-2" fluid>
                    {/* Genre distribution, displayed using GenreChart component */}
                    <Row>
                        <Col>
                            <h5>Genres</h5>
                        </Col>
                        <Col style={{ display: "flex", justifyContent: "flex-end" }}>
                            {/* Buttons to sort genre data alphabetically or by count */}
                            <ButtonGroup>
                                <Button variant="outline-dark" onClick={() => this.setState({ sortGenre: "alphabetical" })}>A-Z</Button>
                                <Button variant="outline-dark" onClick={() => this.setState({ sortGenre: "count" })}>Count</Button>
                            </ButtonGroup>
                        </Col>
                    </Row>
                    <Row>
                        {this.state.movieProfile.genreCount
                            ? <GenreChart data={this.state.movieProfile.genreCount} sort={this.state.sortGenre} />
                            : <i>Add some movies to your list to see a genre distribution here.</i>
                        }
                    </Row>
                </Container>

                <Container className="mt-5" fluid>
                    {/* Show user's movie list */}
                    <Row>
                        <Col>
                            <h5>Movie List</h5>
                        </Col>
                        <Col style={{ display: "flex", justifyContent: "flex-end" }}>
                            {/* Buttons to sort list alphabetically or by user rating */}
                            <ButtonGroup>
                                <Button variant="outline-dark" onClick={() => this.setState({ sortMovieList: "alphabetical" })}>A-Z</Button>
                                <Button variant="outline-dark" onClick={() => this.setState({ sortMovieList: "review" })}>Rating</Button>
                            </ButtonGroup>
                        </Col>
                    </Row>
                    {this.state.movieProfile.movies
                        ?
                        // Component for user's movie list
                        <UserMovieList
                            data={this.state.movieProfile.movies}
                            showMovieReview={this.showMovieReview}
                            sort={this.state.sortMovieList}
                        />
                        : <i>No movies have been added to your list yet.</i>}
                </Container>
                {/* Modal which displays a user's review for a movie, is shown when a "Review" button is clicked 
                    on a list line item. */}
                <Modal size="lg" show={this.state.showMovieReview} onHide={this.hideMovieReview}>
                    <Modal.Header closeButton>
                        <Modal.Title>{this.state.profileUsername}'s Review</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <b>{this.state.currentMovie.title}</b>
                        <br></br>
                        <p>{this.state.currentMovie.userReview}</p>
                    </Modal.Body>
                </Modal>
            </Container>
        )
    }
}

export default ProfilePage;