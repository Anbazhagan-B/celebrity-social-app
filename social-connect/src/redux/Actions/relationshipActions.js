import {
  CONNECT_USER_REQUEST,
  AVOID_USER_REQUEST,
} from "../Constants/relationshipConstants";

export const connectUser = (fromUserId, toUserId) => ({
  type: CONNECT_USER_REQUEST,
  payload: { fromUserId, toUserId },
});

export const avoidUser = (fromUserId, toUserId) => ({
  type: AVOID_USER_REQUEST,
  payload: { fromUserId, toUserId },
});
