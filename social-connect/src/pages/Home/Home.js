import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchPosts } from "../../redux/Actions/postActions";
import Post from "../../controls/Post/Post";
import {
  connectUser,
  avoidUser,
} from "../../redux/Actions/relationshipActions";
import { useNavigate } from "react-router-dom";
import "./Home.css";
import { FaPlusCircle, FaUsers } from "react-icons/fa";
import { FaUser } from "react-icons/fa6";
import { setHeaderTitle } from "../../redux/Actions/headerActions";

function HomePage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { posts, loading, error } = useSelector((state) => state.post);

  const { userInfo } = useSelector((state) => state.user);

  useEffect(() => {
    if (userInfo && userInfo.id > 0) {
      dispatch(fetchPosts(userInfo.id));
    }
  }, [userInfo]);

  const addPost = () => {
    navigate("/add-post");
  };

  const openChat = () => {
    navigate("/connections");
  };
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  dispatch(setHeaderTitle("Home"));
  return (
    <div className="home-container">
      {posts &&
        posts.map((post) => (
          <Post
            key={post.id}
            post={post}
            onConnect={() => dispatch(connectUser(userInfo.id, post.userId))}
            onReject={() => dispatch(avoidUser(userInfo.id, post.userId))}
          />
        ))}
    </div>
  );
}

export default HomePage;
