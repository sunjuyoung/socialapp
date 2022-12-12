import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./register.scss";

const Register = () => {
  const navigate = useNavigate();

  const [inputs, setinputs] = useState({
    nickname: "",
    password:"",
    email:"",
  })
  const [err, setErr] = useState(false);

  const handleOnChange = (e) =>{
      e.preventDefault();
      setinputs((prev)=>({...prev,[e.target.name]:e.target.value}))
  }

  const handleSubmit = async(e) =>{
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8802/api/auth/signUp",inputs);
      if(res.data === 'success'){
        alert("회원가입성공");
        navigate("/login");
      }
      
    } catch (error) {
      setErr(error.response.data[0].defaultMessage);
    }
  }

  return (
    <div className="register">
      <div className="card">
        <div className="left">
          <h1>Lama Social.</h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Libero cum,
            alias totam numquam ipsa exercitationem dignissimos, error nam,
            consequatur.
          </p>
          <span>Do you have an account?</span>
          <Link to="/login">
          <button>Login</button>
          </Link>
        </div>
        <div className="right">
          <h1>Register</h1>
          <form>
            <input type="text" 
            placeholder="Username" 
            name="nickname"  
            onChange={handleOnChange}/>

            <input type="email" 
            placeholder="Email" 
            name="email" 
            onChange={handleOnChange} />

            <input type="password" 
            placeholder="Password"  
            name="password" 
            onChange={handleOnChange} />
            {err && err }
            <button onClick={handleSubmit}>Register</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
