import { configureStore } from "@reduxjs/toolkit";
import leaveHistoryReducer from "./slices/leaveSlice";
import authReducer from "./slices/authSlice";
import googleReducer from "./slices/googleSlice";
import fetchLeaves from "./slices/fetchleaveSlice";
import applyleaveSlice from "./slices/applyLeaveslice";
import fetchLeavesType from "./slices/fetchLeaveTypeSlice";
import leaveApprovalReducer from "./slices/leaveApprovalSlice";
import fetchUsers from "./slices/fetchUsersSlice";
export const store = configureStore({
  reducer: {
    leaveHistory: leaveHistoryReducer,
    auth: authReducer,
    google: googleReducer,
    fetchLeave: fetchLeaves,
    applyleave: applyleaveSlice,
    fetchLeavesType: fetchLeavesType,
    leaveApproval: leaveApprovalReducer,
    fetchUsers: fetchUsers,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
