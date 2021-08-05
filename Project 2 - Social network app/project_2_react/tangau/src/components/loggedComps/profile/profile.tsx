import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Image from 'react-bootstrap/Image'
import 'bootstrap/dist/css/bootstrap.min.css';
import { Profiler } from 'inspector';
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
    userInState: any
    // username: string,
    // fname: string,
    // mname: string,
    // lname: string,
    // gender: string,
    // dateJoined: string,
  }


  const ProfileCard = (props:IProps) => {
    const classes = useStyles();

    const [images, setImages] = useState([])
  useEffect(() => {
    fetchImages()
  }, [])

  let myKey:any = null

  async function fetchImages(){

    await axiosFetcher()

    console.log("myKey: " + myKey)

    let imageKeys = await Storage.list(`${myKey}`)

    console.log('imageKeys 1: ', imageKeys)

    imageKeys = await Promise.all(imageKeys.map(async function (k:any) {

            const key = await Storage.get(k.key)

            return key
        }))

    console.log('imageKeys 2: ', imageKeys)
    setImages(imageKeys)
  }

  let axiosFetcher = async () => {

    const axiosPayLoad= await axios.get('http://localhost:8080/RevatureSocialNetwork/api/user/getphoto');

    const axiosData  = axiosPayLoad.data;

    console.log("axiosdata: ", axiosData);

    myKey = axiosData

  }
  async function onChange(e:any) {
    const file = e.target.files[0];
    const result = await Storage.put(file.name, file)
    console.log("result", result)

    axios.post('http://localhost:8080/RevatureSocialNetwork/api/user/postphoto',result)
    .then(response => {
      console.log(response)
    })
    .catch(error => {
      console.log(error)
    })

    fetchImages()
  }

    return(
        <>
        <Card className={classes.root} style={{ width: '100%', marginTop: '10px', position:'sticky' }}>
      <h1>Test</h1>
      <div style={{ display: 'flex', flexDirection: 'column' }}>
        {
          images.map(image => (

            <Image src={image}
              key={image} rounded />

            // <img
            //   src={image}
            //   key={image}
            //   style={{width: 500, height: 300}}
            // />

          ))
        }
      </div>
      <input
        type="file"
        onChange={onChange}

      />

            {/* <Image src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" rounded /> */}
            {/* <Card.Img variant="top" src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" roundedCircle /> */}
            <Card.Body>
                <Card.Title>@{props.userInState.username}</Card.Title>
                <Card.Text>
                {props.userInState.fname} {props.userInState.mname} {props.userInState.lname}
                </Card.Text>
                <Card.Text>
                {props.userInState.userGender.gender} ({props.userInState.userGender.pronouns})
                </Card.Text>
                <Card.Text>
                Date Joined: {props.userInState.dateJoinedToStr}
                </Card.Text>
                {/* <Button variant="primary">Go somewhere</Button> */}
            </Card.Body>
            </Card>

        </>
    );


}

export default ProfileCard; 
// function App() {
//   const [images, setImages] = useState([])
//   useEffect(() => {
//     fetchImages()
//   }, [])

//   let myKey:any = null

//   async function fetchImages(){

//     await axiosFetcher()

//     console.log("myKey: " + myKey)

//     let imageKeys = await Storage.list(`${myKey}`)

//     console.log('imageKeys 1: ', imageKeys)

//     imageKeys = await Promise.all(imageKeys.map(async function (k:any) {

//             const key = await Storage.get(k.key)

//             return key
//         }))

//     console.log('imageKeys 2: ', imageKeys)
//     setImages(imageKeys)
//   }

//   let axiosFetcher = async () => {

//     const axiosPayLoad= await axios.get('http://localhost:2525/RevatureSocialNetwork/api/user/getphoto');

//     const axiosData  = axiosPayLoad.data;

//     console.log("axiosdata: ", axiosData);

//     myKey = axiosData

//   }
//   async function onChange(e:any) {
//     const file = e.target.files[0];
//     const result = await Storage.put(file.name, file)
//     console.log("result", result)

//     axios.post('http://localhost:8080/RevatureSocialNetwork/api/user/postphoto',result)
//     .then(response => {
//       console.log(response)
//     })
//     .catch(error => {
//       console.log(error)
//     })

//     fetchImages()
//   }
//   return (
//     <div className="App">
//       <h1>Test</h1>
//       <div style={{ display: 'flex', flexDirection: 'column' }}>
//         {
//           images.map(image => (
//             <img
//               src={image}
//               key={image}
//               style={{width: 500, height: 300}}
//             />
//           ))
//         }
//       </div>
//       <input
//         type="file"
//         onChange={onChange}
//       />
//     </div>
//   );


// export default App;