import { call, put, takeLatest } from "redux-saga/effects";
import axios from "axios";
import {
  CONNECT_USER_REQUEST,
  CONNECT_USER_SUCCESS,
  CONNECT_USER_FAIL,
  AVOID_USER_REQUEST,
  AVOID_USER_SUCCESS,
  AVOID_USER_FAIL,
} from "../Constants/relationshipConstants";
import API_CONFIG from "../../config/apiConfig";

function* connectUserSaga(action) {
  try {
    const { fromUserId, toUserId } = action.payload;
    const response = yield call(
      axios.post,
      `${API_CONFIG.BASE_URL}/api/relationships/connect`,
      { fromUserId, toUserId }
    );
    yield put({ type: CONNECT_USER_SUCCESS, payload: response.data });
  } catch (error) {
    yield put({
      type: CONNECT_USER_FAIL,
      payload: error.response ? error.response.data : error.message,
    });
    yield put({
      type: "SHOW_POPUP",
      payload: {
        error: error.response?.data?.error || "Connect Failed",
        message: error.response?.data?.message || "Please try again.",
      },
    });
  }
}

function* avoidUserSaga(action) {
  try {
    const { fromUserId, toUserId } = action.payload;
    const response = yield call(
      axios.post,
      `${API_CONFIG.BASE_URL}/api/relationships/avoid`,
      { fromUserId, toUserId }
    );
    yield put({ type: AVOID_USER_SUCCESS, payload: response.data });
  } catch (error) {
    yield put({
      type: AVOID_USER_FAIL,
      payload: error.response ? error.response.data : error.message,
    });
    yield put({
      type: "SHOW_POPUP",
      payload: {
        error: error.response?.data?.error || "Reject Failed",
        message: error.response?.data?.message || "Please try again.",
      },
    });
  }
}

export function* watchRelationshipSagas() {
  yield takeLatest(CONNECT_USER_REQUEST, connectUserSaga);
  yield takeLatest(AVOID_USER_REQUEST, avoidUserSaga);
}
