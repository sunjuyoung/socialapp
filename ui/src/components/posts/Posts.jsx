import Post from "../post/Post";
import "./posts.scss";
import {useQuery } from '@tanstack/react-query'
import { makeRequest } from "../../axios";


const Posts = () => {
  const { isLoading, error, data } = useQuery({
    queryKey: ['posts'],
    queryFn: () =>
      makeRequest.get("/api/post/list").then((res)=>{
        console.log(res.data);
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
