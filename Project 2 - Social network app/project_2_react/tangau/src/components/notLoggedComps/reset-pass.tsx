import React, {useState, SyntheticEvent} from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import axios from 'axios';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
  invalidEmailDiv: {
    color: 'red'
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
    width: '100%',
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));

export default function PasswordReset() {

  const classes = useStyles();


  const [valid, setValid] = useState('true');

    const handleInvalidChange = () => {
        setValid('false');
    };
    const handleValidChange = () => {
        setValid('true');
    };


    const [validToken, setValidToken] = useState('true');
    const handleInvalidTokenChange = () => {
        setValidToken('false');
    };
    const handleValidTokenChange = () => {
        setValidToken('true');
    };


  const [token, setToken] = useState('');
  const [password, setPassword] = useState('');
  const [passwordComf, setPasswordComf] = useState('');
  const [email, setEmail] = useState('');


  const handleEmailChange = (event: any) => {
    setEmail(event.target.value);
  };

  const handleTokenChange = (event: any) => {
    setToken(event.target.value);
  };

  const handlePasswordChange = (event: any) => {
    setPassword(event.target.value);
  };

  const handlePasswordComfChange = (event: any) => {
    setPasswordComf(event.target.value);
  };


  let axiosFetcher = async (eve: SyntheticEvent) => {

    eve.preventDefault();

    if(password!==passwordComf) {
        handleInvalidChange();
        return;
    }else{
        handleValidChange();
    }
    
    const axiosPayLoad: any = await axios.post('http://localhost:8080/RevatureSocialNetwork/api/user/reset-pass', {
      "token": token,
      "password": password,
      "userEmail": email,
    });

    const axiosData: any = axiosPayLoad.data;

    console.log("axiosdata: ", axiosData);

    if (axiosData === "") {
        console.log('Invalid token!!!')
        handleInvalidTokenChange();
    }
    else{
        handleValidTokenChange();
        window.location.href = "http://localhost:3000/signin";
    }
}





  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        {valid==='true'?<div></div>: <div className={classes.invalidEmailDiv}>Passwords entered do not match!</div> }
        {validToken==='true'?<div></div>: <div className={classes.invalidEmailDiv}>Invalid token entered</div> }
        <form className={classes.form} noValidate>
          <Grid container spacing={3}>
          <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                value={email}
                onChange={handleEmailChange}
              />
            </Grid>
          <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="token"
                label="Token"
                name="token"
                autoComplete="token"
                value={token}
                onChange={handleTokenChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="New password"
                type="password"
                id="password"
                autoComplete="current-password"
                value={password}
                onChange={handlePasswordChange}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="passwordComf"
                label="Confirm password"
                type="password"
                id="passwordComf"
                autoComplete="current-password"
                value={passwordComf}
                onChange={handlePasswordComfChange}
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={axiosFetcher}
          >
            Sign Up
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/signin" variant="body2">
                Return to sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={5}>
        <Copyright />
      </Box>
    </Container>
  );
}