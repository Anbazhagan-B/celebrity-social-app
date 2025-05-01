import { call, put, takeLatest } from "redux-saga/effects";
import axios from "axios";
import {
  FETCH_POSTS_SUCCESS,
  FETCH_POSTS_FAIL,
  FETCH_POSTS_REQUEST,
  ADD_POST_REQUEST,
  ADD_POST_SUCCESS,
  ADD_POST_FAIL,
} from "../Constants/postConstants";
import API_CONFIG from "../../config/apiConfig";

export function* fetchPostsSaga(action) {
  try {
    const { data } = yield call(
      axios.get,
      `${API_CONFIG.BASE_URL}/api/posts/explore/${action.payload}`
    );
    yield put({ type: FETCH_POSTS_SUCCESS, payload: data });
  } catch (error) {
    yield put({ type: FETCH_POSTS_FAIL, payload: "No post available" });
  }
}

export function* addPostSaga(action) {
  try {
    const formData = new FormData();
    formData.append("image", action.payload.image);
    formData.append("caption", action.payload.caption);
    formData.append("userId", action.payload.userId);

    const response = yield call(() =>
      axios.post(`${API_CONFIG.BASE_URL}/api/posts`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
    );

    yield put({ type: ADD_POST_SUCCESS, payload: response.data });
  } catch (error) {
    yield put({
      type: ADD_POST_FAIL,
      payload: error.response ? error.response.data : "Error adding post",
    });
    yield put({
      type: "SHOW_POPUP",
      payload: {
        error: error.response?.data?.error || "Add post failed",
        message: error.response?.data?.message || "Please try again.",
      },
    });
  }
}

export function* watchPostSagas() {
  yield takeLatest(FETCH_POSTS_REQUEST, fetchPostsSaga);
  yield takeLatest(ADD_POST_REQUEST, addPostSaga);
}
