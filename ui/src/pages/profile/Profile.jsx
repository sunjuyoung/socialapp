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
import { useContext } from "react";
import { AuthContext } from "../../context/authContext";
import { useLocation } from "react-router-dom";

const Profile = () => {

  const { currentUser } = useContext(AuthContext);
  
  const userId = parseInt(useLocation().pathname.split("/")[2]);
  const { isLoading, error, data } = useQuery({
    queryKey: ['user'],
    queryFn: () =>
      makeRequest.get("/api/user/"+userId).then((res)=>{
        return res.data;
      })
  })

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
    console.log(following)
    if(following) return makeRequest.delete("/api/relationship/"+userId+"/"+currentUser.id);

    return makeRequest.post("/api/relationship/",{followUser:userId,followedUser:currentUser.id});
},{
  onSuccess: () => {
  // Invalidate and refetch
  queryClient.invalidateQueries({ queryKey: ['user'] })
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
              (<button>update</button>) 
              : <button onClick={handleFollow}>
                {relationshipData.includes(currentUser.id)? "Following" : "Follow"}
                </button>} 
          </div>
          <div className="right">
            <EmailOutlinedIcon />
            <MoreVertIcon />
          </div>
        </div>
      <Posts/>
      </div></>}
    </div>
 
  );
};

export default Profile;
