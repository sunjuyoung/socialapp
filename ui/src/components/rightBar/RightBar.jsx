import "./rightBar.scss";
import { useState } from "react";
import moment from "moment";
import { useSelector } from "react-redux";
import { selectUserInfo } from "../../pages/login/authSlice";

const RightBar = () => {

  const currentUser = useSelector(selectUserInfo);


  // const { isLoading, error, data } = useQuery({
  //   queryKey: ['latelyPosts'],
  //   queryFn: () => 
  //     makeRequest.get("/api/post/latelyList/"+currentUser.id,
  //     {withCredentials: true}).then((res)=>{
  //       return res.data;
  //     })
  // })
  

  return (
    <div className="rightBar">
      <div className="container">
        <div className="item">
          <span>Suggestions For You</span>
          <div className="user">
            <div className="userInfo">
              <img
                src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                alt=""
              />
              <span>Jane Doe</span>
            </div>
            <div className="buttons">
              <button>follow</button>
              <button>dismiss</button>
            </div>
          </div>
          <div className="user">
            <div className="userInfo">
              <img
                src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                alt=""
              />
              <span>Jane Doe</span>
            </div>
            <div className="buttons">
              <button>follow</button>
              <button>dismiss</button>
            </div>
          </div>
        </div>
        <div className="item">
          <span>Latest Activities</span>
          
          
          {/* {isLoading
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
          ))} */}
          
        </div>
        <div className="item">
          <span>Online Friends</span>

       
          <div className="user">
            <div className="userInfo">
              <img
                src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                alt=""
              />
              <div className="online" />
              <span>Jane Doe</span>
            </div>
          </div>
          <div className="user">
            <div className="userInfo">
              <img
                src="https://images.pexels.com/photos/4881619/pexels-photo-4881619.jpeg?auto=compress&cs=tinysrgb&w=1600"
                alt=""
              />
              <div className="online" />
              <span>Jane Doe</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RightBar;
