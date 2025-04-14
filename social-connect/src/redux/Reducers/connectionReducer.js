import {
  FETCH_CONNECTIONS_REQUEST,
  FETCH_CONNECTIONS_SUCCESS,
  FETCH_CONNECTIONS_FAIL,
  SEARCH_USERNAMES_FAIL,
  SEARCH_USERNAMES_SUCCESS,
  SEARCH_USERNAMES_REQUEST,
} from "../Constants/connectionConstants";

const initialState = {
  connections: [],
  loading: false,
  error: null,
  searchResults: [],
};

const connectionReducer = (state = initialState, action) => {
  switch (action.type) {
    case FETCH_CONNECTIONS_REQUEST:
      return { ...state, loading: true, error: null, searchResults: [] };
    case FETCH_CONNECTIONS_SUCCESS:
      return {
        ...state,
        loading: false,
        connections: action.payload,
        searchResults: [],
      };
    case FETCH_CONNECTIONS_FAIL:
      return {
        ...state,
        loading: false,
        error: action.payload,
        searchResults: [],
      };
    case SEARCH_USERNAMES_REQUEST:
      return { ...state, loading: true, searchResults: [] };

    case SEARCH_USERNAMES_SUCCESS:
      return { ...state, loading: false, searchResults: action.payload };

    case SEARCH_USERNAMES_FAIL:
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
};

export default connectionReducer;
