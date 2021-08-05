import React from 'react';
import Nav2 from './navbar/navbar2'
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import {State} from '../../state';
import {IUser, IAppState, IPosts} from '../../state/StateStructure'
import { useSelector } from 'react-redux';
import './navbar/navbar-style.css'
import './logged-main-style.css'
import LiveFeed from './containers/live-feed-container'
import MyProfie from './containers/my-profile-container'
import LoggedRedirect from './logged-redirect'
import { useDispatch } from 'react-redux';
import { bindActionCreators } from 'redux';
import { LoginActionsCreators } from '../../state';
import ViewUserProfile from './containers/search-profile-container'
import axios from 'axios'




interface IProps{
    stateData: any
}

const LoggedRouter = (props:IProps) => {

    const state:IAppState = useSelector((state: State) => state.login);


    const dispatch = useDispatch();
    const { login } = bindActionCreators(LoginActionsCreators, dispatch);

    const username: string = props.stateData.currentUser.username;


    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    let axiosFetcher = async () => {
        const axiosUserPayLoad: any = await axios.get(`http://localhost:8080/RevatureSocialNetwork/api/user/view-profile/${username}`);
        const axiosPostPayLoad: any = await axios.get('http://localhost:8080/RevatureSocialNetwork/api/post/get-posts');

        let currUser:IUser = axiosUserPayLoad.data; 
        let allPosts:IPosts[] = axiosPostPayLoad.data;

        const appState: IAppState = {
            currentUser: currUser,
            postList: allPosts,
        }

        if (state != null){
            login(appState);
        }
  }

    return(
        <div id="logged-main">

            <Router>
                <Nav2 />
                <Switch>
                    <Route path="/live-feed" exact ><LiveFeed stateData={state}/></Route>
                    <Route path="/my-profile" exact ><MyProfie stateData={state}/></Route>
                    <Route path="/view-profile" ><ViewUserProfile stateData={state} uri={window.location.pathname}/></Route>
                    <Route path="/"  ><LoggedRedirect /></Route>
                </Switch>
            </Router>
        </div>
    );
}


export default LoggedRouter;