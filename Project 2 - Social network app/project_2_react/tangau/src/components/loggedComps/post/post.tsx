import React, {useState} from 'react';
import Card from 'react-bootstrap/Card';
import Grid from '@material-ui/core/Grid';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import PostPhoto from '../photo-component/post-photo-component'
import axios from 'axios';
import { State } from '../../../state'
import { useSelector } from 'react-redux';
import {IAppState, IPosts} from '../../../state/StateStructure'

interface IProps{
    postData: any;
}

const useStyles = makeStyles((theme) => ({
    root: {
    },
    PostText: {
        postion:'relative',
        textAlign:'left',
        fontSize:'22px'
    },
    PostTitle: {
        textAlign:'left',
        fontWeight:'bold',
        fontSize:'17px'
    },
    PostTextDate: {
        textAlign: 'right'
    }
  }));

const PostComp = (props:IProps) => {
    
    const state:IAppState = useSelector((state: State) => state.login);

    const [like, setLike] = useState(false);
    const [likeCont, setLikeCount] = useState(0);

    let postId = props.postData.postId;

    let handleLike = async ()=>{
        like===false?setLikeCount(likeCont+1):setLikeCount(likeCont);
        if(like===false){
            setLike(true);
            // const axiosPayLoad : any= 
            // await axios.post(`http://localhost:8080/RevatureSocialNetwork/api/post/like-photo`,{
            // "postId" : postId,
            // "createdByUser":state.currentUser?.username
            // });
        }
    }

    let myStr = "";
    let myDate = new Date(props.postData.postTimeCreated);
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
    myStr = myStr.substring(0,17);

    const classes = useStyles();

    const mname = (props.postData.postedBy.mname!==null?`${props.postData.postedBy.mname} `:"");

    return(
        <>
            <Card style={{ width: '100%', marginTop: '10px' }}>
            {props.postData.myPhotos.length===0?null:
            <PostPhoto postData={props.postData}/>
            }   
            <Card.Body>
                <Card.Title
                className={classes.PostTitle}
                >{`@${props.postData.postedBy.username} (${props.postData.postedBy.fname} ${mname}${props.postData.postedBy.lname})`}</Card.Title>
                <Card.Text
                className={classes.PostText}
                >
                    {`${props.postData.postWrittenContent}`}
                </Card.Text>
                <Grid container spacing={3}>
          <Grid item xs={12}sm={4}>
          <Button variant="contained" 
          onClick={handleLike}
          color="secondary" style={{left:'0px'}}>
            {!like?`like (${likeCont})`:`liked (${likeCont})`}
            </Button>
            </Grid>
            <Grid item xs={12} sm={4}>

            </Grid>
            <Grid item xs={12} sm={4}>
                <Card.Text
                className={classes.PostTextDate}
                >
                    {myStr}
                </Card.Text>
            </Grid>
            </Grid>
            </Card.Body>
            </Card>


        </>
    );

}

export default PostComp;








