
import { apiSlice } from "../api/apiSlice";



export const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({

      getUser: builder.query({
        query: (id) => `/api/user/${id}`,
        providesTags: (result, error, id) => [{ type: 'User', id }],
      }),

      updateUser: builder.mutation({
        query: ({...initialUserData}) => ({
          url: `/api/review/${initialUserData.id}`,
          method: 'PUT',
          body :{
            ...initialUserData,
          }
        }),
        invalidatesTags: ['User'],
      }),


      
      getRelationship: builder.query({
        query: (id) => `/api/relationship/${id}`,
        providesTags: (result, error, id) => [{ type: 'User', id }],
      }),

    }),
  })

  export const {
    useGetUserQuery,
    useGetRelationshipQuery,
    useUpdateUserMutation,
} = usersApiSlice



