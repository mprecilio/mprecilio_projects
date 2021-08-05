import { ActionType } from '../action-types'
import { IUser } from '../StateStructure'


interface MyProfile{
    type: ActionType.MYPROFILE
}

interface LiveFeed{
    type: ActionType.LIVEFEED
}

interface Search{
    type: ActionType.SEARCH
    payload: any
}

export type MainRouterAction = MyProfile | LiveFeed | Search