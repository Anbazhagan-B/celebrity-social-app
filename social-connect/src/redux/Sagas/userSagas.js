import { call, put, takeLatest } from "redux-saga/effects";
import axios from "axios";
import {
  REGISTER_USER_REQUEST,
  REGISTER_USER_SUCCESS,
  REGISTER_USER_FAIL,
  LOGIN_USER_REQUEST,
  LOGIN_USER_SUCCESS,
  LOGIN_USER_FAIL,
} from "../Constants/actionConstants";
import API_CONFIG from "../../config/apiConfig";

function* registerUserSaga(action) {
  try {
    const formData = new FormData();
    formData.append("name", action.payload.name);
    formData.append("email", action.payload.email);
    formData.append("phoneNumber", action.payload.phoneNumber);
    formData.append("password", action.payload.password);
    formData.append("profileImage", action.payload.profileImage);

    const res = yield call(() =>
      axios.post(`${API_CONFIG.BASE_URL}/api/users/register`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
    );

    yield put({ type: REGISTER_USER_SUCCESS, payload: res.data });
  } catch (error) {
    yield put({ type: REGISTER_USER_FAIL, payload: error });
    yield put({
      type: "SHOW_POPUP",
      payload: {
        error: error.response?.data?.error || "Registration Failed",
        message: error.response?.data?.message || "Please try again.",
      },
    });
  }
}

function* loginUserSaga(action) {
  try {
    const res = yield call(
      axios.post,
      `${API_CONFIG.BASE_URL}/api/login/validateByEmail`,
      action.payload
    );
    yield put({ type: LOGIN_USER_SUCCESS, payload: res.data });
  } catch (error) {
    yield put({ type: LOGIN_USER_FAIL, payload: error });
    yield put({
      type: "SHOW_POPUP",
      payload: {
        error: error.response?.data?.error || "Login Failed",
        message: error.response?.data?.message || "Please try again.",
      },
    });
  }
}

export function* watchUserSagas() {
  yield takeLatest(REGISTER_USER_REQUEST, registerUserSaga);
  yield takeLatest(LOGIN_USER_REQUEST, loginUserSaga);
}
