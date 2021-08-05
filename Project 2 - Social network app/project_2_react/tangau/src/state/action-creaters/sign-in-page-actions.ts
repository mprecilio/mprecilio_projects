import { ActionType } from "../action-types"
import { Dispatch } from "react";
import { SignInPageAction } from '../actions/signin-page-actions'





export const loadSignup = ()=>{
    return (dispatch: Dispatch<SignInPageAction>) => {
        dispatch({
            type: ActionType.SIGNUP
        })
    }
}

export const loadSignin = ()=>{
    return (dispatch: Dispatch<SignInPageAction>) => {
        dispatch({
            type: ActionType.SIGNIN
        })
    }
}

