import { createEntityAdapter, createSelector } from "@reduxjs/toolkit";
import { apiSlice } from "../../app/api/apiSlice";

const postsAdapter = createEntityAdapter()
const initialState = postsAdapter.getInitialState();

export const postsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
      getPosts: builder.query({
        query: ({id}) => `/api/post/list/${id}`,
        // transformResponse: responseData => {
        //     return postsAdapter.setAll(initialState, responseData)
        // },
        providesTags: ['Post'],
      
      }),


      addNewPost: builder.mutation({
        query: (data) => ({
            
          url: `/api/post/${data.userId}`,
          method: 'POST',
          body :data
        }),
        invalidatesTags: ['Post'],
      }),

      updateNote: builder.mutation({
        query: ({...initialUserData}) => ({
          url: `/api/review/${initialUserData.id}`,
          method: 'PATCH',
          body :{
            ...initialUserData,
          }
        }),
        invalidatesTags:['Post'],
      }),

      deleteNote: builder.mutation({
        query: ({id}) => ({
          url: `/api/review/${id}`,
          method: 'DELETE',
        }),
        invalidatesTags: (result,error,arg) => [{ type:'Note', id: arg.id }]
      }),


    }),
  })

  export const {
    useGetPostsQuery,
    useDeleteNoteMutation,
    useUpdateNoteMutation,
    useAddNewPostMutation,
} = postsApiSlice

// export const selectPostsResult =(state) => postsApiSlice.endpoints.getNotes.select()

// const selectNotesData = createSelector(selectPostsResult,postsResult => postsResult.data )

// export const {
//     selectAll: selectAllNotes,
//     selectById: selectNoteById,
//     selectIds: selectNoteIds
//     // Pass in a selector that returns the users slice of state
// } = postsAdapter.getSelectors(state => selectNotesData(state) ?? initialState)
// //export const selectAll = notesAdapter.getSelectors(initialState)
