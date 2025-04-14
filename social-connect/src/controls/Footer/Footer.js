import React from "react";
import { Link } from "react-router-dom";
import { FaHome, FaPlusCircle, FaUsers } from "react-icons/fa";
import "./Footer.css";

const Footer = () => {
  return (
    <div className="footer">
      <Link to="/home">
        <FaHome size={24} color="goldenrod" />
      </Link>
      <Link to="/add-post">
        <FaPlusCircle size={38} color="goldenrod" />
      </Link>
      <Link to="/connections">
        <FaUsers size={24} color="goldenrod" />
      </Link>
    </div>
  );
};

export default Footer;
