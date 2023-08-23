import { useState } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import axios from "../../utilities/axios";
const USER_URL = "/user/addUser";
const EMAIL_URL = "/userDetails/getUser";

const Forgot = () => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [validate, setValidate] = useState({});
  const [showPassword, setShowPassword] = useState(false);

  const history = useHistory();
  const location = useLocation();
  const receivedData = location.state?.data;
  const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

  const v1 = PWD_REGEX.test(password);
  const checkEmail = async (e) => {
    let correctEmail = false;
    if(receivedData.key != null){
        setUserName(receivedData.key);
        correctEmail = true;
    }

    return correctEmail;
  };

  const handleSubmit = async (e) => {
        if(v1){
        const callOtpService = await axios.post(
        USER_URL ,
        JSON.stringify({ userName: receivedData.key, password: password }),
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

      console.log("password Updated!");
      }
   };

  const forgotPassword = (e) => {
    e.preventDefault();
    let isValid = checkEmail();

    if(!v1){
       isValid=false;
       alert("Please enter a proper password, must have a smallcase, and special character");
    }
    if (isValid) {
      console.log(receivedData.key);
      history.push("/login");
    }
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
            {/* Original auth form container */}
            <div className="auth-form-container text-start">
              <form
                className="auth-form"
                method="POST"
                onSubmit={forgotPassword}
                autoComplete="off"
              >
                {/* UserName Input */}
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
                      placeholder="New Password"
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
                  </div>
                </div>

                {/* Submit Button */}
                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                    onClick={handleSubmit}
                  >
                    Reset Password
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Forgot;
