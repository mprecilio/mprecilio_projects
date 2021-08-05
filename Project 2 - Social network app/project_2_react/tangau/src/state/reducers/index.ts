import { combineReducers } from "redux";
import signinPageReducer from "./signin-page-reducer";
import loginReducer from './login-reducer'
import mainRouterReducer from './main-router-reducer'

//THIS IS A COMBINATION OF ALL REDUCERS IN YOUR SYSTEM, we'll only have one for our example
const rootReducer = combineReducers({
    signin : signinPageReducer,
    login: loginReducer,
    mainRouter: mainRouterReducer,
})


export default rootReducer;

export type State = ReturnType<typeof rootReducer>;
