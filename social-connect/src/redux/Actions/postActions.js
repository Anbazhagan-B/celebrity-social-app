import {
  FETCH_POSTS_REQUEST,
  ADD_POST_REQUEST,
  RESET_POST_SUCCESS_NAVIGATE,
} from "../Constants/postConstants";

export const fetchPosts = (postData) => ({
  type: FETCH_POSTS_REQUEST,
  payload: postData,
});

export const addPost = (postData) => ({
  type: ADD_POST_REQUEST,
  payload: postData,
});

export const resetPostSuccessNavigate = () => ({
  type: RESET_POST_SUCCESS_NAVIGATE,
});
