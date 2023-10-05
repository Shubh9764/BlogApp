import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { signup } from "../services/user-service";
import {toast} from 'react-toastify'


const INITIAL_STATE = {
  name:'',
  email:'',
  password:'',
  confirmPassword:'',
  about:'',
}

const Signup = () => {
  const [state,setState] =useState(INITIAL_STATE)
  const [error,setError] = useState({
    errors:{}
  })
  const handelChange = (e) => {
    setState({
      ...state,
      [e.target.name]:e.target.value
    })
  }
  const resetHandler = () => {
    setState(INITIAL_STATE)
  }
  useEffect(() =>{
    console.log(state)
  },[state])
  useEffect(() =>{
    console.log(error)
  },[error])

  const submitHandler = (e) => {
    e.preventDefault()
    const {confirmPassword,...rest} = state;
    signup(rest).then((res) =>{
      console.log(res)
      toast.success("user register successfully")
    })
    .catch(e => {
      console.log(e)
      toast.error("user register failed")
      setError({
        errors:e?.response?.data,
      })
    })
  }
  return (
    <Container style={{marginTop:20}} className="lh-1">
      <Row>
        <Col sm={{ span: 6, offset: 3 }}>
          <Card>
            <Card.Header as="h4">SignUp</Card.Header>
            <Card.Body >
              <Form onSubmit={submitHandler} noValidate>
                <Form.Group className="mb-3" controlId="name">
                  <Form.Label>Your Name</Form.Label>
                  <Form.Control type="text" name="name" placeholder="Enter email"
                  onChange={handelChange} 
                  value={state.name}
                  required
                  isInvalid={error.errors?.name?true:false}
                  />
                  <Form.Control.Feedback type="invalid">{error.errors?.name}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="email">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control type="email" name='email' placeholder="Enter email" 
                  onChange={handelChange} 
                  value={state.email}
                  required
                  isInvalid={error.errors?.email?true:false}
                  />
                  <Form.Control.Feedback type="invalid">{error.errors?.email}</Form.Control.Feedback>
                </Form.Group>

                <Form.Group className="mb-3" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" name="password" placeholder="Password"
                  onChange={handelChange} 
                  value={state.password}
                  required
                  isInvalid={error.errors?.password?true:false}
                  />
                  <Form.Control.Feedback type="invalid">{error.errors?.password}</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="confirmPassword">
                  <Form.Label>Confirm Password</Form.Label>
                  <Form.Control type="password" name="confirmPassword" placeholder="Password" 
                  onChange={handelChange}
                  value={state.confirmPassword}
                  required
                  isInvalid={error.errors?.confirmPassword?true:false}
                  />
                  <Form.Control.Feedback type="invalid">confirmPassword Should match Password</Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="about">
                  <Form.Label>About</Form.Label>
                  <Form.Control as="textarea" name="about"  rows={3} placeholder="Password" 
                  onChange={handelChange}
                  value={state.about}
                  required
                  isInvalid={error.errors?.about?true:false}
                  />
                  <Form.Control.Feedback type="invalid">{error.errors?.about}</Form.Control.Feedback>
                </Form.Group>
                <Container className="text-center">
                  <Button variant="primary" type="submit">
                    Register
                  </Button>
                  <Button variant="secondary" type="reset" className="ms-2" onClick={resetHandler}>
                    Reset
                  </Button>
                </Container>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Signup;
