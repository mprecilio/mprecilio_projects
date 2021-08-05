/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable @typescript-eslint/no-unused-vars */
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState, useEffect } from 'react'
import { Storage } from 'aws-amplify'
import axios from 'axios'


const useStyles = makeStyles({
    root: {
      position: "sticky",
      top: "74px",
      minWidth: "275"
    },
  
    title: {
      fontSize: 14
    },
    pos: {
      marginBottom: 12
    }
  });

  interface IProps{
    postData: any
  }


const Photo = (props:IProps) => {
    const classes = useStyles();

    const [images, setImages] = useState([])
    useEffect(() => {
      fetchImages()
    }, [])
  
    let myKey:any = (props.postData.myPhotos.length>0?props.postData.myPhotos[0].postPhotoKey:null)
  
    async function fetchImages(){
  
      // console.log("myKey: " + myKey)
  
      let imageKeys = await Storage.list(`${myKey}`)
      // console.log('------checkpoint 1-------')
      // console.log('imageKeys 1: ', imageKeys)
  
       imageKeys = await Promise.all(imageKeys.map(async (k:any) => {
  
               const key = await Storage.get(k.key)
  
               return key
           }))
  
      //  console.log('imageKeys 2: ', imageKeys)
       setImages(imageKeys)
    }

  let axiosFetcher = () => {

    myKey = (props.postData.myPhotos.length>0?props.postData.myPhotos[0]:null)

}

async function onChange(e:any) {
  const file = e.target.files[0];
  const result = await Storage.put(file.name, file)
  // console.log("result", result)

  axios.post('http://localhost:8080/RevatureSocialNetwork/api/user/postphoto',result)
  .then(response => {
    console.log(response)
  })
  .catch(error => {
    console.log(error)
  })

  axiosFetcher();
  fetchImages()
}

    return(
        <>

          {
          images.map(image => (

            // eslint-disable-next-line jsx-a11y/alt-text
            <img src={image}
              key={image}
              style={{ width: '100%', borderRadius:'4px', position:'sticky' }} />

          ))
        }
        
        </>
    );


}

export default Photo;
