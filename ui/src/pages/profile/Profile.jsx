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
import {  useState } from "react";
import { useLocation } from "react-router-dom";
import Update from "../../components/update/Update";
import { useGetRelationshipQuery, useGetUserQuery } from "../../app/slice/usersApiSlice";
import { useSelector } from "react-redux";
import { selectUserInfo } from "../login/authSlice";

const Profile = () => {
  const userInfo = useSelector(selectUserInfo);
  const [openUpdate,setOpenUpdate] = useState(false);

  const userId = parseInt(useLocation().pathname.split("/")[2]);

  const { isLoading,
          error,
          data:user,
          isSuccess
       } = useGetUserQuery(userId);


  const { isLoading: isRelLoading,
          data:rel,
          isSuccess:isRelSuccess,
       } = useGetRelationshipQuery(userId);

       console.log(rel)


  
//   const queryClient = useQueryClient();
//   // Mutations
//   const mutation = useMutation((following)=>{

//     if(following) return makeRequest.delete("/api/relationship/"+currentUser.id+"/"+userId);

//     return makeRequest.post("/api/relationship/"+currentUser.id+"/"+userId);
// },{
//   onSuccess: () => {
//   // Invalidate and refetch
//   queryClient.invalidateQueries({ queryKey: ['relationship'] })
// }
// })

  const handleFollow = (e) =>{
    e.preventDefault();
   // mutation.mutate(relationshipData?.includes(parseInt(currentUser.id)))
  }


  let content

  if (isLoading) content = <p>Loading...</p>
  if (isSuccess) content = (
    <div className="profile">
    {isLoading? "loading" : 
    <>
    <div className="images">
      <img
        src={`${process.env.PUBLIC_URL}/upload/${user.coverPic}`}
        alt=""
        className="cover"
      />
      <img
        src={`${process.env.PUBLIC_URL}/upload/${user.profilePic}`}
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
          <span>{user.nickname}</span>
          <div className="info">
            <div className="item">
              <PlaceIcon />
              <span>USA</span>
            </div>
            <div className="item">
              <LanguageIcon />
              <span>{user.website}</span>
            </div>
          </div>
            {isRelLoading? "Loading.." : parseInt(userInfo.id) === userId ? 
            (<button onClick={()=> setOpenUpdate(true)}>update</button>) 
            : <button onClick={handleFollow}>
              {rel.includes(parseInt(userInfo.id))? "Following" : "Follow"}
              </button>} 
        </div>
        <div className="right">
          <EmailOutlinedIcon />
          <MoreVertIcon />
        </div>
      </div>
    <Posts userId={userId}/>
    </div></>}

    {openUpdate && <Update setOpenUpdate={setOpenUpdate} user={user}/>}
  </div>
  )

  


  return content;
};

export default Profile;
