import { apiSlice } from "../../app/api/apiSlice"
import { logout } from "./authSlice";

export const authApiSlice = apiSlice.injectEndpoints({
    endpoints: builder => ({
        login: builder.mutation({
            query: credentials => ({
                url: '/login',
                method: 'POST',
                body: {...credentials}
            })
        }),
        refresh: builder.mutation({
            query: () => ({
                url: '/api/member/refreshToken',
                method: 'GET',
            
            })
        }),

    })
})

export const {
    useLoginMutation,
    useRefreshMutation,
} = authApiSlice