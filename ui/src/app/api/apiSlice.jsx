import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'



const baseQuery = fetchBaseQuery({
  baseUrl: 'http://localhost:8082',
  credentials: 'include',
  prepareHeaders: (headers, { getState }) => {
      const token = getState().auth.token

      if (token) {
          headers.set("authorization", `Bearer ${token}`)
      }
      return headers
  }
})



export const apiSlice = createApi({
  //baseQuery: fetchBaseQuery({ baseUrl: 'http://localhost:8087' }),
  baseQuery,
  tagTypes:['Post','Like','User'],
  endpoints: () => ({})
  })
