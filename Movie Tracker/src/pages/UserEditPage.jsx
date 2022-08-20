import React, { Component } from 'react'
import { Button, Container, Form } from "react-bootstrap"

import { getAccessToken, getUsername } from "../services/auth.js"
import { getUser, updateUser } from "../services/UserService.js"

class UserEditPage extends Component {
    constructor(props) {
        super(props)

        // Default state values
        this.state = {
            passwordUpdated: false,
        }

        this.submitEdit = this.submitEdit.bind(this)
    }

    componentDidMount() {
        // Get user info as soon as the page loads 

        // Call API to get user info
        getUser({
            username: getUsername(),
            token: getAccessToken(),
        })
            .then(data => {
                // Update state with user info
                this.setState(data)
            })
            .catch(e => {
                console.log(e)
            });
    }

    submitEdit(e) {
        // Prevent the form from immediately refreshing
        e.preventDefault()

        // Call the API to update the user with new information
        updateUser({
            username: getUsername(),
            token: getAccessToken(),
            email: this.state.email,
            password: this.state.passwordUpdated ? this.state.password : null
        })
            .then(data => {
                // Bring user to the homepage if successfully updated
                window.location.replace("/")
            })
            .catch(e => {
                console.log(e)
            });
    }

    render() {
        return (
            <Container>
                <h3 className="App-title">Edit User Info</h3>
                {/* Form containing username (read-only), email, and password */}
                <Form onSubmit={this.submitEdit}>
                    <Form.Group>
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" placeholder="Enter Username" disabled defaultValue={this.state.username} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="text" placeholder="Enter Email" defaultValue={this.state.email} onChange={(e) => this.setState({ email: e.target.value })} />
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Enter Password" defaultValue="password" onChange={(e) => this.setState({ password: e.target.value, passwordUpdated: true })} />
                    </Form.Group>
                    <Button variant="secondary" type="submit">
                        Update Info
                    </Button>
                </Form>
            </Container>
        )
    }

}

export default UserEditPage