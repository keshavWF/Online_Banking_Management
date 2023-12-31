import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import Form from "../../utilities/Forms";
import axios from "../../utilities/axios";
const AUTH_URL = "/user/authenticate";
const CAN_LOGIN_URL = "/bank/canLogin";
const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);
  const [validate, setValidate] = useState({});
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("null");

  const [selectedRole, setSelectedRole] = useState("USER");
  const toggleRole = () => {
      setSelectedRole((prevRole) => (prevRole === "USER" ? "ADMIN" : "USER"));
  };

  const history = useHistory();
  const validateLogin = () => {
    let isValid = true;

    let validator = Form.validator({
      username: {
        value: username,
        isRequired: true,
      },
      password: {
        value: password,
        isRequired: true,
        minLength: 6,
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

  const authenticate = async (e) => {
    e.preventDefault();

    const validate = validateLogin();

    console.log(
      JSON.stringify({ userName: username, password: password, isAdmin: "No" })
    );
    //console.log(selectedRole);
    //console.log(CAN_LOGIN_URL + "/" + username + "/" + password + "/" + selectedRole,);
    const res = await axios.get(
      CAN_LOGIN_URL + "/" + username + "/" + password + "/" + selectedRole,
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
    if (res.data.valueOf() === "IncorrectCredentials") {
          alert("IncorrectCredentials");
          return;
    }

    const res2 = await axios.get(
        AUTH_URL + "/" + username + "/" + password,
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
    ).catch(error=> {
         if (error.response && error.response.status === 403) {
           alert("Invalid Credentials")
           console.log('Access forbidden. You do not have permission.');
         } else {
           console.log('An error occurred.');
         }
         return;
    });


    if (res.data.valueOf() === "UserDoesNotExists") {
      alert("you don't have a account with us, please sign up.");
      return;
    }

    //console.log(res2.data.token);
    sessionStorage.setItem("username", username);
    sessionStorage.setItem("isLoggedin", "true");
    sessionStorage.setItem("token", res2.data.token);
    sessionStorage.setItem("role", setSelectedRole);

    console.log(selectedRole.valueOf() === "USER");
    if(selectedRole.valueOf() === "USER")
        history.push("/dashboard");
    else
        history.push("/adminPage");
  };

  const togglePassword = (e) => {
    if (showPassword) {
      setShowPassword(false);
    } else {
      setShowPassword(true);
    }
  };

  return (
    <div className="row g-0 auth-wrapper">
      <div className="col-12 col-md-5 col-lg-6 h-100 auth-background-col">
        <div className="auth-background-holder"></div>
        <div className="auth-background-mask"></div>
      </div>

      <div className="col-12 col-md-7 col-lg-6 auth-main-col text-center">
        <div className="d-flex flex-column align-content-end">
          <div className="auth-body mx-auto">
            <p>Login to your account</p>
            <div className="auth-form-container text-start">
              <form
                className="auth-form"
                method="POST"
                onSubmit={authenticate}
                autoComplete={"off"}
              >
                <div className="username mb-3">
                  <input
                    type="username"
                    className={`form-control ${
                      validate.validate && validate.validate.username
                        ? "is-invalid "
                        : ""
                    }`}
                    id="username"
                    name="username"
                    value={username}
                    placeholder="username"
                    onChange={(e) => setUsername(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.username
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.username
                      ? validate.validate.username[0]
                      : ""}
                  </div>
                </div>

                <div className="password mb-3">
                  <div className="input-group">
                    <input
                      type={showPassword ? "text" : "password"}
                      className={`form-control ${
                        validate.validate && validate.validate.password
                          ? "is-invalid "
                          : ""
                      }`}
                      name="password"
                      id="password"
                      value={password}
                      placeholder="Password"
                      onChange={(e) => setPassword(e.target.value)}
                    />

                    <button
                      type="button"
                      className="btn btn-outline-primary btn-sm"
                      onClick={(e) => togglePassword(e)}
                    >
                      <i
                        className={
                          showPassword ? "far fa-eye" : "far fa-eye-slash"
                        }
                      ></i>{" "}
                    </button>

                    <div
                      className={`invalid-feedback text-start ${
                        validate.validate && validate.validate.password
                          ? "d-block"
                          : "d-none"
                      }`}
                    >
                      {validate.validate && validate.validate.password
                        ? validate.validate.password[0]
                        : ""}
                    </div>
                    <div className="mb-3">
                            <button
                              type="button"
                              className="btn btn-outline-primary btn-sm"
                              onClick={toggleRole} // Attach toggle function to button click
                            >
                              {selectedRole === "USER" ? "USER" : "ADMIN"}
                            </button>
                          </div>
                  </div>

                  <div className="extra mt-3 row justify-content-between">
                    
                    <div className="col-8">
                      <div className="forgot-password text-end">
                        <Link to="/forgot-password">Forgot password?</Link>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                  >
                    Log In
                  </button>
                </div>
              </form>

              <hr />
              <div className="auth-option text-center pt-2">
                No Account?{" "}
                <Link className="text-link" to="/register">
                  Sign up{" "}
                </Link>
              </div>
              <hr />
                <div className="auth-option text-center pt-2">
                  For Admin:{" "}
                  <Link className="text-link" to="/adminLogin">
                    Admin Register{" "}
                  </Link>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
