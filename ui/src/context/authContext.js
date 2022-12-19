import axios from "axios";
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState({
   user: JSON.parse(localStorage.getItem("user")) || null,
   nickname: localStorage.getItem("nickname") ||   null,
   accessToken: localStorage.getItem("accessToken") ||   null,
   id: localStorage.getItem("id") ||   null,

  }

  );

  const login = async (inputs) => {
    const res = await axios.post("http://localhost:8082/login", inputs)
    setCurrentUser({
      nickname: res.data.nickname,
      id: res.data.id,
      email: res.data.email,
      accessToken: res.data.accessToken,
      user:res.data
    })


  };

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(currentUser.user));
    localStorage.setItem("accessToken", currentUser.accessToken);
    localStorage.setItem("id", currentUser.id);
    localStorage.setItem("nickname", currentUser.nickname);
  }, [currentUser]);

  return (
    <AuthContext.Provider value={{ currentUser, login }}>
      {children}
    </AuthContext.Provider>
  );
};
