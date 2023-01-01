import "./post.scss";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import TextsmsOutlinedIcon from "@mui/icons-material/TextsmsOutlined";
import ShareOutlinedIcon from "@mui/icons-material/ShareOutlined";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

import DeleteIcon from '@mui/icons-material/Delete';
import UpdateIcon from '@mui/icons-material/Update';

import { Link } from "react-router-dom";
import Comments from "../comments/Comments";
import { useContext, useState } from "react";
import moment from "moment";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { makeRequest } from "../../axios";
import { AuthContext } from "../../context/authContext";


const Post = ({ post }) => {
  const [commentOpen, setCommentOpen] = useState(false);
  const { currentUser } = useContext(AuthContext);
  const [menuOpen, setMenuOpen] = useState(false);
  const [selectedItem, setSelectedItem] = useState('');

  const { isLoading, error, data } = useQuery({
    queryKey: ['likes',post.postId],
    queryFn: () =>
      makeRequest.get("/api/like/"+post.postId).then((res)=>{
        return res.data;
      })
  })

  
  const queryClient = useQueryClient();
  // Mutations
  const mutation = useMutation((liked)=>{
    console.log(liked)
    if(liked) return makeRequest.delete("/api/like/"+post.postId+"/"+currentUser.id);

    return makeRequest.post("/api/like/"+post.postId+"/"+currentUser.id);
},{
  onSuccess: () => {
  // Invalidate and refetch
  queryClient.invalidateQueries({ queryKey: ['likes'] })
}
})

  const handleLike = (e) =>{
    e.preventDefault();
    mutation.mutate(data?.includes(parseInt(currentUser.id)))
  }

  const handlePostDelete = (e) =>{

  }


  return (
    <div className="post">
      <div className="container">
        <div className="user">
          <div className="userInfo">
            <img src={`${process.env.PUBLIC_URL}/upload/`+post.profilePic} alt="" />
            <div className="details">
              <Link
                to={`/profile/${post.userId}`}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                <span className="name">{post.nickname}</span>
              </Link>
              <span className="date">{moment(post.modifiedBy).fromNow()}</span>
            </div>
          </div>
          {/* {post.userId === currentUser.id
          ? <MoreHorizIcon /> 
          : <></> } */}
      
        <div className="deleteBtn">
          <DeleteIcon onClick={handlePostDelete()}/>
        </div>

        </div>
        <div className="content">
          <p>{post.description}</p>
          <img src={`${process.env.PUBLIC_URL}/upload/`+post.img} alt="" />
        </div>
        <div className="info">
          <div className="item">
            {isLoading ? "loading" : data?.includes(parseInt(currentUser.id))
            ? 
            <FavoriteOutlinedIcon style={{color:"red"}} onClick={handleLike}/> 
            : 
            <FavoriteBorderOutlinedIcon onClick={handleLike} />}
            {data?.length} Likes
          </div>
          <div className="item" onClick={() => setCommentOpen(!commentOpen)}>
            <TextsmsOutlinedIcon />
            12 Comments
          </div>
          <div className="item">
            <ShareOutlinedIcon />
            Share
          </div>
        </div>
        {commentOpen && <Comments postId={post.postId}/>}
      </div>
    </div>
  );
};

export default Post;
