import {
  FETCH_POSTS_REQUEST,
  FETCH_POSTS_SUCCESS,
  FETCH_POSTS_FAIL,
  POST_CONNECT,
  POST_REJECT,
  RESET_POST_SUCCESS_NAVIGATE,
  ADD_POST_SUCCESS,
} from "../Constants/postConstants";

const initialState = {
  posts: [],
  loading: false,
  error: null,
  successNavigate: false,
};

export default function postReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_POSTS_REQUEST:
      return { ...state, loading: true, successNavigate: false };
    case FETCH_POSTS_SUCCESS:
      return {
        ...state,
        loading: false,
        posts: action.payload,
        successNavigate: false,
      };
    case FETCH_POSTS_FAIL:
      return {
        ...state,
        loading: false,
        error: action.payload,
        successNavigate: false,
      };
    case RESET_POST_SUCCESS_NAVIGATE:
      return { ...state, successNavigate: false };
    case ADD_POST_SUCCESS:
      return { ...state, successNavigate: true };
    case POST_CONNECT:
    case POST_REJECT:
      return state;
    default:
      return state;
  }
}
