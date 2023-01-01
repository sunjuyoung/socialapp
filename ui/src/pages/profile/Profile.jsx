import "./profile.scss";
import FacebookTwoToneIcon from "@mui/icons-material/FacebookTwoTone";
import LinkedInIcon from "@mui/icons-material/LinkedIn";
import InstagramIcon from "@mui/icons-material/Instagram";
import PinterestIcon from "@mui/icons-material/Pinterest";
import TwitterIcon from "@mui/icons-material/Twitter";
import PlaceIcon from "@mui/icons-material/Place";
import LanguageIcon from "@mui/icons-material/Language";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import Posts from "../../components/posts/Posts"
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { makeRequest } from "../../axios";
import { useContext, useState } from "react";
import { AuthContext } from "../../context/authContext";
import { useLocation } from "react-router-dom";
import Update from "../../components/update/Update";

const Profile = () => {
  const [openUpdate,setOpenUpdate] = useState(false);
  const { currentUser } = useContext(AuthContext);
  
  const userId = parseInt(useLocation().pathname.split("/")[2]);
  const { isLoading, error, data } = useQuery({
    queryKey: ['user'],
    queryFn: () =>
      makeRequest.get("/api/user/"+userId).then((res)=>{
        return res.data;
      })
  })
  console.log(data);

  const { isLoading :rIsLoading, data: relationshipData } = useQuery({
    queryKey: ['relationship'],
    queryFn: () =>
      makeRequest.get("/api/relationship/"+userId).then((res)=>{
        return res.data;
      })
  })
  
  const queryClient = useQueryClient();
  // Mutations
  const mutation = useMutation((following)=>{

    if(following) return makeRequest.delete("/api/relationship/"+currentUser.id+"/"+userId);

    return makeRequest.post("/api/relationship/"+currentUser.id+"/"+userId);
},{
  onSuccess: () => {
  // Invalidate and refetch
  queryClient.invalidateQueries({ queryKey: ['relationship'] })
}
})

  const handleFollow = (e) =>{
    e.preventDefault();
    mutation.mutate(relationshipData?.includes(parseInt(currentUser.id)))
  }


  return (
    
    <div className="profile">
      {isLoading? "loading" : 
      <>
      <div className="images">
        <img
          src={`${process.env.PUBLIC_URL}/upload/${data.coverPic}`}
          alt=""
          className="cover"
        />
        <img
          src={`${process.env.PUBLIC_URL}/upload/${data.profilePic}`}
          alt=""
          className="profilePic"
        />
      </div>
      <div className="profileContainer">
        <div className="uInfo">
          <div className="left">
            <a href="http://facebook.com">
              <FacebookTwoToneIcon fontSize="large" />
            </a>
            <a href="http://facebook.com">
              <InstagramIcon fontSize="large" />
            </a>
            <a href="http://facebook.com">
              <TwitterIcon fontSize="large" />
            </a>
            <a href="http://facebook.com">
              <LinkedInIcon fontSize="large" />
            </a>
          </div>
          <div className="center">
            <span>{data.nickname}</span>
            <div className="info">
              <div className="item">
                <PlaceIcon />
                <span>USA</span>
              </div>
              <div className="item">
                <LanguageIcon />
                <span>{data.website}</span>
              </div>
            </div>
              {rIsLoading? "Loading.." : parseInt(currentUser.id) === userId ? 
              (<button onClick={()=> setOpenUpdate(true)}>update</button>) 
              : <button onClick={handleFollow}>
                {relationshipData.includes(parseInt(currentUser.id))? "Following" : "Follow"}
                </button>} 
          </div>
          <div className="right">
            <EmailOutlinedIcon />
            <MoreVertIcon />
          </div>
        </div>
      <Posts userId={userId}/>
      </div></>}

      {openUpdate && <Update setOpenUpdate={setOpenUpdate} user={data}/>}
    </div>
 
  );
};

export default Profile;
