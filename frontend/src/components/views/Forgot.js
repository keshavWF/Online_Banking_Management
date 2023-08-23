import { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import axios from "../../utilities/axios";
const OTP_URL = "/otp/send";
const VALIDATE_URL = "/bank/checkOTP";
const EMAIL_URL = "/userDetails/getUser";

const Forgot = () => {
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [validate, setValidate] = useState({});
  const [username, setUserName] = useState("");
  const [sentEmailStatus, setSentEmailStatus] = useState(false);

  const history = useHistory();

  const handleSubmit = async (e) => {
    if(email === ""){
            alert("Enter Email Address");
    }
    else{
    const callGetUserName = await axios.get(
    EMAIL_URL + "/" + email,
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

    if(callGetUserName.data === "InvalidEmail"){
        alert("Unregistered Email Address");
    }
    else{
        setSentEmailStatus(true);
        setUserName(callGetUserName.data);
        const callOtpService = await axios.post(
            OTP_URL + "?email=" + email,
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
          alert("Otp sent to Email Address");
      }
    }
   };
   const handleFetch = async (e) => {
       const checkOTP = await axios.get(
           VALIDATE_URL + "/" + email + "/" + otp,
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

       if(checkOTP.data.valueOf() === "INVALID"){
            alert("Invalid OTP");
       }
       else{
            console.log("Correct OTP!!");
            const dataToSend = { key: username };
            history.push({
                pathname:"/reset-password",
                state: {data: dataToSend}
            });
       }
   };

  const validateForgotPassword = () => {
    let isValid = true;

    if (!email) {
      setValidate({
        email: ["The email is required."],
      });
      isValid = false;
    } else {
      setValidate({
        email: [],
      });
    }

    if(!sentEmailStatus) {
        isValid = false;
    }

    return isValid;
  };

  const forgotPassword = (e) => {
    e.preventDefault();
    const isValid = validateForgotPassword();

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
                {/* Email Input */}
                <div className="email mb-3">
                  <input
                      type="email"
                      className={`form-control ${
                        validate.validate && validate.validate.email
                          ? "is-invalid "
                          : ""
                      }`}
                      id="email"
                      name="email"
                      value={email}
                      placeholder="Email"
                      onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                {/* OTP Input */}
                <div className="otp mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.otp && validate.otp.length > 0 ? "is-invalid " : ""
                    }`}
                    id="otp"
                    name="otp"
                    value={otp}
                    placeholder="Enter OTP"
                    onChange={(e) => setOtp(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.otp && validate.otp.length > 0 ? "d-block" : "d-none"
                    }`}
                  >
                    {validate.otp && validate.otp.length > 0 ? validate.otp[0] : ""}
                  </div>
                </div>


                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                    onClick={handleSubmit}
                  >
                    Send OTP
                  </button>
                </div>


               <div className="text-center mt-2">
                    <button
                      type="button"
                      className="btn btn-secondary w-100 theme-btn mx-auto"
                      onClick={handleFetch}
                    >
                      Verify OTP
                    </button>
              </div>
              </form>
              <hr />
              <div className="auth-option text-center pt-2">
                <Link className="text-link" to="/login">
                  Back to Login
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Forgot;
