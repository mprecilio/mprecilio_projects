import { ActionType } from "../action-types"
import { Dispatch } from "react";
import { LoginActions } from '../actions/logged-actions'
import { IAppState } from '../StateStructure'



export const login = (loggedinData: IAppState)=>{
    return (dispatch: Dispatch<LoginActions>) => {
        dispatch({
            type: ActionType.LOGGED,
            payload: loggedinData
        })
    }
}

export const register = (loggedinData: IAppState)=>{
    return (dispatch: Dispatch<LoginActions>) => {
        dispatch({
            type: ActionType.LOGGED,
            payload: loggedinData
        })
    }
}

export const logout = ()=>{
    return (dispatch: Dispatch<LoginActions>) => {
        dispatch({
            type: ActionType.LOGGEDOUT
        })
    }
}