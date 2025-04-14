import React from "react";
import { useSelector, useDispatch } from "react-redux";
import "./ErrorPopup.css";

const ErrorPopup = () => {
  const { show, title, message } = useSelector((state) => state.popup);
  const dispatch = useDispatch();

  if (!show) return null;

  return (
    <div className="popup-backdrop">
      <div className="popup">
        <h3>{title}</h3>
        <p>{message}</p>
        <button onClick={() => dispatch({ type: "HIDE_POPUP" })}>Close</button>
      </div>
    </div>
  );
};

export default ErrorPopup;
