import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
//import { AuthContextProvider } from "./context/authContext";
import { DarkModeContextProvider } from "./context/darkModeContext";
import { store } from './app/store';
import { Provider } from 'react-redux';


const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <DarkModeContextProvider>
      {/* <AuthContextProvider> */}
      <Provider store={store}>
        <App />
      </Provider>
      {/* </AuthContextProvider> */}
    </DarkModeContextProvider>
  </React.StrictMode>
);
