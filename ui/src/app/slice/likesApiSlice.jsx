
import { apiSlice } from "../api/apiSlice";

export const likesApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
      getLikes: builder.query({
        query: ({id}) => `/api/like/${id}`,

        providesTags: (result, error, arg) =>
        result
          ? [...result.map(({ id }) => ({ type: 'Like', id })), 'Like']
          : ['Like'],

      }),

      addLike: builder.mutation({
        query: ({...initialUserData}) => ({
          url: `/api/like/${initialUserData.postId}/${initialUserData.userId}`,
          method: 'POST',
 
        }),
        invalidatesTags: ['Like'],
      }),

      deleteLike: builder.mutation({
        query: ({...initialUserData}) => ({
          url: `/api/like/${initialUserData.postId}/${initialUserData.userId}`,
          method: 'DELETE',
 
        }),
        invalidatesTags: ['Like'],
    }),


    }),
  })

  export const {
    useGetLikesQuery,
    useDeleteLikeMutation,
    useAddLikeMutation,
} = likesApiSlice
export const selectLikesResult = (id) => likesApiSlice.endpoints.getLikes.useQuery(id);


