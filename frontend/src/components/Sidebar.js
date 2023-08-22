import React from 'react';
import { Link } from 'react-router-dom';
import { Nav, NavItem, NavLink } from 'reactstrap';

const Sidebar = () => {
  const userName = sessionStorage.getItem('userName');

  return (
    <div className="sidebar bg-light">
      <div className="sidebar-header text-center py-4">
        <h4>Welcome, {userName}</h4>
      </div>
      <Nav vertical className="py-3">
        <NavItem>
          <NavLink tag={Link} to="/create-account">
            Create New Account
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={Link} to="/send-money">
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
    </div>
  );
};

export default Sidebar;
