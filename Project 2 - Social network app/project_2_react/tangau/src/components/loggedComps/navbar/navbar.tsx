import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Form from 'react-bootstrap/Form';
import FormControl from 'react-bootstrap/FormControl';
import Button from 'react-bootstrap/Button';
import ButtonMUI from '@material-ui/core/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import './navbar-style.css'


const useStyles = makeStyles({
    root: {
      position: "sticky",
      top: "0",
      zIndex: 10,
    },
  
    title: {
      fontSize: 14
    },
    pos: {
      marginBottom: 12
    }
  });



const NavBar = () => {

    const classes = useStyles();

    return(
        <>
            {/* bg primary or light */}
            <Navbar className={classes.root} bg="light" variant="light">
                <Container className="containerClass">
                <Navbar.Brand href="#home">Tangau</Navbar.Brand>
                <Nav className="me-auto">
                <Nav.Link href="#home">Live Feed</Nav.Link>
                <Nav.Link href="#features">My Profile</Nav.Link>
                </Nav>
                <Form className="d-flex">
                    <FormControl
                        type="search"
                        placeholder="Search"
                        className="mr-2"
                        aria-label="Search"
                    />
                    <Button variant="outline-secondary">Search</Button>
                </Form>
                </Container>
                <ButtonMUI 
                variant="contained" 
                color="secondary">
                  logout
                </ButtonMUI>
            </Navbar>


        </>
    );

}

export default NavBar;