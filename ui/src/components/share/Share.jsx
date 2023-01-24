import "./share.scss";
import Image from "../../assets/img.png";
import Map from "../../assets/map.png";
import Friend from "../../assets/friend.png";
import PersonIcon from '@mui/icons-material/Person';
import {  useEffect, useState } from "react";

import {  uploadRequest } from "../../axios";
import { useSelector } from "react-redux";
import { selectUserInfo } from "../../pages/login/authSlice";
import { useAddNewPostMutation } from "../../app/slice/postsApiSlice";

const Share = () => {

  const [image, setImage] = useState(null)
  const [description, setDescription] = useState("")
  const currentUser = useSelector(selectUserInfo);


  const [addNewPost ,{
    isLoading: isAddLoading,
    isSuccess: isAddSuccess,
    isError: isAddError,
    error: addError
  }] = useAddNewPostMutation();


  const upload = async ()=>{
    try {
      const formData = new FormData();
      formData.append("files", image);
      const res = await uploadRequest.post("/upload", formData);
      return res.data;
    } catch (error) {
      console.log(error)
    }
  }


  const handleClick = async (e) =>{
    e.preventDefault();
    let imgUrl = "";
   // if(image) imgUrl = await upload();
    await addNewPost({userId:currentUser.id,description})
    setDescription("");
    setImage(null);
  }



  return (
    <div className="share">
      <div className="container">
        <div className="top">
          <div className="left">
            {currentUser.profilePic?
             (<>
             <img
              src={process.env.PUBLIC_URL+"upload/"+currentUser.profilePic}
              alt=""/>
              </>) 
             : (<><PersonIcon /></>)}
       
            <input type="text" placeholder={`What's on your mind ${currentUser.nickname}?`} 
            id="description"
            onChange={(e)=> setDescription(e.target.value)} 
            value={description}
            />
          </div>
          <div className="right">
            {image && <img className="file" alt="" src={URL.createObjectURL(image)} />}
          </div>
        </div>
        <hr />
        <div className="bottom">
          <div className="left">
            <input type="file" id="image" style={{display:"none"}} 
            onChange={(e)=> setImage(e.target.files[0])} />
            <label htmlFor="image">
              <div className="item">
                <img src={Image} alt="" />
                <span>Add Image</span>
              </div>
            </label>
            <div className="item">
              <img src={Map} alt="" />
              <span>Add Place</span>
            </div>
            <div className="item">
              <img src={Friend} alt="" />
              <span>Tag Friends</span>
            </div>
          </div>
          <div className="right">
            <button onClick={handleClick}>Share</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Share;
