import axios from "axios";

const token = JSON.parse(localStorage.getItem("user"));
const headers =  {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+token?.accessToken,
  };
  const header = {
    'Authorization': "Bearer "+token?.accessToken
  }
export const makeRequest = axios.create({
    baseURL:"http://localhost:8082",
    headers : headers
})

export const uploadRequest = axios.create({
  baseURL:"http://localhost:8082",
 headers : header
})

export const putRequest = axios.create({
  baseURL:"http://localhost:8082",
  headers : headers,
  method: "put"
})