import "./rightBar.scss";
import { useState } from "react";
import moment from "moment";
import { useSelector } from "react-redux";
import { selectUserInfo } from "../../pages/login/authSlice";
import { useGetRecommendUserQuery, useGetRelUserQuery } from "../../app/slice/usersApiSlice";

const RightBar = () => {

  const currentUser = useSelector(selectUserInfo);

  const {data,isLoading,isSuccess} = useGetRelUserQuery(currentUser.id);
  
  
  
  const {data:recommendUser,
    isLoading:isRecLoading,
    isSuccess:isRecSuccess,
  } = useGetRecommendUserQuery(currentUser.id);

  if(isLoading) console.log("...")
  if(isSuccess) console.log(data)
  

  return (
    <div className="rightBar">
      <div className="container">
        <div className="item">
          <span>Suggestions For You</span>


        {isRecLoading? (<div>Loading..</div>) 
        : ( recommendUser.map(rUser => (
            <div className="user">
            <div className="userInfo">
              <img
                src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                alt=""
              />
              <span>{rUser.nickname}</span>
            </div>
            <div className="buttons">
              <button>follow</button>
              <button>dismiss</button>
            </div>
          </div>
        ))

        )}


        </div>
        {/* <div className="item">
          <span>Latest Activities</span>
          
          
          {isLoading
          ? "loading" 
          :data.map((p)=> (
            <div className="user" key={p.postId}>
            <div className="userInfo">
              <img
                src={`${process.env.PUBLIC_URL}/upload/`+p.profilePic}
                alt=""
              />
              <p>
                <span>{p.nickname}</span> {p.description}
              </p>
            </div>
            <span>{moment(p.modifiedBy).fromNow()}</span>
          </div>
          ))}
          
        </div> */}
        <div className="item">
          <span>Online Friends</span>


          {isLoading? (<div>Loading..</div>)
          : (
            data.map(f=> (
              <div className="user">
              <div className="userInfo">
                <img
                  src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                  alt=""
                />
                <div className="online" />
                <span>{f.nickname}</span>
              </div>
            </div>
            ))

          )}
       




        </div>
      </div>
    </div>
  );
};

export default RightBar;
