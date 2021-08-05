import { ActionType } from '../action-types'

interface SigninPage{
    type: ActionType.SIGNIN
}

interface SignupPage{
    type: ActionType.SIGNUP
}

export type SignInPageAction = SigninPage | SignupPage