/* eslint-disable array-callback-return */
/* eslint-disable react-hooks/exhaustive-deps */
import React, {useEffect, useState} from 'react';
import Post from '../post/post'
import Profile from '../profile/profile-container-2'
import NoProfile from '../profile/no-profile-found'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import {useHistory } from 'react-router-dom';
import '../navbar/navbar-style.css'
import axios from 'axios';




interface IProps{
    stateData: any,
    uri: string
}

const ViewUserProfile = (props:IProps) => {
    

    
        let myProfile = {
            "userID": 0,
            "username": "test",
            "password": "",
            "fname": "",
            "mname": null,
            "lname": "",
            "userEmail": "@gmail.com",
            "userGender": {
                "genderId": 0,
                "gender": "",
                "pronouns": ""
            },
            "userRole": {
                "roleId": 0,
                "role": ""
            },
            "profilePhoto": 'Default.png',
            "dateJoinedToStr": null,
            "userGenderInt": 0,
            "userRoleInt": 0,
            "token": ""
        };
    const [profile, setProfile] = useState(myProfile);

    let axiosProfileFetcher = async (username:string) => {
        console.log('-------------getting user')
        console.log(username + " current profile " + profile.username);
        const axiosData: any = await axios.get(`http://localhost:8080/RevatureSocialNetwork/api/user/view-profile/${username}`);

        let searchData = axiosData.data;
        
        console.log(searchData.username + " : " + profile.username);
        
        if(searchData.username==='test1' && profile.username!=='test1'){
            setProfile(searchData);
            console.log('--------------checkpont 3-----------------')
        }
        else if(searchData.username!=='test1'){
            console.log('--------------checkpont 4------------------')
            setProfile(searchData);
        }
        console.log('--------checkpont 5---------')
        console.log(searchData)
        console.log('-----------------')
    }  


    const history = useHistory() 
    let myUri :string = history.location.pathname;
    if(profile.username!==myUri.slice(14))axiosProfileFetcher(myUri.slice(14));

    useEffect(() => {
        return history.listen((location) => { 
            console.log(`You changed the page to: ${location.pathname}`) 
            let myPath = location.pathname;
            let slicedUri = myPath.slice(14);
            console.log(profile.username + ' my sliced uri: ' +slicedUri)
            if(profile.username!==slicedUri) axiosProfileFetcher(slicedUri);
        }) 
    },[history]) 





    return(
        <div id="logged-main">  
            <Container>
                {profile.username==='test1'?(
                <Row>
                    <Col xs={2}></Col>
                    <Col xs={7}>
                    </Col>
                    <Col xs={3}>
                    <NoProfile />
                    </Col>
                </Row>):(
                    <Row>
                    <Col xs={2}></Col>
                    <Col xs={7}>
                        {props.stateData.postList.map(function(val:any, index:number){
                            if(val.postedBy.username===profile.username)return <Post key={val.postId} postData={val} />
                        })}
                    </Col>
                    <Col xs={3}>
                    <Profile 
                        userInState={{currentUser:profile}}
                    />
                    </Col>
                </Row>
                )
                    }
            </Container>

            
            
        </div>
    );
}


export default ViewUserProfile;