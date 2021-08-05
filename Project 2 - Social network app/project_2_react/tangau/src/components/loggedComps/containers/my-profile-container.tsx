import React from 'react';
import Post from '../post/post'
import PostForm from '../create-post/post-form'
import Profile from '../profile/profile-container'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import '../navbar/navbar-style.css'




interface IProps{
    stateData: any
}

const MyProfie = (props:IProps) => {







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
                            if(val.postedBy.username===props.stateData.currentUser.username)return <Post key={val.postId} postData={val} />
                            return null;
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


export default MyProfie;