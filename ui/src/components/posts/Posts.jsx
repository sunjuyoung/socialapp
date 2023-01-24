import Post from "../post/Post";
import "./posts.scss";
import { useSelector } from "react-redux";
import { useGetPostsQuery } from "../../app/slice/postsApiSlice";
import { selectCurrentId } from "../../pages/login/authSlice";
import { useEffect } from "react";

const Posts = () => {
  const userId = useSelector(selectCurrentId);

  console.log(userId);
  const { currentData: post, isLoading } = useGetPostsQuery(userId)

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
