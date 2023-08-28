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

const Auth = () => {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/register" component={Register} />
        <Route path="/dashboard" component={UserDashboard} />
        <Route path="/forgot-password" component={Forgot} />
        <Route path="/reset-password" component={ResetPassword} />
        <Route path="/user/account-creation" component={AccountCreation} />
        <Route path="/user/add-payee" component={AddPayee} />
        <Route path="/user/account-details" component={AccountDetails} />
        <Route path="/user/transaction-form" component={TransactionForm} />
        <Route path="/user/view-transactions" component={ViewTransactions} />
        <Route path="/" component={Login} />
      </Switch>
    </Router>
  );
};

export default Auth;
