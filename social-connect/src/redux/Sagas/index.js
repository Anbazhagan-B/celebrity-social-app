import { all } from "redux-saga/effects";
import { watchPostSagas } from "./postSagas";
import { watchUserSagas } from "./userSagas";
import { watchRelationshipSagas } from "./relationshipSagas";
import { watchConnectionSagas } from "./connectionSagas";

export default function* rootSaga() {
  yield all([
    watchPostSagas(),
    watchUserSagas(),
    watchRelationshipSagas(),
    watchConnectionSagas(),
  ]);
}
