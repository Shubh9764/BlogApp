import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import { addComment, getPostById } from '../services/post-service';
import { Button, Card, CardBody, CardText, Col, Container, Form, InputGroup, Row } from 'react-bootstrap';
import { BASE_URL } from '../services/helper';

const PostPage = () => {
    const [post,setPost] = useState({});
    const [comment,setComment] = useState({
      content:'',
    });
    const {postId} = useParams();
    useEffect(()=>{
        getPostById(postId).then((res) => {
            setPost(res)
            console.log(res)
        })
        .catch((e) =>{
            console.log(e)
        })
    },[])
    useEffect(()=>{
     console.log(comment)
  },[comment])
    const changeHandler = (e) => {
      setComment({
        content:e.target.value
      })
    }
    const submitHandler = () => {
      console.log(comment)
      addComment(post.postId,comment).then((res) => {
        console.log(res);
        setPost({...post,
          comments:[...post.comments,res]
        })
      })
      .catch( (e) => {
        console.log(e);
      })
    }
  return (
    <Container className='mt-4'>
      <Row>
        <Col sm={{span:10,offset:1}}>
        <Card className='border-0 shadow mt-3'>
        <CardBody>
            
            <CardText>Posted By <b>{post?.user?.name}</b> on <b>{new Date(post?.addedDate).toDateString()}</b></CardText>
            <h1>{post.title}</h1>
            <div className='image-container shadow' style={{maxWidth:'50%'}}>
              <img src={`${BASE_URL}/api/post/image/download/`+postId} alt="img" />
            </div>
            <CardText className='mt-5' dangerouslySetInnerHTML={{__html:post.content}}></CardText>
        </CardBody>
    </Card>
    
        </Col>
        <Col sm={{span:8,offset:3}} className='mt-3'>
        <h3>Comments({post.comments?.length})</h3>
       {post.comments?.map((comment)  => (
        <Card id={comment.id} className='border-dark shadow mt-3'>
        <CardBody>
          <CardText>{comment.content}</CardText>
        </CardBody>
    </Card>
        ))}
        <Card  className='border-dark shadow mt-3'>
        <CardBody>
        <InputGroup>
        <InputGroup.Text>Comment</InputGroup.Text>
        <Form.Control as="textarea" aria-label="With textarea" name='content' required value={comment.content} onChange={changeHandler}  />
        <Button variant="primary" type="submit" onClick={submitHandler}>
          Submit
          </Button>
      </InputGroup>
        </CardBody>
    </Card>
        </Col>
      </Row>
    </Container>
  )
}

export default PostPage
