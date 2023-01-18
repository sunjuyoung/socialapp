import Post from "../post/Post";
import "./posts.scss";

import { useSelector } from "react-redux";
import { postsApiSlice, selectAllPosts, useGetPostsQuery } from "./postsApiSlice";
import { selectCurrentId, selectCurrentToken, selectUserInfo } from "../../pages/login/authSlice";
import { useEffect } from "react";

const Posts = () => {
  const userId = useSelector(selectCurrentId);
  const { currentData: post, isFetching, isLoading,isSuccess } = useGetPostsQuery({id:userId}, {
    pollingInterval: 30000,
    refetchOnMountOrArgChange: true,
    skip: false,
  })

  useEffect(() => {

  }, [isSuccess])


  return <div className="posts">
    {
     isLoading 
    ? "loading" 
    : post?.map(p=>(
      <Post post={p} key={p.postId}/>
    ))} 

    
  </div>;
};

export default Posts;
