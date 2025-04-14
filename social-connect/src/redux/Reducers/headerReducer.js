import { SET_HEADER_TITLE } from "../Constants/headerConstants";

const initialState = {
  title: "",
};

const headerReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_HEADER_TITLE:
      return {
        ...state,
        title: action.payload,
      };
    default:
      return state;
  }
};

export default headerReducer;
