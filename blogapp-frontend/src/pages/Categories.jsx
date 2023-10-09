import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getPostsByCategories } from "../services/post-service";
import Post from "../components/Post";
import { Col, Container, Row } from "react-bootstrap";
import CategorySideMenu from "../components/CategorySideMenu";

const Categories = () => {
  const [posts, setPosts] = useState([]);
  const { categoryId } = useParams();
  useEffect(() => {
    console.log(categoryId);
    getPostsByCategories(categoryId)
      .then((res) => {
        console.log(res);
        setPosts(res);
      })
      .catch((e) => {
        console.log(e);
      });
  }, [categoryId]);
  return (
    <div>
      <Container className="mt-3">
        <Row>
          <Col md={2} className="pt-3">
            <CategorySideMenu />
          </Col>
          <Col md={10}>
            {posts.length > 0 && posts.map((post) => <Post key={post.postId} post={post} />)}
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Categories;
