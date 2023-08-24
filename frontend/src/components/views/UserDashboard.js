import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import AccountCreation from "./AccountCreation";
import AddPayee from "./AddPayee";
import { Switch } from "react-router-dom/cjs/react-router-dom.min";
import {
  Button,
  Card,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
} from "reactstrap";
import { Row, Col } from "reactstrap";
import AccountDetails from "./AccountDetails";
import TransactionForm from "./TransactionForm";
import ViewTransactions from "./ViewTransactions";
//
const UserDashboard = () => {
  return (
    <div className="row g-0 auth-wrapper">
      <div className="col-12 col-lg-12 auth-main-col text-center">
        {/* <div className="d-flex flex-column align-content-flex-start">
                    <div className="auth-body mx-auto">
                        <h2>Bank</h2>
                    </div>
                </div> */}
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
            <div>
              <Row>
                <Col className=".container-fluid">
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://picsum.photos/300/200" />
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
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://picsum.photos/300/200" />
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
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://picsum.photos/300/200" />
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
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://picsum.photos/300/200" />
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
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://picsum.photos/300/200" />
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
          </Switch>
        </Router>
      </div>
    </div>
  );
};

export default UserDashboard;
