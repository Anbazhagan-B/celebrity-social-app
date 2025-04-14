import {
  FETCH_CONNECTIONS_REQUEST,
  SEARCH_USERNAMES_REQUEST,
} from "../Constants/connectionConstants";

export const fetchConnections = (userId) => ({
  type: FETCH_CONNECTIONS_REQUEST,
  payload: userId,
});

export const searchUsernames = (prefix) => ({
  type: SEARCH_USERNAMES_REQUEST,
  payload: prefix,
});
