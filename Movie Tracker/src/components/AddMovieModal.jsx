import React from "react"

import { Button, Form, Modal } from "react-bootstrap"

import { ratingsList } from "../services/ratings.js"

// This modal pops up when a user clicks the "Add to List" button
// It allows a user to select a movie rating, write a review, and then submit
const AddMovieModal = ({ show, hideModal, updateModalForm, saveModal, movieDetails }) => {
    return (
        <Modal size="lg" show={show} onHide={hideModal}>
            <Modal.Header closeButton>
                <Modal.Title>{movieDetails.title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group>
                        <Form.Label>Rating</Form.Label>
                        <Form.Control as="select" defaultValue={null} onChange={(e) => updateModalForm({ rating: e.target.value })}>
                            <option key="default" value={null}>Choose a Rating...</option>
                            {ratingsList().map((rating, index) => <option key={`rating-${index + 1}`} value={rating.value}>{rating.info}</option>)}
                        </Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Review</Form.Label>
                        <Form.Control type="text" as="textarea" onChange={(e) => updateModalForm({ review: e.target.value })} />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={hideModal}>Cancel</Button>
                <Button variant="success" onClick={saveModal}>Save</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default AddMovieModal