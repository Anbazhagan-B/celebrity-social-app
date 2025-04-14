import { createStore, applyMiddleware, combineReducers } from "redux";
import createSagaMiddleware from "redux-saga";
import rootSaga from "./Sagas";
import userReducer from "./Reducers/userReducer";
import postReducer from "./Reducers/postReducer";
import connectionReducer from "./Reducers/connectionReducer";
import headerReducer from "./Reducers/headerReducer";
import popupReducer from "./Reducers/popupReducer";

const rootReducer = combineReducers({
  user: userReducer,
  post: postReducer,
  connections: connectionReducer,
  header: headerReducer,
  popup: popupReducer,
});

const sagaMiddleware = createSagaMiddleware();
const reduxStore = createStore(rootReducer, applyMiddleware(sagaMiddleware));

sagaMiddleware.run(rootSaga);

export default reduxStore;
