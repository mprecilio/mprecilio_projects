/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { SyntheticEvent } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState, useEffect } from 'react'
import { Storage } from 'aws-amplify'
import axios from 'axios'
import ProfilePhoto from '../photo-component/second-profile-photo-component'


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
    },
    paper: {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
    },
    avatar: {
    },
    form: {
      width: '100%', // Fix IE 11 issue.
    },
    submit: {
    },
    formControl: {
      minWidth: 120,
      marginTop:'23px',
      width: '100%',
    },
    selectEmpty: {
    },
  });

  interface IProps{
    userInState: any
  }


const ProfileCard = (props:IProps) => {

  console.log(props.userInState)
    const classes = useStyles();

    const [images, setImages] = useState([])
    const [edit, setEdit] = useState(false)


    const editProfile = ()=>{
      setEdit(true);
    }

    const dontEditProfile = ()=>{
      setEdit(false);
    }


    useEffect(() => {
      fetchImages()
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])
  
    let myKey:any = null
  
    async function fetchImages(){
  
     await axiosFetcher()
  
      console.log("myKey: " + myKey)
  
      let imageKeys = await Storage.list(`${myKey}`)
  
      console.log('imageKeys 1: ', imageKeys)
  
       imageKeys = await Promise.all(imageKeys.map(async (k:any) => {
  
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

  await axiosFetcher();
  fetchImages()
}

const [fname, setFname] = useState('');
const [mname, setMname] = useState('');
const [lname, setLname] = useState('');
const [gender, setGender] = useState('');


const handleFnameChange = (event: any) => {
  setFname(event.target.value);
};

const handleMnameChange = (event: any) => {
  setMname(event.target.value);
};

const handleLnameChange = (event: any) => {
  setLname(event.target.value);
};

const handleGenderChange = (event: any) => {
  setGender(event.target.value);
};


let axiosUpdate = async (eve: SyntheticEvent) => {

  eve.preventDefault();
  dontEditProfile();

  
  await axios.put('http://localhost:8080/RevatureSocialNetwork/api/user/update-info', {
      "username": props.userInState.currentUser.username,
      "fname": fname,
      "mname": mname,
      "lname": lname,
      "userGenderInt": gender,
  });

}
  let myStr = "";
    let myDate = new Date(props.userInState.currentUser.dateJoined);
    myStr+=myDate;
    myStr = myStr.slice(4, 21);
    let myArr = myStr.split(' '); 
    myStr = ''
    myArr.map(function(val, index){
        myStr += val
        if(index < 2) myStr+='-';
        else myStr+= ' ';
        return ""
    })
    myStr = myStr.substring(0,11);  


  console.log("#########")
  console.log(props.userInState)
  console.log("#########")

    return(
        <>
            <Card className={classes.root} style={{ width: '100%', marginTop: '10px', position:'sticky' }}>
            {/* <Image src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" rounded /> */}
            {/* <Card.Img variant="top" src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" roundedCircle /> */}
            <div style={{ display: 'flex', flexDirection: 'column' }}>
          <ProfilePhoto userInState={props.userInState} />
        </div>
            <Card.Body>
                <Card.Title>@{props.userInState.currentUser.username}</Card.Title>
                <Card.Text>
                {props.userInState.currentUser.fname} {props.userInState.currentUser.mname} {props.userInState.currentUser.lname}
                </Card.Text>
                <Card.Text>
                {props.userInState.currentUser.userGender.gender} ({props.userInState.currentUser.userGender.pronouns})
                </Card.Text>
                <Card.Text>
                Date Joined: {myStr}
                </Card.Text>
                {/* <Button variant="primary">Go somewhere</Button> */}
              </Card.Body>
              </Card>
        

        </>
    );


}

export default ProfileCard;
