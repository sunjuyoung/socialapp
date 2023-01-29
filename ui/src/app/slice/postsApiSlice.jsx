import { createEntityAdapter, createSelector } from "@reduxjs/toolkit";
import { apiSlice } from "../api/apiSlice";

const postsAdapter = createEntityAdapter()
const initialState = postsAdapter.getInitialState();

export const postsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
      getPosts: builder.query({
        query: (id) => `/api/post/list/${id}`,
        providesTags: (result, error, id) => [{ type: 'Post', id }],

      }),

      searchPosts: builder.query({
        query: (keyword) => `/api/post/search/${keyword}`,
        transformResponse: responseData => {
          return postsAdapter.setAll(initialState, responseData)
      },
        providesTags: (result, error, keyword) => [{ type: 'Post', keyword }],

      }),


      addNewPost: builder.mutation({
        query: (data) => ({
            
          url: `/api/post/${data.userId}`,
          method: 'POST',
          body :data
        }),
        invalidatesTags: ['Post'],
      }),

      updatePost: builder.mutation({
        query: ({...initialUserData}) => ({
          url: `/api/review/${initialUserData.id}`,
          method: 'PATCH',
          body :{
            ...initialUserData,
          }
        }),
        invalidatesTags: ['Post'],
      }),

      deletePost: builder.mutation({
        query: ({id}) => ({
          url: `/api/review/${id}`,
          method: 'DELETE',
        }),
        invalidatesTags: ['Post'],
      }),

      addPostImage: builder.mutation({
        query: (data) => ({
          url: '/upload',
          method: 'POST',
          body :data
        }),
      }),


    }),
  })

  export const {
    useGetPostsQuery,
    useSearchPostsQuery,
    useDeletePostMutation,
    useAddPostImageMutation,
    useUpdatePostMutation,
    useAddNewPostMutation,
} = postsApiSlice

export const selectPostsResult = (keyword) => postsApiSlice.endpoints.searchPosts.useQuery(keyword);

const selectNotesData = createSelector(selectPostsResult,postsResult => postsResult.data )



export const {
    selectAll: selectAllNotes,
    selectById: selectNoteById,
    selectIds: selectNoteIds
    // Pass in a selector that returns the users slice of state
} = postsAdapter.getSelectors(state => selectNotesData(state) ?? initialState)
//export const selectAll = notesAdapter.getSelectors(initialState)
