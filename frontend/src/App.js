import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./components/views/Login";
import Register from "./components/views/Register";
import Forgot from "./components/views/Forgot";
import UserDashboard from "./components/views/UserDashboard";
import ResetPassword from "./components/views/ResetPassword";
import AccountCreation from "./components/views/AccountCreation";
import AddPayee from "./components/views/AddPayee";
import ViewTransactions from "./components/views/ViewTransactions";
import TransactionForm from "./components/views/TransactionForm";
import AccountDetails from "./components/views/AccountDetails";

import adminLogin from "./components/views/adminLogin";
import AdminDashboard from "./components/views/AdminDashboard";
const Auth = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/dashboard" component={UserDashboard} />
        <Route path="/forgot-password" component={Forgot} />
        <Route path="/reset-password" component={ResetPassword} />
        <Route path='/adminLogin' component={adminLogin} />
        <Route path='/adminPage' component={AdminDashboard} />
        <Route path='/' component={Login} />
      </Switch>
    </Router>
  );
};

export default Auth;
