import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

// Define the initial state of the application

const initialState = {};
const middleware = [thunk];

let store;

// Enable extension if it is Chrome
if (window.navigator.userAgent.includes("Chrome")) {
  store = createStore(
    rootReducer,
    initialState,
    compose(
      applyMiddleware(...middleware),
      window.__REDUX_DEVTOOLS_EXTENSION__ &&
        window.__REDUX_DEVTOOLS_EXTENSION__()
    )
  );
} else {
  store = createStore(
    rootReducer,
    initialState,
    compose(applyMiddleware(...middleware))
  );
}

export default store;
