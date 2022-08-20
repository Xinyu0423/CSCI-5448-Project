import React, { Component } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap";

import { isLoggedIn, setAccessToken, setUsername } from '../services/auth.js'
import { loginUser } from '../services/UserService.js'

class LoginPage extends Component {
    constructor(props) {
        super(props)

        // Set default values for the state
        this.state = {
            username: "",
            password: "",
            invalid: false,
        }

        this.submitLogin = this.submitLogin.bind(this)
    }

    submitLogin(e) {
        // Prevent the page from refreshing immediately
        e.preventDefault()

        // Call the API to login
        loginUser({
            username: this.state.username,
            password: this.state.password,
        })
            .then(data => {
                // Update access token and username in local storage
                setAccessToken(data.token)
                setUsername(data.username)

                // Navigate user to homepage if they successfully log in
                if (isLoggedIn()) {
                    window.location.replace("/")
                }
            })
            .catch(e => {
                // Show error message if invalid login
                this.setState({ invalid: true })
            });
    }

    render() {
        return (
            <>
                <h3 className="App-title">User Login</h3>
                <Container>
                    {this.state.invalid &&
                        // Error message for invalid login
                        <Alert variant="danger" onClose={() => this.setState({ invalid: false })} dismissible>
                            Invalid Username or Password.
                    </Alert>}
                    {/* Form containing fields for username/password to login */}
                    <Form onSubmit={this.submitLogin}>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" placeholder="Enter Username" onChange={(e) => this.setState({ username: e.target.value })} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Enter Password" onChange={(e) => this.setState({ password: e.target.value })} />
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Login
                        </Button>
                    </Form>
                </Container>
            </>
        )
    }
}

export default LoginPage;