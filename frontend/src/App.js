import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./components/views/Login";
import Register from "./components/views/Register";
import Forgot from "./components/views/Forgot";
import UserDashboard from "./components/views/UserDashboard";
import ResetPassword from "./components/views/ResetPassword";
const Auth = () => {
  return (
    <Router>
      <Switch>
        <Route path='/login' component={Login} />
        <Route path='/register' component={Register} />
        <Route path='/dashboard' component={UserDashboard} />
        <Route path='/forgot-password' component={Forgot} />
        <Route path='/reset-password' component={ResetPassword} />
        <Route path='/' component={Login} />
      </Switch>
    </Router>
  );
}

export default Auth;
