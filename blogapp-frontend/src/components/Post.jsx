import React from 'react'
import { Button, Card, CardBody, CardText } from 'react-bootstrap'
import { Link } from 'react-router-dom'

const Post = ({post = { title:"Default Title",content:'default'}}) => {
  return (
    <Card className='border-0 shadow mt-3'>
        <CardBody>
            <h1>{post.title}</h1>
            <CardText>{post.content.substring(0,10)}....</CardText>
            <Link to={`/posts/${post.postId}`} className='btn btn-secondary'>Read More</Link>
        </CardBody>
    </Card>
  )
}

export default Post
