import Post from "../post/Post";
import "./posts.scss";
import {useQuery } from '@tanstack/react-query'
import { makeRequest } from "../../axios";
import { useContext } from "react";
import { AuthContext } from "../../context/authContext";

const Posts = ({userId}) => {
  const { currentUser } = useContext(AuthContext);
  let profileUserId = "";
  if(userId){
    profileUserId = userId;
  }

  const { isLoading, error, data } = useQuery({
    queryKey: ['posts'],
    queryFn: () => 
      makeRequest.get("/api/post/list/"+currentUser.id+"?profileUserId="+profileUserId,
      {withCredentials: true}).then((res)=>{
        return res.data;
      })
  })


  return <div className="posts">
    {error 
    ? "Something went wrong.." 
    : isLoading 
    ? "loading" 
    : data.map(post=>(
      <Post post={post} key={post.postId}/>
    ))}
  </div>;
};

export default Posts;
