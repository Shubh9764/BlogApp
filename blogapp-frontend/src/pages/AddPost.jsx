import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  Col,
  Container,
  Form,
  Row,
} from "react-bootstrap";
import { loadAllCategories } from "../services/category-service";
import JoditEditor from "jodit-react";
import { createPost } from "../services/post-service";
import {toast} from 'react-toastify'

const INITIAL_STATE = {
  title: "",
  content: "",
  categoryId: '-1',
};

const AddPost = () => {
  const [state, setState] = useState(INITIAL_STATE);
  const [categories, setCategories] = useState([]);
const [errors,setErrors] = useState({})
  const resetHandler = () => {
    setState(INITIAL_STATE);
  };
  const editor = useRef(null);
  const stateChangeHandler = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
    if(!!errors[e.target.name]){
      setErrors({
        ...errors,
        [e.target.name]:null
      })
    }
  };
  const contentFieldChange = (data) => {
    setState({
      ...state,
      content: data,
    });
    if(!!errors['content']){
      setErrors({
        ...errors,
        'content':null
      })
    }
  };

  const validateFormErrors = () => {
    const {title,content,categoryId}= state;
    const newErrors = {}
    console.log("validating")
    if(title === '')
      newErrors.title ="Title is Required"
    if(content === '')
      newErrors.content ="Content is Required"
    if(categoryId === '-1')
      newErrors.categoryId ="Category is Required"

    return newErrors
  }
  const submitHandler = (e) => {
    e.preventDefault()
    console.log(state);
    const formErrors = validateFormErrors()
    if(Object.keys(formErrors).length > 0){
      setErrors(formErrors)
      return
    }
    createPost(state).then((res ) => {
      console.log(res)
    })
    .catch((e) => {
      console.log(e)
      toast("Post creation Failed ")
    })
  };

  useEffect(() => {
    console.log(state);
  }, [state]);
  useEffect(() => {
    loadAllCategories()
      .then((res) => {
        setCategories(res);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);
  return (
    <Row>
      <Col sm={{ span: 6, offset: 3 }}>
        <Card style={{ marginTop: 25 }} className="shadow">
          <Card.Header as="h5" className="text-center">
            Post
          </Card.Header>
          <CardBody>
            <Form onSubmit={submitHandler}>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Post Title</Form.Label>
                <Form.Control
                  type="text"
                  name="title"
                  placeholder="Enter Title"
                  onChange={stateChangeHandler}
                  value={state.title}
                  isInvalid={errors.title?true:false}
                  />
                  <Form.Control.Feedback type="invalid">{errors.title}</Form.Control.Feedback>
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Content</Form.Label>
                <JoditEditor
                  ref={editor}
                  value={state.content}
                  tabIndex={1} // tabIndex of textarea
                  onChange={contentFieldChange}
                  />
                  <Form.Control.Feedback type="invalid">{errors.content}</Form.Control.Feedback>
              </Form.Group>
              <Form.Group className="mb-3" controlId="formBasicCheckbox">
                <Form.Label>Post Content</Form.Label>
                <Form.Select onChange={stateChangeHandler} name="categoryId" defaultValue={-1} isInvalid={errors.categoryId?true:false}>
                  <option value={-1}>
                    --Pick a car brand--
                  </option>
                  {categories.map((cat) => (
                    <option key={cat.id} value={cat.id}>
                      {cat.title}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.categoryId}</Form.Control.Feedback>
              </Form.Group>
              <Container className="text-center">
                <Button variant="primary" type="submit">
                  Post
                </Button>
                <Button
                  className="ms-2"
                  variant="danger"
                  type="reset"
                  onClick={resetHandler}
                >
                  Reset
                </Button>
              </Container>
            </Form>
          </CardBody>
        </Card>
      </Col>
    </Row>
  );
};

export default AddPost;
