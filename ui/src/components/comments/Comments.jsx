import { useContext } from "react";
import "./comments.scss";
import { AuthContext } from "../../context/authContext";
import { useQuery } from "@tanstack/react-query";
import { makeRequest } from "../../axios";
import moment from "moment";

const Comments = ({postId}) => {
  const { currentUser } = useContext(AuthContext);
  
  const { isLoading, error, data } = useQuery({
    queryKey: ['comments'],
    queryFn: () =>
      makeRequest.get("/api/comment/"+currentUser.id).then((res)=>{
        console.log(res.data);
        return res.data;
      })
  })
 
  return (
    <div className="comments">
      <div className="write">
        <img src={currentUser.profilePic} alt="" />
        <input type="text" placeholder="write a comment" />
        <button>Send</button>
      </div>
      {isLoading? "loading" : data.map((comment) => (
        <div className="comment" key={comment.id}>
          <img src={comment.profilePic} alt="" />
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
