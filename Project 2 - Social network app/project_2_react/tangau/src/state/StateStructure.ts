export interface IAppState {
    currentUser: IUser | null;
    postList: IPosts[] | null;
}

//SUPPORTING STATE MODELS
export interface IUser{
    userID: number;
    username: string;
    fname: string;
    mname: string;
    lname: string;
    userEmail:string;
    dateJoinedToStr: string;
    userGender: IUserGender;
    userRole: IUserRole;
    profilePhoto: string;
}

export interface IPosts{
    postId: number;
    postWrittenContent: string;
    postedBy: string[];
    postsPhotos: IPostPhotos[];
    postOwner:IUser[]
}
export interface IPostPhotos{
    postPhotoId: number;
    postPhotoKey: string;
}

export interface IUserGender{
    genderId: number;
    gender: string;
    pronouns: string;
}

export interface IUserRole{
    roleId: number;
    role: string;
}

export const initialState: IAppState = {
    currentUser: null,
    postList: null
}