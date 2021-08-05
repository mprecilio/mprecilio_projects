import SignIn from './sign-in';
import SignUp from './sign-up';
import SendEmail from './reset-pass-send-email'
import PasswordReset from './reset-pass'
import SignInRedirect from './sign-in-redirect'
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

interface IProps{
    
}

const SignInRouter = (props:IProps) => {


    return(
        <>
            <Router>
                <Switch>
                    <Route path="/signin" exact component={SignIn}/>
                    <Route path="/signup" exact component={SignUp}/>
                    <Route path="/send-email" exact component={SendEmail}/>
                    <Route path="/reset-pass" exact component={PasswordReset}/>
                    <Route path="/" component={SignInRedirect}/>
                </Switch>
            </Router>
            
        </>
    );
}


export default SignInRouter;