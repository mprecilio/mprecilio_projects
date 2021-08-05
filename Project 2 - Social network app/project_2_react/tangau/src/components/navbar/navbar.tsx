import React from 'react';
import './navbar-style.css';
import NavbarRouter from './navbar-router';


interface IProps{
    
}



const navbar:React.FC<IProps> = (props:IProps)=>{
    
    return(
        <>
            <div id="navbar-div">
            <NavbarRouter />
            </div>
            <div id="navbar-bottom-grad1"></div>  
            

        </>
    );
}

export default navbar;