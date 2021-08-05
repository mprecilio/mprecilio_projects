// import React from 'react';
import { useSelector } from 'react-redux';
import './App.css';
import './main-style/style.css'
import SignInRoute from './components/notLoggedComps/sign-in-router'
import { State } from './state'
import LoggedRouter from './components/loggedComps/logged-router'


function App() {
  const state = useSelector((state: State) => state.login);

  return (    
    <div className="App" id="app-main">
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
      <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />

      {(state===null)?<SignInRoute />:<LoggedRouter stateData={state}/>}

      
    
    </div>
  );
}

export default App;
