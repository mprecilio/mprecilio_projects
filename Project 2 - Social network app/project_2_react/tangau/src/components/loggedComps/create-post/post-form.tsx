import React, { SyntheticEvent } from "react";
import Card from 'react-bootstrap/Card';
import Form from 'react-bootstrap/Form';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Storage } from 'aws-amplify'
import axios from 'axios'
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';



const useStyles = makeStyles({
    root: {
      
    },
    postTextInput: {
      fontSize: 14,
      width:'100%'
    },
    pos: {
      marginBottom: 12
    }
  });

  interface IProps{
    userInState: any
  }



  let result:any = null;

const PostForm = (props:IProps) => {
    const classes = useStyles();
  

    console.log(props.userInState);
    
    const [text, setText] = React.useState('');

    async function handleImageChange(e : any) {
        const file = e.target.files[0];
        result = await Storage.put(file.name, file)
        console.log("result", result)
      
      }
    


    let axiosFetcher = async (eve: SyntheticEvent) => {

        eve.preventDefault();
  
        console.log('yoo this is the result: ', result);
        
        await axios.post('http://localhost:8080/RevatureSocialNetwork/api/post/new-post', {
            "postWrittenContent": text.trim(),
            "createdByUser": props.userInState.currentUser.username
        });

        axios.post('http://localhost:8080/RevatureSocialNetwork/api/post/postphoto',{
          "profilePhoto":result.key})
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        })
    }
  
    return (
        <Card style={{ width: '100%', marginTop: '10px', padding: '20px' }}>
            <Form>
                <Form.Group className="position-relative mb-3">
                <Form.Label><h2>Create a post</h2></Form.Label>
                <Form.Control
                type="file"
                required
                name="file"
                onChange={handleImageChange}
                />
                </Form.Group>
                <TextField
                className={classes.postTextInput}
                id="outlined-multiline-static"
                label={`What's on your mind? (${text.length}/281) `}
                multiline
                rows={4}
                defaultValue=""
                inputProps={{
                    maxLength: 281,
                  }}
                onChange={(eve) => { (text.length<=281?setText(eve.target.value):console.log(text.length)); }}
                variant="outlined"
                />

                <Button 
                type="submit"
                onClick={axiosFetcher}
                >Submit form</Button>
            </Form>
        </Card>
    );
}

export default PostForm;








