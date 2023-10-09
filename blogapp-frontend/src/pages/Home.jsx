import React, { useEffect } from 'react'
import NewFeed from '../components/NewFeed'
import { Col, Container, Row } from 'react-bootstrap'
import CategorySideMenu from '../components/CategorySideMenu'

const Home = () => {
 
  return (
    <div>
      {/* <NewFeed/>
       */}
       <Container className='mt-3'>
       <Row>
        <Col md={2} className='pt-3'>
        <CategorySideMenu/>
        </Col>
        <Col md={10}>
        <NewFeed/>
        </Col>
       </Row>
       </Container>
    </div>
  )
}

export default Home
