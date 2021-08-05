import React from 'react';
import Post from '../post/post'
import PostForm from '../create-post/post-form'
import Profile from '../profile/profile-container'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import { useSelector } from 'react-redux';
import {State} from '../../../state'
import '../navbar/navbar-style.css'




interface IProps{
    stateData: any
}

const LiveFeed = (props:IProps) => {



    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const state = useSelector((state: State) => state.login);




    return(
        <div id="logged-main">  
            <Container>
                <Row>
                    <Col xs={2}></Col>
                    <Col xs={7}>
                        
                        <PostForm
                            userInState={props.stateData}
                        />
                        {props.stateData.postList.map(function(val:any, index:number){
                            return <Post key={val.postId} postData={val} />
                        })}
                    </Col>
                    <Col xs={3}>
                    <Profile 
                        userInState={props.stateData}
                    />
                    </Col>
                </Row>
            </Container>

        </div>
    );
}


export default LiveFeed;