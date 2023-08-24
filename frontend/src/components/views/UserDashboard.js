import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import AccountCreation from "./AccountCreation";
import AddPayee from "./AddPayee";
import { Switch } from "react-router-dom/cjs/react-router-dom.min";
import AccountDetails from "./AccountDetails";
import TransactionForm from "./TransactionForm";
import ViewTransactions from "./ViewTransactions";
import Sidebar from "../Sidebar";
/*import {
  Row, Col, 
  Button,
  Card,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
} from "reactstrap";
const cardStyle = { width: "16rem" };*/

const UserDashboard = () => {
  return (
    <div className="row g-0 auth-wrapper">
      <Sidebar className= "col-lg-4"/>
      
      <div className="col-12 col-lg-8 auth-main-col text-center">
      <AccountDetails/>
        <Router>
          <Switch>
            <Route path="/user/account-creation" component={AccountCreation} />
            <Route path="/user/add-payee" component={AddPayee} />
            <Route path="/user/account-details" component={AccountDetails} />
            <Route path="/user/transaction-form" component={TransactionForm} />
            <Route path="/user/view-transactions" component={ViewTransactions} />
          </Switch>
        </Router>
      </div>
    </div>
  );
};

export default UserDashboard;

/*
  <div>
    <Row>
      <Col className=".container-fluid">
        <Card {...cardStyle}>
          <CardBody>
            <CardTitle tag="h5">Account Details</CardTitle>
            <CardText>View Existing Accounts</CardText>
            <Link className="mb-3" to="./user/account-details">
              <Button>Button</Button>
            </Link>
          </CardBody>
        </Card>
      </Col>
      <Col className=".container-fluid">
        <Card {...cardStyle}>
          <CardBody>
            <CardTitle tag="h5">Create account</CardTitle>
            <CardText>
              Create a new savings or checking account
            </CardText>
            <Link className="mb-3" to="/user/account-creation">
              <Button>Button</Button>
            </Link>
          </CardBody>
        </Card>
      </Col>
      <Col className=".container-fluid">
        <Card {...cardStyle}>
          <CardBody>
            <CardTitle tag="h5">Add Payee</CardTitle>
            <CardText>Add New Payees</CardText>
            <Link className="mb-3" to="/user/add-payee">
              <Button>Button</Button>
            </Link>
          </CardBody>
        </Card>
      </Col>
      <Col className=".container-fluid">
        <Card {...cardStyle}>
          <CardBody>
            <CardTitle tag="h5">Send Money</CardTitle>
            <CardText>Send Money to Added Payees</CardText>
            <Link className="mb-3" to="/user/transaction-form">
              <Button>Button</Button>
            </Link>
          </CardBody>
        </Card>
      </Col>
      <Col className=".container-fluid">
        <Card {...cardStyle}>
          <CardBody>
            <CardTitle tag="h5">Recent Transactions</CardTitle>
            <CardText>
              View your recent NEFT, RTGS and IMPS Transactions
            </CardText>
            <Link className="mb-3" to="/user/view-transactions">
              <Button>Button</Button>
            </Link>
          </CardBody>
        </Card>
      </Col>
    </Row>
  </div>
*/