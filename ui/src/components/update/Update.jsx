import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useState } from 'react'
import { makeRequest, putRequest, uploadRequest } from '../../axios';
import './update.scss';

const Update = ({setOpenUpdate,user}) => {
    const [coverPic, setCoverPic] = useState(null)
    const [profilePic, setProfilePic] = useState(null)
    const [texts, setTexts] = useState({
        nickname : "",
        city: "",
        website: ""
    })
    
    const queryClient = useQueryClient()

    // Mutations
    const mutation = useMutation((profile)=>{
        return putRequest.put("/api/user/"+user.id, profile)
    },{
      onSuccess: () => {
      // Invalidate and refetch
      queryClient.invalidateQueries({ queryKey: ['user'] })
    }
  })

    const upload = async (file)=>{
        try {
            const formData = new FormData();
            formData.append("files", file);
            const res = await uploadRequest.post("/upload", formData);
            return res.data;
        } catch (error) {
         console.log(error)
        }
      }

    const handleChange = (e) =>{
        setTexts((prev)=>({...prev, [e.target.name]: e.target.value }))
    }

    
    const handleClick = async (e) =>{
    e.preventDefault();
    let cover = user.coverPic;
    let profile = user.profilePic;

    cover = coverPic? await upload(coverPic) : user.coverPic;
    profile = profilePic? await upload(profilePic) : user.profilePic;

    mutation.mutate({...texts,coverPic:cover, profilePic:profile})
    setTexts("");
    setCoverPic(null);
    setProfilePic(null);
    setOpenUpdate(false);
    }

  return (
    <div className='update'>
        Update
        <form>
            <input type="file"  name='coverPic' id='coverPic' 
            onChange={(e)=> setCoverPic(e.target.files[0])}/>
            <input type="file"  name='profilePic' id='profilePic' 
            onChange={(e)=> setProfilePic(e.target.files[0])}/>
            <input type="text"  placeholder='nickname' name='nickname' 
            onChange={handleChange}  value={user.nickname}/>
            <input type="text"  placeholder='city' name='city' 
            onChange={handleChange} value={user.city} />
            <input type="text"  placeholder='website' name='website' 
            onChange={handleChange} value={user.website} />
            <button onClick={handleClick}>update</button>
        </form>
        <button onClick={()=> setOpenUpdate(false)}>X</button>
    </div>
  )
}

export default Update