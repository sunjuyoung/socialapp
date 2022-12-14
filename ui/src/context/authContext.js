import axios from "axios";
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState({
   //user: JSON.parse(localStorage.getItem("user")) || null,
   nickname: localStorage.getItem("nickname") ||   null,
   accessToken: localStorage.getItem("accessToken") ||   null,
   id: localStorage.getItem("id") ||   null,

  }

  );

  const login = async (inputs) => {
    const res = await axios.post("http://localhost:8082/login", inputs)
    console.log(res.data);
    setCurrentUser({
      nickname: res.data.nickname,
      id: res.data.id,
      email: res.data.email,
      accessToken: res.data.accessToken
    })


  };

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(currentUser));
    localStorage.setItem("accessToken", currentUser.accessToken);
    localStorage.setItem("id", currentUser.id);
  }, [currentUser]);

  return (
    <AuthContext.Provider value={{ currentUser, login }}>
      {children}
    </AuthContext.Provider>
  );
};
