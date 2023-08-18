import React from 'react';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import AccountCreation from './AccountCreation';
import AddPayee from './AddPayee';

const UserRegistered = () => {
    return (
        <div className="row g-0 auth-wrapper">

            <div className="col-12 col-md-7 col-lg-6 auth-main-col text-center">
                <div className="d-flex flex-column align-content-end">
                    <div className="auth-body mx-auto">
                        <h2>Welcome to Bank</h2>
                    </div>
                </div>
                <Router>
                    <div> 
                        <Link to = "/"> Account Details </Link>
                        <Link to = "/user/account-creation"> Create New Account </Link>
                        <Link to = "/user/add-payee"> Add Payee </Link>
                        <Link to = "/"> Send Money </Link>

                        <Route path = "/user/account-creation" component={AccountCreation} />
                        <Route path = "/user/add-payee" component = {AddPayee} />
                    </div>
                </Router>
            </div>

        </div>
    );
}

export default UserRegistered;