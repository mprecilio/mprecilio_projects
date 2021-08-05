import { ActionType } from '../action-types'
import { IAppState } from '../StateStructure'


interface Login{
    type: ActionType.LOGGED
    payload: IAppState
}

interface Register{
    type: ActionType.REGISTER
    payload: IAppState
}

interface Logout{
    type: ActionType.LOGGEDOUT
}

export type LoginActions = Login | Register | Logout