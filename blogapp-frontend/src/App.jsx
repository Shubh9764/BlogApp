import { useState } from "react";
import About from "./pages/About";
import Base from "./components/Base";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Services from "./pages/Services";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import UserDashboard from "./pages/UserDashboard";
import PrivateRoute from "./components/PrivateRoute";
import ProfileInfo from "./pages/ProfileInfo";
import CustomNavBar from "./components/CustomNavBar";

import PostPage from "./pages/PostPage";
import Categories from "./pages/Categories";
function App() {
  return (
    <>
      <Router>
        <CustomNavBar/>
          <Routes>
            <Route path="/about" element={<About />} />
            <Route path="/" element={<Home />} />
            <Route path="/posts/:postId" element={<PostPage />} />
            <Route path="/categories/:categoryId" element={<Categories />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/services" element={<Services />} />
            <Route path="/user" element={<PrivateRoute />}>
              <Route path="dashboard" element={<UserDashboard />} />
              <Route path="profile-info" element={<ProfileInfo />} />
            </Route>
          </Routes>
        <ToastContainer />
      </Router>
    </>
  );
}

export default App;
