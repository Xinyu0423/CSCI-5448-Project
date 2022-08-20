import React, { Component } from "react";
import { Button, ButtonGroup, Container, Row, Col } from "react-bootstrap";
import { Table } from 'react-bootstrap'

import { getProfileLeaderboard } from "../services/ProfileService.js"

class LeaderboardPage extends Component {
    constructor(props) {
        super(props);

        // Default state values
        this.state = {
            leaderboard: [],
            sortBy: 'count',
        }
        this.getLeaderboard = this.getLeaderboard.bind(this)
    }

    componentDidMount() {
        // Get leaderboard values on page load
        this.getLeaderboard()
    }

    getLeaderboard() {
        // Call API to get leaderboard information
        getProfileLeaderboard()
            .then(data => {
                // Update state with leaderboard information
                this.setState({ leaderboard: data })
            })
            .catch(e => {
                console.log(e)
            });
    }

    // Sort data based on the specified key
    sortedData = (data, sort) => {
        if (sort === "watch time") {
            return data.sort((a1, a2) => a1[0] < a2[0] ? 1 : -1)
        } else if (sort === "count") {
            return data.sort((a1, a2) => a1[1] < a2[1] ? 1 : -1)
        }
        return data.sort((a1, a2) => a1[3] < a2[3] ? 1 : -1)
    }

    render() {
        return (
            <Container className="mt-2">
                <h3 className="App-title">User Leaderboard</h3>
                <Row>
                    {/* Buttons to sort the leaderboard by count and watch time */}
                    <Col style={{ display: "flex", justifyContent: "flex-end" }}>
                        <ButtonGroup>
                            <Button variant="outline-dark" onClick={() => this.setState({ sortBy: "count" })}>Count</Button>
                            <Button variant="outline-dark" onClick={() => this.setState({ sortBy: "watch time" })}>Watch Time</Button>
                        </ButtonGroup>
                    </Col>
                </Row>
                <Row>
                    {this.state.leaderboard.length
                        // Table of all users 
                        ? <Table bordered hover>
                            <thead>
                                <tr>

                                    <th>Username</th>
                                    <th>Total Watch Time</th>
                                    <th>Total Movie Count</th>
                                    <th>Average Rating</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.sortedData(this.state.leaderboard, this.state.sortBy).map((item, index) => (
                                    <tr key={index}>
                                        <td>
                                            {/* Link to user profile */}
                                            <a href={`/profile/${item[3]}`}>{item[3]}</a>
                                        </td>
                                        <td>
                                            {/* Time watched */}
                                            {parseInt(item[0] / 60)} hours {item[0] % 60} mins
                                </td>
                                        <td>
                                            {/* Total movie count */}
                                            {item[1]}
                                        </td>
                                        <td>
                                            {/* Average rating */}
                                            {Math.round(item[2] * 100) / 100}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                        : <i>Unable to load user leaderboard data. Try again later.</i>
                    }
                </Row>
            </Container>
        )
    }
}

export default LeaderboardPage;
