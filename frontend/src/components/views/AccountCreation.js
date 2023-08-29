import { useHistory } from "react-router";
import axios from "../../utilities/axios";
import React, { useState } from "react";
import Form from "../../utilities/Forms";
import Sidebar from "../Sidebar";

const ADD_ACCOUNT_URL = "/account/addAccount/";

// Account Number is auto-generated

// userID to be pulled in from the logged in state.

const AccountCreation = () => {
  const [accountType, setAccountType] = useState("");
  const [aadhar, setAadhar] = useState("");
  const [validate, setValidate] = useState({});
const history = useHistory();
  const validateAccountCreation = () => {
    let isValid = true;

    let validator = Form.validator({
      accountType: {
        value: accountType,
        isRequired: true,
      },
      aadhar: {
        value: aadhar,
        isRequired: true,
      },
    });

    if (validator !== null) {
      setValidate({
        validate: validator.errors,
      });

      isValid = false;
    }
    return isValid;
  };

  const accountCreation = async (e) => {
    e.preventDefault();

    const validate = validateAccountCreation();

    // if (validate) {
    //   setAccountType();
    //   setAadhar("");
    // }
    const auth = "Bearer " + sessionStorage.getItem("token");
    const account_response = await axios.post(
      ADD_ACCOUNT_URL+sessionStorage.getItem("username"),
      JSON.stringify({ accountType: accountType }),
      {
        headers: {
          "Content-Type": "application/json",
          "Authorization": auth ,
          "Access-Control-Allow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      }
    );
    console.log(account_response);
    history.push("/dashboard");
  };

  return (
    <div className="row g-0 auth-wrapper">
      <div className="col-12 col-lg-9 auth-main-col text-center">
        <div className="d-flex flex-column align-content-end">
          <div className="auth-body mx-auto">
            <h2>Create New Account</h2>
            <div className="auth-form-container text-start">
              <form
                className="auth-form"
                method="POST"
                onSubmit={accountCreation}
                autoComplete={"off"}
              >
                <div className="accountType mb-3">
                  <input
                    type="text"
                    id="accountType"
                    name="accountType"
                    value={accountType}
                    placeholder="Account Type"
                    onChange={(e) => setAccountType(e.target.value)}
                  />
                </div>

                <div className="aadhar mb-3">
                  <input
                    type="text"
                    id="aadhar"
                    name="aadhar"
                    value={aadhar}
                    placeholder="Aadhar Number"
                    onChange={(e) => setAadhar(e.target.value)}
                  />
                </div>

                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                  >
                    Add Account
                  </button>
                </div>
              </form>

              <hr />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountCreation;
