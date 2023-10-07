import React, { useEffect, useState } from 'react'
import {Navbar,Nav, NavDropdown,Container} from 'react-bootstrap'
import { Link, useNavigate } from 'react-router-dom'
import { doLogout, getUser, isLoggedIn } from '../services/helper'

const CustomNavBar = () => {
  const navigate = useNavigate(isLoggedIn())
  const [login,setLogin] =useState(false)
  const [user,setUser] = useState(undefined)


  useEffect(() => {
    console.log(isLoggedIn())
    setLogin(isLoggedIn())
    setUser(getUser())
  },[login])

  const logout = () => {
    doLogout(() =>{
      setLogin(false)
      navigate('/')
    })
  }

  return (
    <Navbar collapseOnSelect expand="lg"  bg="primary" data-bs-theme="dark" 
    className='px-5'>
        <Navbar.Brand as={Link} to='/'>BlogApp</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse  id="responsive-navbar-nav">
          <Nav className="me-auto">
          <Nav.Link as={Link} to='/'>
                New Feed
            </Nav.Link>
            <Nav.Link as={Link} to='/services'>
                Services
            </Nav.Link>
            <Nav.Link as={Link} to='/about'>About</Nav.Link>
          </Nav>
          <Nav>
            {
              login ? (
                <>
                <Nav.Link onClick={logout}>Logout</Nav.Link>
                <Nav.Link as={Link} to='/user/profile-info'>Profile</Nav.Link>
                <Nav.Link as={Link} to='/user/dashboard' >{user.email}</Nav.Link>
                </>
              ) :
              (
                <>
                <Nav.Link as={Link} to='/login'>Login</Nav.Link>
                <Nav.Link as={Link} to='/signup'>SignUp</Nav.Link>
                </>
              )
            }
        
            
          </Nav>
        </Navbar.Collapse>
    </Navbar>
  )
}

export default CustomNavBar
