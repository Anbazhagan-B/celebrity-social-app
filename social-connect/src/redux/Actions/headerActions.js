import { SET_HEADER_TITLE } from "../Constants/headerConstants";

export const setHeaderTitle = (title) => ({
  type: SET_HEADER_TITLE,
  payload: title,
});
