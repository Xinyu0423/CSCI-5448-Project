import React from "react";

import { Col, Container, Image, Row, ListGroup } from "react-bootstrap"

// Component used on the MovieDetailPage, shows a lot of detailed information based on the movie inputted
const MovieDetail = ({ data }) => {
    return (
        <Container className="mt-2" fluid>
            <Row>
                <Col xs={4}>
                    <Image src={data.poster} rounded fluid />
                </Col>
                <Col>
                    <h3>{data.title} ({data.releaseYear})</h3>
                    <ListGroup variant="flush">
                        <ListGroup.Item>
                            <b>Release Date:</b>  {data.releaseDate}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Runtime:</b> {data.runtime}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Rated:</b> {data.rated}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Director:</b> {data.director}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Writer:</b> {data.writer}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Cast:</b> {data.casts}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Genre:</b> {data.genre}
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <b>Plot:</b>
                            <br />
                            {data.plot}
                        </ListGroup.Item>
                    </ListGroup>
                </Col>
            </Row>
        </Container>
    )
}

export default MovieDetail;