import React, { useEffect, useState } from "react";
import "./Register.css";
import { useDispatch, useSelector } from "react-redux";
import { registerUser } from "../../redux/Actions/userAction";
import { useNavigate } from "react-router-dom";
import { FaEdit, FaUser } from "react-icons/fa";

function UserRegistrationPage() {
  const [profileImage, setProfileImage] = useState(null);
  const [preview, setPreview] = useState(null);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { userInfo } = useSelector((state) => state.user);

  useEffect(() => {
    if (userInfo) {
      navigate("/home");
    }
  }, [userInfo]);

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setProfileImage(file);
      const reader = new FileReader();
      reader.onloadend = () => setPreview(reader.result);
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const userData = {
      name,
      email,
      phoneNumber,
      password,
      profileImage,
    };
    dispatch(registerUser(userData));
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit} className="register-form">
        <h2>Register as user</h2>

        <div className="profile-pic-wrapper">
          {preview ? (
            <img src={preview} alt="profile" className="profile-pic" />
          ) : (
            <div className="profile-placeholder">
              <FaUser className="profile-icon" />
            </div>
          )}
          <label htmlFor="profile-upload" className="edit-icon">
            <FaEdit />
          </label>
          <input
            id="profile-upload"
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            hidden
          />
        </div>
        <div className="input-container">
          <input
            type="text"
            className="register-input"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
          <input
            type="email"
            className="register-input"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="text"
            className="register-input"
            placeholder="Phone Number"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
          />
          <input
            type="password"
            className="register-input"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit" className="register-button">
            Register
          </button>
        </div>
      </form>
    </div>
  );
}

export default UserRegistrationPage;
