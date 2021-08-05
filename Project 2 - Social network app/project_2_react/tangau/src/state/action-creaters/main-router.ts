import { ActionType } from "../action-types"
import { Dispatch } from "react";
import { MainRouterAction } from '../actions/main-router-actions'
import {IUser, IUserGender, IUserRole} from '../reducers/login-reducer'


export const loadLF = ()=>{
    return (dispatch: Dispatch<MainRouterAction>) => {
        dispatch({
            type: ActionType.LIVEFEED
        })
    }
}

export const loadMyProfile = ()=>{
    return (dispatch: Dispatch<MainRouterAction>) => {
        dispatch({
            type: ActionType.MYPROFILE
        })
    }
}

export const loadSearch = (searchData: any)=>{
    return (dispatch: Dispatch<MainRouterAction>) => {
        dispatch({
            type: ActionType.SEARCH,
            payload: searchData
        })
    }
}