import { useContext, useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/authContext";
import { useLoginMutation } from "./authApiSlice";
import { setCredentials } from "./authSlice";
import "./login.scss";


const Login = () => {

  const dispatch = useDispatch();
  const [login,{isLoading}] = useLoginMutation();

  const [inputs, setinputs] = useState({
    nickname: "",
    password:"",
  })
  const [err, setErr] = useState(false);
  const navigate = useNavigate();

  const handleOnChange = (e) =>{
      e.preventDefault();
      setinputs((prev)=>({...prev,[e.target.name]:e.target.value}))
  }
  //const { login } = useContext(AuthContext);

  const handleLogin = async(e) => {
    e.preventDefault();
    try {
      const result = await login({nickname:inputs.nickname,password:inputs.password}).unwrap()
      dispatch(setCredentials(result))
        
      navigate('/')
    } catch (error) {
      console.log(error);
      
    }



    // try {
    //   await login(inputs);
    //   navigate("/")
    // } catch (error) {
    //   console.log(error)
    //   setErr(error)
    // }
  };

  if (isLoading) return <p>Loading...</p>

  const content = (
    <div className="login">
    <div className="card">
      <div className="left">
        <h1>Hello World.</h1>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero cum,
          alias totam numquam ipsa exercitationem dignissimos, error nam,
          consequatur.
        </p>
        <span>Don't you have an account?</span>
        <Link to="/register">
          <button>Register</button>
        </Link>
      </div>
      <div className="right">
        <h1>Login</h1>
        <form>
          <input type="text" placeholder="Username"  name="nickname" onChange={handleOnChange}/>
          <input type="password" placeholder="Password" name="password" onChange={handleOnChange}/>
          <button onClick={handleLogin}>Login</button>
        </form>
      </div>
    </div>
  </div>

  )

  return content;
};

export default Login;
