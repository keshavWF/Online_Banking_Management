import React from 'react';
import { Link } from 'react-router-dom';
import { Switch } from 'react-router-dom/cjs/react-router-dom.min';
import { Nav, NavItem, NavLink } from 'reactstrap';
import {
  BrowserRouter as Router,
  Route
} from "react-router-dom";
import AccountCreation from './views/AccountCreation';
import AddPayee from './views/AddPayee';
import AccountDetails from './views/AccountDetails';
import TransactionForm from "./views/TransactionForm";
import ViewTransactions from "./views/ViewTransactions";
const Sidebar = () => {
  const userName = sessionStorage.getItem('userName');

  return (
    <div className="sidebar bg-light">
      <div className="sidebar-header text-center py-4">
        <h4>Welcome, {userName}</h4>
      <Router>
          <Switch>
            <Route path="/user/account-creation" component={AccountCreation} />
            <Route path="/user/add-payee" component={AddPayee} />
            <Route path="/user/account-details" component={AccountDetails} />
            <Route path="/user/transaction-form" component={TransactionForm} />
            <Route
              path="/user/view-transactions"
              component={ViewTransactions}
            />
      <Nav vertical className="py-3">
        <NavItem>
          <NavLink tag={Link} to="/user/create-account">
            Create New Account
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={Link} to="/user/send-money">
            Send Money
          </NavLink>
        </NavItem>
        <NavItem >
          <NavLink tag={Link} to="/add-payee">
            Add New Payee
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={Link} to="/view-transactions">
            View Recent Transactions
          </NavLink>
        </NavItem>
      </Nav>
    </Switch>
    </Router>
    </div>
    </div>
  );
};

export default Sidebar;
