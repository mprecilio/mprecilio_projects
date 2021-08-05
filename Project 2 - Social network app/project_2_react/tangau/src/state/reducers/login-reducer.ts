import { LoginActions } from '../actions/logged-actions'
import { ActionType } from '../action-types'

const initialState: IUser|null = null;

export interface IUser{
    userID: number;
    username: string;
    fname: string;
    mname: string;
    lname: string;
    userEmail:string;
    dateJoined: any;
    userGender: IUserGender;
    userRole: IUserRole;
    profilePhoto: string;
    dateJoinedToStr:string;
}

export interface IProfilePhotos{
    profilePhotoId: number;
    profilePhotoKey: string;
}

export interface IUserGender{
    genderId: number;
    gender: string;
}

export interface IUserRole{
    roleId: number;
    role: string;
}


const reducer = (state: IUser|null = initialState, action: LoginActions)=>{
    switch(action.type){
        case ActionType.LOGGED:
            return action.payload;
        case ActionType.REGISTER:
            return action.payload;
        case ActionType.LOGGEDOUT:
            return null;
        default:
            return state;
    }
}

export default reducer;