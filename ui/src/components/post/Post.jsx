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
import { useState } from "react";
import moment from "moment";
import { useSelector } from "react-redux";
import { selectCurrentId, selectUserInfo } from "../../pages/login/authSlice";
import {  selectLikesResult,
   useAddLikeMutation, useDeleteLikeMutation } from "../../app/slice/likesApiSlice";
import { useEffect } from "react";


const Post = ({ post }) => {
  const [commentOpen, setCommentOpen] = useState(false);
  const currentUser = useSelector(selectUserInfo);

  
  const [menuOpen, setMenuOpen] = useState(false);
  const [selectedItem, setSelectedItem] = useState('');


  const userId = useSelector(selectCurrentId);
  const { currentData:like, isError, isLoading, isSuccess } = selectLikesResult({id:post.postId});

  const  [deleteLike,{
    isLoading: isDelLoading,
    isSuccess: isDelSuccess,
    isError: isDelError,
    error: delError
  }] =useDeleteLikeMutation();

  const [addLike,{
    isLoading: isAddLoading,
    isSuccess: isAddSuccess,
    isError: isAddError,
    error: addError
  }] = useAddLikeMutation();


  const handleLike = async (e) =>{
    console.log(post.postId)
      await addLike({postId:post.postId,userId:currentUser.id});
  }

  const handleDelLike = async (e) =>{
    console.log(post.postId)
    await deleteLike({postId:post.postId,userId:currentUser.id});
  }

  const handlePostDelete = (e) =>{

  }

  const content = (
    <>
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
            {isLoading ? "loading" : like?.includes(parseInt(currentUser.id))
            ? 
            <FavoriteOutlinedIcon style={{color:"red"}} onClick={handleDelLike}/> 
            : 
            <FavoriteBorderOutlinedIcon onClick={handleLike} />}
            {like?.length} Likes
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
    </>
  )


  return content;
};

export default Post;
