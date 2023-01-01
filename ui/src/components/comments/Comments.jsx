import { useContext, useState } from "react";
import "./comments.scss";
import { AuthContext } from "../../context/authContext";
import { useMutation, useQuery,useQueryClient } from "@tanstack/react-query";
import { makeRequest } from "../../axios";
import moment from "moment";
import PersonIcon from '@mui/icons-material/Person';

const Comments = ({postId}) => {
  const { currentUser } = useContext(AuthContext);
  const [description, setDescription] = useState("");

  const { isLoading, error, data } = useQuery({
    queryKey: ['comments'],
    queryFn: () =>
      makeRequest.get("/api/comment/"+currentUser.id).then((res)=>{
        console.log(res.data);
        return res.data;
      })
  })
  
  const queryClient = useQueryClient();
    // Mutations
    const mutation = useMutation((newComment)=>{
      return makeRequest.post("/api/comment/"+postId+"/"+currentUser.id,newComment)
  },{
    onSuccess: () => {
    // Invalidate and refetch
    queryClient.invalidateQueries({ queryKey: ['comments'] })
  }
})
  
  const handleComment = async(e) =>{
    e.preventDefault();
    mutation.mutate({description})
    setDescription("");
  }
 
  return (
    <div className="comments">
      <div className="write">
      {currentUser.profilePic?
             (<>
             <img
              src={process.env.PUBLIC_URL+"upload/"+currentUser.profilePic}
              alt=""/>
              </>) 
             : (<><PersonIcon /></>)}
        <input type="text" placeholder="write a comment" id="description"  
        value={description} 
        onChange={(e)=>setDescription(e.target.value)}/>
        <button onClick={handleComment}>Send</button>
      </div>
      {isLoading? "loading" : data.map((comment) => (
        <div className="comment" key={comment.id}>
                {comment.profilePic?
             (<>
             <img
              src={comment.profilePic}
              alt=""/>
              </>) 
             : (<><PersonIcon /></>)}
          <div className="info">
            <span>{comment.nickname}</span>
            <p>{comment.description}</p>
          </div>
          <span className="date">
            {moment(comment.modifiedBy).fromNow()}
            </span>
        </div>
      ))}
    </div>
  );
};

export default Comments;
