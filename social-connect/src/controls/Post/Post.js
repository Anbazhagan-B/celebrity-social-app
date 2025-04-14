import React from "react";
import "./Post.css";

function Post({ post, onConnect, onReject }) {
  return (
    <div className="postContainer">
      <label className="post-title">{post.userName}</label>
      <img className="post-img" src={post.imageUrl} alt="postImg" />
      <div className="post-btns-div">
        <button className="post-btn" onClick={onConnect}>
          Connect
        </button>
        <button className="post-btn reject" onClick={onReject}>
          Reject
        </button>
      </div>
    </div>
  );
}

export default Post;
