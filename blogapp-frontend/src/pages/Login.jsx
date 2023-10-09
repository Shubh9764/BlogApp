import React, { useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { doLogin, myAxios } from "../services/helper";
import { login } from "../services/user-service";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const INITIAL_STATE = {
  email:'',
    password:'',
}


const Login = () => {
  const [state,setState] =useState(INITIAL_STATE)
  const navigate = useNavigate();

  useEffect(() => {
    console.log(state)
  },[state])

  const changeHandler = (e) => {
    setState({
      ...state,
      [e.target.name]:e.target.value
    })
  }
  const resetHandler = () => {
    setState(INITIAL_STATE)
  }
  const submitHandler = (e) =>{
    e.preventDefault()
    login(state).then((res) => {
      console.log(res)
      doLogin(res,() => {
        console.log("data stored to localstorage")
        navigate('/user/dashboard')
      })
      toast.success("Login Successfull")
      
    })
    .catch((e) =>{
      console.log(e)
      if(e.response?.status == 400 || e.response?.status == 404)
      toast.error(e.response.data.message)
      else
       toast.error("Somethin went Wrong")
    })
  }

  return (
    <Container style={{marginTop:20}}>
      <Row>
        <Col sm={{ span: 6, offset: 3 }}>
          <Card >
            <Card.Header as="h5" className="text-center">Login</Card.Header>
            <Card.Body>
              <Form onSubmit={submitHandler}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control type="email" name="email"
                  value={state.email}
                  onChange={changeHandler}
                  placeholder="Enter email" />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password"
                  name="password"
                  value={state.password}
                  onChange={changeHandler}
                  placeholder="Password" />
                </Form.Group>
                <div className="text-center">
                <Button variant="success" type="submit">
                  Login
                </Button>
                <Button variant="secondary" type="reset"  className="ms-2" onClick={resetHandler}>
                  Reset
                </Button>
                </div>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
