import { configureStore } from '@reduxjs/toolkit'
import { setupListeners } from '@reduxjs/toolkit/query'
import { apiSlice } from './api/apiSlice'
import authReducer from '../pages/login/authSlice'
import { postsApiSlice } from '../components/posts/postsApiSlice'
import { likesApiSlice } from '../components/posts/likesApiSlice'


export const store = configureStore({
  reducer: {
    [apiSlice.reducerPath]: apiSlice.reducer,
    [postsApiSlice.reducerPath] : postsApiSlice.reducer,
    [likesApiSlice.reducerPath] : likesApiSlice.reducer,
    auth: authReducer,
  },

  middleware: (getDefaultMiddleware) =>
  getDefaultMiddleware().concat(apiSlice.middleware),
devTools: true

})

setupListeners(store.dispatch)