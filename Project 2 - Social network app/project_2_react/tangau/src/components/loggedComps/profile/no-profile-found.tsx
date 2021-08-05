import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from 'react-bootstrap/Card';
import Image from 'react-bootstrap/Image'
import 'bootstrap/dist/css/bootstrap.min.css';

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



const ProfileCard = () => {
    const classes = useStyles();

    // console.log(props.userInState);


    return(
        <>
            <Card className={classes.root} style={{ width: '100%', marginTop: '10px', position:'sticky' }}>
            <Image src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" rounded />
            {/* <Card.Img variant="top" src="https://penntoday.upenn.edu/sites/default/files/2021-07/social-influencer-main.jpg" roundedCircle /> */}
            <Card.Body>
                <Card.Title>(No such user)</Card.Title>
                <Card.Text>
                No user matching that username
                </Card.Text>
                <Card.Text>
                
                </Card.Text>
                <Card.Text>
                </Card.Text>
                {/* <Button variant="primary">Go somewhere</Button> */}
            </Card.Body>
            </Card>


        </>
    );


}

export default ProfileCard;
