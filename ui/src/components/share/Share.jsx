import "./share.scss";
import Image from "../../assets/img.png";
import Map from "../../assets/map.png";
import Friend from "../../assets/friend.png";
import { useContext, useState } from "react";
import { AuthContext } from "../../context/authContext";
import {
  useMutation,
  useQueryClient,
} from '@tanstack/react-query'
import { makeRequest, uploadRequest } from "../../axios";

const Share = () => {

  const [image, setImage] = useState(null)
  const [description, setDescription] = useState("")
  const {currentUser} = useContext(AuthContext)

  const queryClient = useQueryClient()

    // Mutations
    const mutation = useMutation((newPost)=>{
      console.log("woefjweoifjio",newPost)
        return makeRequest.post("/api/post/"+currentUser.id,newPost)
    },{
      onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries({ queryKey: ['posts'] })
    }
  })


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
    console.log(image)
    if(image) imgUrl = await upload();
    mutation.mutate({description,img:imgUrl})
  
  }


  return (
    <div className="share">
      <div className="container">
        <div className="top">
          <img
            src={currentUser.profilePic}
            alt=""
          />
          <input type="text" placeholder={`What's on your mind ${currentUser.nickname}?`} id="description"
          onChange={(e)=> setDescription(e.target.value)} />
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
