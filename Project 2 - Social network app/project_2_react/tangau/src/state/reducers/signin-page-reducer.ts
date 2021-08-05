import { SignInPageAction } from '../actions/signin-page-actions'
import { ActionType } from '../action-types'

const initialState = "signin";


const reducer = (state: string = initialState, action: SignInPageAction)=>{
    switch(action.type){
        case ActionType.SIGNIN:
            return ActionType.SIGNIN;
        case ActionType.SIGNUP:
            return ActionType.SIGNUP;
        default:
            return state;
    }
}

export default reducer;