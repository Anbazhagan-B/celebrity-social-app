import React from "react";
import { useSelector } from "react-redux";
import "./Header.css";

const Header = () => {
  const { title } = useSelector((state) => state.header);

  return (
    <div className="header">
      <h1>{title}</h1>
    </div>
  );
};

export default Header;
