import { MainRouterAction } from '../actions/main-router-actions'
import { ActionType } from '../action-types'
import {IUser} from './login-reducer'

const initialState = "livefeed";


const reducer = (state: any|null = initialState, action: MainRouterAction)=>{
    switch(action.type){
        case ActionType.MYPROFILE:
            return "myprofile";
        case ActionType.LIVEFEED:
            return "livefeed";
        case ActionType.SEARCH:
            return "search";
        default:
            return "myprofile";
    }
}

export default reducer;