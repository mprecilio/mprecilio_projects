import React from 'react';
import './navbar-router-style.css'
import {BrowserRouter, Route, Link} from 'react-router-dom';

interface IProps {
}

const MyReactRouterComponent: React.FC<IProps> = (props: IProps)=>{

    return (
        <> 

            <BrowserRouter>

                <Link to="/">
                    <Route path="/path/1"></Route>
                </Link>
                <Link to="/path/1">
                    <img src="https://www.myantics.cc/home.png" alt="" id="home-img"></img>
                </Link>
                <Link to="/path/2">
                    <img src="https://www.myantics.cc/live.png" alt="" id="live-img"></img>
                </Link>
                <Link to="/path/3">
                    <img src="https://www.myantics.cc/profile.png" alt="" id="profile-img"></img>
                </Link>

                <Route path="/path/1" render={()=><h2>{/* in first route */}</h2>}></Route>
                <Route path="/path/2" render={()=><h2>{/* in second route */}</h2>}></Route>
                <Route path="/path/3" render={()=><h2>{/* in third route */}</h2>}></Route>
            </BrowserRouter>

            {/* <h4>END OF ROUTE DEMO</h4> */}
        </>
    );
}

export default MyReactRouterComponent;