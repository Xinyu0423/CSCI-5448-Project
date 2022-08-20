import React, { Component } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap"

import { isLoggedIn, setAccessToken, setUsername } from '../services/auth.js'
import { registerUser } from '../services/UserService.js'

class RegisterPage extends Component {
    constructor(props) {
        super(props)

        // Set default values for the state
        this.state = {
            username: "",
            email: "",
            password: "",
            invalid: false,
        }
        this.submitRegister = this.submitRegister.bind(this)
    }

    submitRegister(e) {
        // Prevent the page from refreshing immediately
        e.preventDefault()

        // Call the user registration API
        registerUser({
            username: this.state.username,
            password: this.state.password,
            email: this.state.email,
        })
            .then(data => {
                // Set user authentication info locally
                setAccessToken(data.token)
                setUsername(data.username)

                // Navigate user to main page if they successfully are logged in
                if (isLoggedIn()) {
                    window.location.replace("/")
                }

            })
            .catch((e) => this.setState({ invalid: true }));
    }

    render() {
        return (
            <>
                <h3 className="App-title">User Registration</h3>
                <Container>
                    {this.state.invalid &&
                        // Error message for registering a duplicate user
                        <Alert variant="danger" onClose={() => this.setState({ invalid: false })} dismissible>
                            User Already Exists.
                </Alert>}
                    {/* Form with username/email/password for user registration */}
                    <Form onSubmit={this.submitRegister}>
                        <Form.Group>
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" placeholder="Enter Username" onChange={(e) => this.setState({ username: e.target.value })} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" placeholder="Enter Email" onChange={(e) => this.setState({ email: e.target.value })} />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" placeholder="Enter Password" onChange={(e) => this.setState({ password: e.target.value })} />
                        </Form.Group>
                        <Button variant="secondary" type="submit">
                            Register
                    </Button>
                    </Form>
                </Container>
            </>
        )
    }
}

export default RegisterPage;