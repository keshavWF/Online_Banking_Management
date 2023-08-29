import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Link,
  useHistory,
} from "react-router-dom";
import AccountCreation from "./AccountCreation";
import AddPayee from "./AddPayee";
import { Switch } from "react-router-dom/cjs/react-router-dom.min";
import { Button, Card, CardBody, CardText } from "reactstrap";
import { Row, Col } from "reactstrap";
import AccountDetails from "./AccountDetails";
import TransactionForm from "./TransactionForm";
import ViewTransactions from "./ViewTransactions";
import axios from "../../utilities/axios";
const USER_URL = "/user/fetchUser/";

const UserDashboard = () => {
    const [remember, setRemember] = useState(false);
    const history = useHistory();
    useEffect(() => {
        handle();
        return;// Call your function here
      }, []);
    const handle= async (e) => {
    const res = await axios.get(
            USER_URL + sessionStorage.getItem("username") ,
            {
              headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Headers": "Content-Type",
                "Access-Control-Allow-Credentials": true,
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
              },
              withCredentials: false,
            }
          );
        if(res.data.isAdmin != null && res.data.isAdmin != false){
            console.log(res.data);
            setRemember(true);
            return;
        }
        if(remember === false){
            alert("Account disabled/deactivated");
            history.push("/login");
            return;
         }
    };


  const handleLogout = async (e) => {
    sessionStorage.clear();
    history.push("/login");
  };
  return (
    <div className="row g-0 auth-wrapper">
      <div className="col-12 col-lg-12 auth-main-col text-center">
        
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
                    <img alt="Sample" src="https://fastly.picsum.photos/id/370/300/200.jpg?hmac=2OPcz57c8RNwGYThK48To4ueZ3XX3fDt7KdG37B7alI" />
                    <CardBody>
                      {/* <CardTitle tag="h5">Account Details</CardTitle> */}
                      <CardText>View Existing Accounts</CardText>
                      <Link className="mb-3" to="./user/account-details">
                        <Button>Account Details</Button>
                      </Link>
                    </CardBody>
                  </Card>
                </Col>
                <Col className=".container-fluid">
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://fastly.picsum.photos/id/370/300/200.jpg?hmac=2OPcz57c8RNwGYThK48To4ueZ3XX3fDt7KdG37B7alI" />
                    <CardBody>
                      {/* <CardTitle tag="h5">Create account</CardTitle> */}
                      <CardText>
                        Create a new savings or checking account
                      </CardText>
                      <Link className="mb-3" to="/user/account-creation">
                        <Button>Create account</Button>
                      </Link>
                    </CardBody>
                  </Card>
                </Col>
                <Col className=".container-fluid">
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://fastly.picsum.photos/id/370/300/200.jpg?hmac=2OPcz57c8RNwGYThK48To4ueZ3XX3fDt7KdG37B7alI" />
                    <CardBody>
                      {/* <CardTitle tag="h5">Add Payee</CardTitle> */}
                      <CardText>Add New Payees</CardText>
                      <Link className="mb-3" to="/user/add-payee">
                        <Button>Add Payee</Button>
                      </Link>
                    </CardBody>
                  </Card>
                </Col>
                <Col className=".container-fluid">
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://fastly.picsum.photos/id/370/300/200.jpg?hmac=2OPcz57c8RNwGYThK48To4ueZ3XX3fDt7KdG37B7alI" />
                    <CardBody>
                      {/* <CardTitle tag="h5">Send Money</CardTitle> */}
                      <CardText>Send Money to Added Payees</CardText>
                      <Link className="mb-3" to="/user/transaction-form">
                        <Button>Send Money</Button>
                      </Link>
                    </CardBody>
                  </Card>
                </Col>
                <Col className=".container-fluid">
                  <Card style={{ width: "16rem" }}>
                    <img alt="Sample" src="https://fastly.picsum.photos/id/370/300/200.jpg?hmac=2OPcz57c8RNwGYThK48To4ueZ3XX3fDt7KdG37B7alI" />
                    <CardBody>
                      {/* <CardTitle tag="h5">Recent Transactions</CardTitle> */}
                      <CardText>
                        View your recent NEFT, RTGS and IMPS Transactions
                      </CardText>
                      <Link className="mb-3" to="/user/view-transactions">
                        <Button>Recent Transactions</Button>
                      </Link>
                    </CardBody>
                  </Card>
                </Col>
              </Row>
              <Button onClick={handleLogout} className="mt-3" color="danger">
                Logout
              </Button>
            </div>
          </Switch>
        </Router>
      </div>
    </div>
  );
};

export default UserDashboard;
