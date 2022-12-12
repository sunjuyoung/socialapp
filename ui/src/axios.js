import axios from "axios";

let token = localStorage.getItem("accessToken");
const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+token,
  }
export const makeRequest = axios.create({
    baseURL:"http://localhost:8802",
    headers : headers
})