import React, { useEffect, useState } from "react";
import "./Login.css";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../../redux/Actions/userAction";
import { useNavigate } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { userInfo } = useSelector((state) => state.user);

  useEffect(() => {
    if (userInfo) {
      navigate("/home");
    }
  }, [userInfo]);

  const handleLogin = (event) => {
    event.preventDefault();
    dispatch(loginUser({ email, password }));
  };

  const handleRegisterClick = (event) => {
    event.preventDefault();
    navigate("/register");
  };

  return (
    <div className="form-container">
      <form className="login-form">
        <h2>Login</h2>
        <div>
          <input
            type="text"
            className="login-input"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <input
            type="password"
            className="login-input"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <button type="submit" onClick={handleLogin} className="login-button">
            Log In
          </button>
          <button
            type="submit"
            onClick={handleRegisterClick}
            className="login-button"
          >
            Register
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;
