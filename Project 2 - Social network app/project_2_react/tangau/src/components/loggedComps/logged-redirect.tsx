import React from 'react'
import { useHistory } from 'react-router-dom';


export default function SignInRedirect() {
    const history = useHistory();

    return(
        <>
            {history.push('/live-feed')}
        </>
    );

}