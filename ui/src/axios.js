import axios from "axios";

const token = localStorage.getItem("accessToken");
const headers = {
    'Content-Type': 'application/json',
    'Authorization': "Bearer "+token,
  }
  const header = {
    'Authorization': "Bearer "+token,
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