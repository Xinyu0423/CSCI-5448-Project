import React, { Component } from "react";

import { Button, ButtonGroup, Col, Container, Row } from "react-bootstrap"
import * as Icon from "react-bootstrap-icons"

import AddMovieModal from "../components/AddMovieModal.jsx"
import MovieDetail from "../components/MovieDetail.jsx"

import { getAccessToken, getUsername, isLoggedIn } from "../services/auth.js"
import { getMovieDetails } from "../services/MovieService.js"
import { addToList, getUserMovieAdded, removeFromList } from "../services/UserService.js"

class MovieDetailPage extends Component {
    constructor(props) {
        super(props);

        // Set default values for the state
        this.state = {
            movieAdded: false,
            movieId: props.match.params.id,
            movieDetails: {},
            showAddModal: false,
            rating: null,
            review: "",
        }

        this.getMovieDetails = this.getMovieDetails.bind(this)
        this.getUserMovieAdded = this.getUserMovieAdded.bind(this)
    }

    componentDidMount() {
        // Get movie details from url parameters as soon as page loads
        this.getMovieDetails(this.props.match.params.id);
        this.getUserMovieAdded(this.props.match.params.id);
    }

    // Check if user has the current movie already added or not
    getUserMovieAdded(id) {
        getUserMovieAdded({
            username: getUsername(),
            id,
        })
            .then(data => {
                this.setState({ movieAdded: data.added })
            })
            .catch(e => {
                console.log(e)
            });
    }

    // Get details for the specified movie
    getMovieDetails(id) {
        getMovieDetails({ id })
            .then(data => {
                this.setState({ movieDetails: data })
                console.log(data)
            })
            .catch(e => {
                console.log(e)
            });
    }

    // Method to show the add movie modal
    showModal = () => {
        this.setState({ showAddModal: true })
    }

    // Method to hide the add movie modal
    hideModal = () => {
        this.setState({ showAddModal: false })
    }

    // Callback function to update the data in the modal form
    updateModalForm = (data) => {
        this.setState(data)
    }

    // Method which is called when the user clicks "save" on the add movie modal
    saveModal = () => {
        // Set the movie as added and hide the modal
        this.setState({ movieAdded: true })
        this.hideModal()

        // Call the API to add the movie to the user's list
        addToList({
            username: getUsername(),
            token: getAccessToken(),
            movieId: this.state.movieId,
            rating: this.state.rating,
            review: this.state.review,
        })
            .catch(e => {
                console.log(e)
            });
    }

    // Method to remove a movie from a user's list
    removeMovie = () => {
        // Call the API to remove the movie form the user's list
        removeFromList({
            username: getUsername(),
            token: getAccessToken(),
            movieId: this.state.movieId,
        })
            .then(data => {
                // Update state accordingly
                this.setState({ movieAdded: false })
            })
            .catch(e => {
                console.log(e)
            });
    }

    render() {
        return (
            <>
                {/* Movie Detail Component - Shows all the details for the specified movie */}
                <MovieDetail data={this.state.movieDetails} />
                <Container className="mt-2" fluid>
                    <Row className="mt-2">
                        {/* Only show buttons if user is logged in */}
                        {isLoggedIn() &&
                            <Col xs={4}>
                                {this.state.movieAdded ?
                                    // If the movie has been added, show the added label and remove button
                                    <ButtonGroup>
                                        <Button variant="success" disabled>
                                            <Icon.CheckSquare /> Added
                                </Button>
                                        <Button variant="outline-danger" onClick={this.removeMovie}>
                                            <Icon.XSquare /> Remove
                                </Button>
                                    </ButtonGroup>
                                    :
                                    // If the movie has not been added, show the add button
                                    <Button variant="outline-dark" onClick={this.showModal}>
                                        <Icon.PlusSquare /> Add to List
                            </Button>
                                }
                            </Col>}
                    </Row>
                </Container>
                {/* Add Movie Modal - Shows when user clicks the "add to list" button */}
                <AddMovieModal
                    show={this.state.showAddModal}
                    hideModal={this.hideModal}
                    updateModalForm={this.updateModalForm}
                    saveModal={this.saveModal}
                    movieDetails={this.state.movieDetails}
                />
            </>
        )
    }
}

export default MovieDetailPage;