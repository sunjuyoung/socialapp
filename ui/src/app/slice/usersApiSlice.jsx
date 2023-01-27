
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


      getRelUser: builder.query({
        query: (id) => `/api/user/rel/${id}`,
        providesTags: (result, error, id) => [{ type: 'User', id }],
      }),

      getRecommendUser: builder.query({
        query: (id) => `/api/user/recommendUser/${id}`,
        providesTags: (result, error, id) => [{ type: 'User', id }],
      }),
      
      getRelationship: builder.query({
        query: (id) => `/api/relationship/${id}`,
        providesTags: (result, error, id) => [{ type: 'User', id }],
      }),



    }),
  })

  export const {
    useGetUserQuery,
    useGetRelUserQuery,
    useGetRecommendUserQuery,
    useGetRelationshipQuery,
    useUpdateUserMutation,

} = usersApiSlice



