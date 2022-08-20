import React from "react"
import { Col, Container, Image, ListGroup, Row } from "react-bootstrap"

// Component for a single result row on the search page.
// Used to display a small amount of movie info, leads to movie detail page when clicked
const MovieSearchItem = ({ data }) => {
    return (
        <ListGroup.Item>
            <Container>
                <Row noGutters>
                    <Col xs={4}>
                        <Image src={data.poster} fluid className="w-50" />
                    </Col>
                    <Col>
                        <a href={`/movie/${data.movieID}`}>
                            <h5>{data.title}</h5>
                        </a>
                        <label><b>Release Year: </b> {data.releaseYear}</label>
                    </Col>
                </Row>
            </Container>
        </ListGroup.Item>
    )
}

export default MovieSearchItem