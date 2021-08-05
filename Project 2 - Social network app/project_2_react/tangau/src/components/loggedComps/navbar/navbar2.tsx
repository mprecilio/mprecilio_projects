import React, {useState, SyntheticEvent} from 'react';
import Offcanvas from 'react-bootstrap/Offcanvas';
import Form from 'react-bootstrap/Form';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import { alpha, makeStyles } from '@material-ui/core/styles';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import MenuIcon from '@material-ui/icons/Menu';
import SearchIcon from '@material-ui/icons/Search';
import ButtonMUI from '@material-ui/core/Button';
import axios from 'axios';
import {LoginActionsCreators} from '../../../state'
import { useDispatch } from 'react-redux';
import { bindActionCreators } from 'redux';
import {State} from '../../../state'
import InputBase from '@material-ui/core/InputBase';
import { Link, useHistory } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
  root: {
    position: 'sticky',
    top: "0",
    zIndex: 10,
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  search: {
    position: 'sticky',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  }
,
  NavButtons: {
    position: 'relative',
    minWidth:'365px',
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  margin: {
    margin: theme.spacing(1),
  },
  withoutLabel: {
    marginTop: theme.spacing(3),
  },
  textField: {
    width: '25ch',
  },
  ButtonMui: {
    position: 'absolute',
    bottom: '20px',
    minWidth:'365px',
  },
  MenuTitle: {
    fontSize:'30px',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));

export default function SearchAppBar() {

  const history = useHistory();

    const dispatch = useDispatch();
    const { logout } = bindActionCreators(LoginActionsCreators, dispatch);

    const classes = useStyles();
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const [search, setSearch] = useState('');

    let axiosProfileFetcher = async (eve: SyntheticEvent) => {

      eve.preventDefault();
      
      history.push(`/view-profile/${search}`);

    }

    let axiosFetcher = async (eve: SyntheticEvent) => {

        eve.preventDefault();
        
        console.log('-------------invalidating session')
        await axios.delete('http://localhost:8080/RevatureSocialNetwork/api/user/logout');

        logout();
    }


  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            edge="start"
            className={classes.menuButton}
            color="inherit"
            aria-label="open drawer"
            onClick={handleShow}
          >
            <MenuIcon />
          </IconButton>
          <Typography className={classes.title} variant="h6" noWrap>
            Tangau
          </Typography>
          <div className={classes.search}>
            <Form>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>

              <InputBase
                placeholder="Search…"
                classes={{
                  root: classes.inputRoot,
                  input: classes.inputInput,
                }}
                inputProps={{ 'aria-label': 'search' }}
                onChange={(eve)=>{setSearch(eve.target.value)}}
              />
              <Button 
              style={{display:"none"}}
              type="submit" 
              variant="contained"
              onClick={async (eve)=>{
                //axios call to use the user searched for
                await axiosProfileFetcher(eve);
              }}
              >Go</Button>

                
              </Form>
          </div>
        </Toolbar>
      </AppBar>
      <Offcanvas show={show} onHide={handleClose}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title className={classes.MenuTitle}>Menu</Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body>

                <ButtonGroup
                orientation="vertical"
                color="primary"
                aria-label="vertical outlined primary button group"
                variant="text"
            >
                <ButtonMUI className={classes.NavButtons}><Link to="/live-feed">Live Feed</Link></ButtonMUI>
                <ButtonMUI className={classes.NavButtons}><Link to="/my-profile">My Profile</Link></ButtonMUI>
            </ButtonGroup>

            
            
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
            <ButtonMUI 
                className={classes.ButtonMui}
                variant="contained" 
                color="secondary"
                onClick={axiosFetcher}
                >
                  logout
            </ButtonMUI>
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  );
}