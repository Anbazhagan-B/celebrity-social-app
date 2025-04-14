import {
  REGISTER_USER_REQUEST,
  REGISTER_USER_SUCCESS,
  REGISTER_USER_FAIL,
  LOGIN_USER_REQUEST,
  LOGIN_USER_SUCCESS,
  LOGIN_USER_FAIL,
} from "../Constants/actionConstants";

const initialState = {
  userInfo: null,
  loading: false,
  error: null,
};

export default function userReducer(state = initialState, action) {
  switch (action.type) {
    case REGISTER_USER_REQUEST:
    case LOGIN_USER_REQUEST:
      return { ...state, loading: true, error: null };
    case REGISTER_USER_SUCCESS:
    case LOGIN_USER_SUCCESS:
      return { ...state, loading: false, userInfo: action.payload };
    case REGISTER_USER_FAIL:
    case LOGIN_USER_FAIL:
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
}
