import {
  REGISTER_USER_REQUEST,
  LOGIN_USER_REQUEST,
} from "../Constants/actionConstants";

export const registerUser = (userData) => ({
  type: REGISTER_USER_REQUEST,
  payload: userData,
});

export const loginUser = (userData) => ({
  type: LOGIN_USER_REQUEST,
  payload: userData,
});
