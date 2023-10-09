import React, { useEffect, useState } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import { loadAllCategories } from "../services/category-service";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";

const CategorySideMenu = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    loadAllCategories()
      .then((res) => {
        setCategories(res);
      })
      .catch((e) => {
        console.log(e);
        toast.error("error occuerd while fetching categories");
      });
  }, []);
  return (
    <>
      <ListGroup>
        <ListGroupItem action="true" as={Link} to="/">
          All Blogs
        </ListGroupItem>
        {categories.length > 0 &&
          categories.map((cat) => (
            <ListGroupItem
              key={cat.id}
              as={Link}
              className="mt-1"
              action="true"
              to={`/categories/${cat.id}`}
            >
              {cat.title}
            </ListGroupItem>
          ))}
      </ListGroup>
    </>
  );
};

export default CategorySideMenu;
