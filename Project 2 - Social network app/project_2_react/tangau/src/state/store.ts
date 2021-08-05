import { createStore, applyMiddleware, /* Store, */ compose } from "redux";
import rootReducer from './reducers';
import thunk from 'redux-thunk';

//LET'S CREATE THE STATE STORE (container)
declare global {
    interface Window {
      __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
      devToolsExtension?: typeof compose;
    }
  }
  let devtools: any = window['devToolsExtension'] ? window['devToolsExtension']() : (f:any)=>f;
  let middleware = applyMiddleware(thunk);
  export const store: any = middleware(devtools(createStore))(rootReducer, {});

// export const store = createStore(rootReducer,// composeEnhancers());
//     {},
//     compose(
//       applyMiddleware(thunk),
//       window.devToolsExtension ? window.devToolsExtension() : f:any => f
//     )
// )

