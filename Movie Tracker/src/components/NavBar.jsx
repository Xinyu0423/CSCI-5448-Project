import React from "react";
import { Nav, Navbar, NavDropdown } from "react-bootstrap";
import * as Icon from "react-bootstrap-icons"

import { isLoggedIn, getUsername, deleteTokens } from '../services/auth.js';

const Log = () => {
    if (isLoggedIn()) {
        // If user is logged in, show dropdown with profile, user edit, and sign out buttons
        return (
            <Nav>
                <NavDropdown title={getUsername()} alignRight>
                    <NavDropdown.Item href={`/profile/${getUsername()}`}>View Profile</NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="/user/edit">Edit User</NavDropdown.Item>
                    <NavDropdown.Item onClick={() => {
                        deleteTokens();
                        window.location.replace("/")
                    }}>
                        Sign Out
                </NavDropdown.Item>
                </NavDropdown>
            </Nav>
        )
    } else {
        // If user is not logged in, show Register/Login buttons on the right side of the navbar
        return (
            <Nav>
                <Nav.Link href="/register">Register</Nav.Link>
                <Nav.Link href="/login">Login</Nav.Link>
            </Nav>
        )
    }
}

// Navbar component, shown at the top of every page
const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Navbar.Brand href='/'>
                <Icon.Film /> Movie Tracker
                </Navbar.Brand>
            <Nav>
                <Nav.Link href='/'>
                    Home
                    </Nav.Link>
                <Nav.Link href="/search">
                    Search
                    </Nav.Link>
                <Nav.Link href="/leaderboard">
                    Leaderboard
                    </Nav.Link>
            </Nav>
            <Navbar.Collapse className="justify-content-end">
                <Log />
            </Navbar.Collapse>
        </Navbar>
    )
}

export default NavBar;