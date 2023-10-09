import React, { useEffect, useState } from 'react'
import { getAllPosts } from '../services/post-service'
import { Col, Pagination, Row } from 'react-bootstrap'
import Post from './Post'
import { useParams } from 'react-router-dom'
import InfiniteScroll from 'react-infinite-scroll-component';
const NewFeed = () => {
    const [postContent,setPostContent] = useState({
      content:[],
      totalPages:'',
      totalElements:'',
      pageSize : '',
      pageNumber : '',
      lastPage : ''
    })
    const [currentPage,setCurrentPage] = useState(0)
  useEffect(() => {
    
    changePage(currentPage)
  },[currentPage])
  const changePage = (pageNumber,pageSize =5) => {
    console.log(pageNumber)
    getAllPosts(pageNumber,pageSize).then(
        (res) => {
          console.log(res)
          setPostContent({
            content:  [...postContent.content, ...res.content],
            totalPages:res.totalPages,
            totalElements:res.totalElements,
            pageSize : res.pageSize,
            pageNumber : res.pageNumber,
            lastPage : res.lastPage

          })
        }
      ).catch( (e) => {
        console.log(e)
      })
      // window.scroll(0,0)
}

const changePageInfiniteScroll = () => {
  console.log("page changed")
  setCurrentPage(currentPage + 1)
  
}
  return (
    <div className="container-fluid">
        <Row>
            <Col md={{span:12}}>
                <h1>BlogsCount {postContent?.totalElements}</h1>
                
           <InfiniteScroll
           dataLength={postContent.content ? postContent.content.length : 5}
           next={changePageInfiniteScroll}
           hasMore={!postContent.lastPage}
           >
          { postContent.totalElements > 0 && postContent.content.map((post) => <Post key={post.postId}  post={post}/>)
           }
           </InfiniteScroll>
           {/* {postContent?.totalPages>0 &&
           <Pagination>
           <Pagination.First disabled={postContent?.pageNumber === 0} onClick={() => changePage(0)} >first</Pagination.First>
           <Pagination.Prev disabled={postContent?.pageNumber === 0}  onClick={() => changePage(postContent.pageNumber - 1)}>prev</Pagination.Prev>
           {[...Array(postContent.totalPages)].map((item,index) =>
             <Pagination.Item  key={index} active={index === postContent.pageNumber } onClick={() => changePage(index)}>{index+1}</Pagination.Item>
           )
           }
           <Pagination.Next disabled={postContent.lastPage} onClick={() => changePage(postContent.pageNumber + 1)}>next</Pagination.Next>
           <Pagination.Last disabled={postContent.lastPage} onClick={() => changePage(postContent.totalPages - 1)}>last</Pagination.Last>
           </Pagination>
           } */}
           


            </Col>
           
        </Row>
    </div>
  )
}

export default NewFeed
