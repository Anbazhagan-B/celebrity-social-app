import { takeLatest, call, put } from "redux-saga/effects";
import axios from "axios";
import {
  FETCH_CONNECTIONS_REQUEST,
  FETCH_CONNECTIONS_SUCCESS,
  FETCH_CONNECTIONS_FAIL,
  SEARCH_USERNAMES_FAIL,
  SEARCH_USERNAMES_SUCCESS,
  SEARCH_USERNAMES_REQUEST,
} from "../Constants/connectionConstants";
import API_CONFIG from "../../config/apiConfig";

function* fetchConnectionsSaga(action) {
  const userId = action.payload;
  try {
    const response = yield call(
      axios.get,
      `${API_CONFIG.INTERACTION_SERVICE}/api/relationships/connections/${userId}`
    );
    yield put({ type: FETCH_CONNECTIONS_SUCCESS, payload: response.data });
  } catch (error) {
    yield put({
      type: FETCH_CONNECTIONS_FAIL,
      payload: error.response?.data?.message
        ? error.response?.data?.message
        : "Error fetching connections",
    });
  }
}

function* searchUsernamesSaga(action) {
  try {
    const res = yield call(() =>
      axios.get(
        `${API_CONFIG.INTERACTION_SERVICE}/api/relationships/search?prefix=${action.payload}`
      )
    );
    yield put({ type: SEARCH_USERNAMES_SUCCESS, payload: res.data });
  } catch (error) {
    yield put({
      type: SEARCH_USERNAMES_FAIL,
      payload: error.message || "Username search failed",
    });
  }
}

export function* watchConnectionSagas() {
  yield takeLatest(FETCH_CONNECTIONS_REQUEST, fetchConnectionsSaga);
  yield takeLatest(SEARCH_USERNAMES_REQUEST, searchUsernamesSaga);
}
