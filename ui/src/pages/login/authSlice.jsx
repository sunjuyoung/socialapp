import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
    name: 'auth',
    initialState: { token: null, currentUser:null, id: null,profilePic:null },
    reducers :{
        setCredentials : (state, action) => {
            const result  = action.payload
            state.token = result.accessToken
            state.currentUser = result.nickname
            state.id = result.id
            state.profilePic = result.profilePic
        },
        logout : (state, action) => {
            state.token = null
        }
    }
})

export const { setCredentials, logout } = authSlice.actions
export default authSlice.reducer
export const selectCurrentToken = (state) => state.auth.token
export const selectCurrentUser = (state) => state.auth.currentUser
export const selectCurrentId = (state) => state.auth.id
export const selectUserInfo = (state) => state.auth
