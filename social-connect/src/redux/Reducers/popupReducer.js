const initialState = {
  show: false,
  title: "",
  message: "",
};

const popupReducer = (state = initialState, action) => {
  switch (action.type) {
    case "SHOW_POPUP":
      return {
        show: true,
        title: action.payload.error,
        message: action.payload.message,
      };
    case "HIDE_POPUP":
      return initialState;
    default:
      return state;
  }
};

export default popupReducer;
