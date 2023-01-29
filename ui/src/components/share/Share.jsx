import "./share.scss";
import Image from "../../assets/img.png";
import Map from "../../assets/map.png";
import Friend from "../../assets/friend.png";
import PersonIcon from '@mui/icons-material/Person';
import {  useState } from "react";
import { useSelector } from "react-redux";
import { selectUserInfo } from "../../pages/login/authSlice";
import { useAddNewPostMutation, useAddPostImageMutation } from "../../app/slice/postsApiSlice";

const Share = () => {

  const [file, setFile] = useState(null);
  const [description, setDescription] = useState("")
  const currentUser = useSelector(selectUserInfo);

  const [addNewPost ,{
    isLoading: isAddLoading,
  }] = useAddNewPostMutation();

  const [addPostImage ,{
    isSuccess: isImgSuccess,
    data:ImgData,
  }] = useAddPostImageMutation();


  const upload = async ()=>{
    try {
      const formData = new FormData();
      formData.append("files", file);
      await addPostImage(formData)
      if(isImgSuccess)console.log(ImgData); 
    } catch (error) {
      console.log(error)
    }
  }


  const handleClick = async (e) =>{
  
    let imgUrl = "";
    if(file) imgUrl = await upload();
    await addNewPost({userId:currentUser.id,description,img:file})
    setDescription("");
    setFile(null);
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
            {file && <img className="file" alt="" src={URL.createObjectURL(file)} />}
          </div>
        </div>
        <hr />
        <div className="bottom">
          <div className="left">
          <input
              type="file"
              id="post_file"
              name="file"
              style={{ display: "none" }}
              onChange={(e) => setFile(e.target.files[0])}
            />
            <label htmlFor="post_file">
              <div className="item">
                <img src={Image} alt="file" />
                <span >Add Image</span>
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
