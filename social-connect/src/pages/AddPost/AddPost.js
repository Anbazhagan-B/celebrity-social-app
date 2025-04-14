import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  addPost,
  resetPostSuccessNavigate,
} from "../../redux/Actions/postActions";
import "./AddPost.css";
import { useNavigate } from "react-router-dom";
import { setHeaderTitle } from "../../redux/Actions/headerActions";
import { FaUser } from "react-icons/fa";

const AddPost = () => {
  const [image, setImage] = useState(null);
  const [caption, setCaption] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { userInfo } = useSelector((state) => state.user);
  const { successNavigate } = useSelector((state) => state.post);

  useEffect(() => {
    dispatch(setHeaderTitle("Add Post"));
  }, [dispatch]);

  useEffect(() => {
    if (successNavigate) {
      navigate("/home");
      dispatch(resetPostSuccessNavigate());
    }
  }, [successNavigate, dispatch, navigate]);

  const handleImageData = (event) => {
    if (event?.target?.files?.[0]) {
      const fileData = event.target.files[0];
      setImage(fileData);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (userInfo) {
      const postData = {
        userId: userInfo.id,
        image: image,
        caption: caption,
      };
      dispatch(addPost(postData));
    }
  };

  return (
    <div className="add-post-container">
      <form className="add-post-form" onSubmit={handleSubmit}>
        <div className="image-preview-wrapper">
          {image ? (
            <img
              className="uploaded-img"
              src={URL.createObjectURL(image)}
              alt="Preview"
            />
          ) : (
            <div className="default-image-preview">
              <FaUser className="default-user-icon" />
            </div>
          )}
        </div>
        <input
          type="file"
          accept="image/*"
          onChange={handleImageData}
          required
        />
        <input
          type="text"
          placeholder="Caption"
          value={caption}
          onChange={(e) => setCaption(e.target.value)}
          required
        />
        <button type="submit">Submit Post</button>
      </form>
    </div>
  );
};

export default AddPost;
