/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { SyntheticEvent } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState, useEffect } from 'react'
import { Storage } from 'aws-amplify'
import axios from 'axios'
import ProfilePhoto from '../photo-component/profile-photo-component'
import Form from 'react-bootstrap/Form'
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';


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





    return(
        <>
        {edit===false?(
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
                Date Joined: {props.userInState.currentUser.dateJoinedToStr}
                </Card.Text>
                {/* <Button variant="primary">Go somewhere</Button> */}
              </Card.Body>
              <Button variant="outline-primary"
              onClick={editProfile}
              >Edit</Button>{' '}
              </Card>):(
                <Card className={classes.root} style={{ width: '100%', top:'10px', marginTop: '0px', position:'relative' }}>
                {/* <Image src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" rounded /> */}
                {/* <Card.Img variant="top" src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" roundedCircle /> */}
                <div style={{ display: 'flex', flexDirection: 'column' }}>
              <ProfilePhoto userInState={props.userInState} />
                    <b>Change Picture</b>
                    <input 
                     type = "file"
                    onChange={onChange}
                    />
            </div>
                <Card.Body>
                    <Form>
                    <Card.Title>@{props.userInState.currentUser.username}</Card.Title>
                    <Card.Text>
                    <Grid container spacing={3}>
                    <Grid item xs={12} sm={12}>
              <TextField
                autoComplete="fname"
                name="firstName"
                variant="outlined"
                required
                fullWidth
                id="firstName"
                label="First Name"
                autoFocus
                value={fname}
                onChange={handleFnameChange}
              />
            </Grid>
            <Grid item xs={12} sm={12}>
              <TextField
                variant="outlined"
                fullWidth
                id="middleName"
                label="Middle Name"
                name="middleName"
                autoComplete="mname"
                value={mname}
                onChange={handleMnameChange}
              />
            </Grid>
            <Grid item xs={12} sm={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="lname"
                value={lname}
                onChange={handleLnameChange}
              />
            </Grid>
            </Grid>
            <FormControl variant="outlined" className={classes.formControl}>
              <InputLabel id="demo-simple-select-outlined-label">Gender *</InputLabel>
              <Select
                labelId="demo-simple-select-outlined-label"
                id="demo-simple-select-outlined"
                required
                value={gender}
                onChange={handleGenderChange}
                label="Gender"
              >
                
                <MenuItem value={1}>Male (He/Him)</MenuItem>
                <MenuItem value={2}>Female (She/Her)</MenuItem>
                <MenuItem value={3}>Other (They/Them)</MenuItem>
              </Select>
            </FormControl>
                    </Card.Text>
                    <Card.Text>
                    {props.userInState.currentUser.userGender.gender} ({props.userInState.currentUser.userGender.pronouns})
                    </Card.Text>
                    
                  <Button 
                  
                  variant="outline-primary"
                  onClick={axiosUpdate}
                  >Submit</Button>
                  <Button variant="outline-danger" onClick={dontEditProfile}>Cancel</Button>
                    </Form>
                    {/* <Button variant="primary">Go somewhere</Button> */}
                  </Card.Body>

                  </Card>
              )
        }

        </>
    );


}

export default ProfileCard;
