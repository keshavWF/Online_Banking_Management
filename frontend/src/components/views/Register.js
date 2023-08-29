import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import Form from "../../utilities/Forms";
import axios from "../../utilities/axios";
const REGISTER_URL = "/userDetails/addUser";
const USERNAME_URL = "/bank/exists";
const LOGIN_URL = "/user/addUser";

const Register = () => {
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [currentaddress, setCurrentAddress] = useState("");
  const [permanentaddress, setPermanentAddress] = useState("");
  const [gender, setGender] = useState("");
  const [dob, setDob] = useState("");
  const [fathername, setFatherName] = useState("");
  const [username, setUsername] = useState("");
  const [phonenumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [validate, setValidate] = useState({});
  const [showPassword, setShowPassword] = useState(false);
  const [checkUsername, setCheckUsername] = useState(false);
  const [aadhar, setAadhar] = useState("");
  const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

  const history = useHistory();
  const validateRegister = () => {
    let isValid = true;

    let validator = Form.validator({
      firstname: {
        value: firstname,
        isRequired: true,
      },
      lastname: {
        value: lastname,
        isRequired: true,
      },
      email: {
        value: email,
        isRequired: true,
        isEmail: true,
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

  const handleSubmit = async (e) => {
    e.preventDefault();

    const validate = validateRegister();

    const v2 = PWD_REGEX.test(password);
    if (!v2) {
      alert(
        "Please enter a proper password, must have a smallcase, uppercase, numerical, and special character"
      );
      return;
    }
    console.log(checkUsername);
    if (!checkUsername) {
      alert("Username already exists. Please enter a unique username");
      return;
    }
    console.log(
      JSON.stringify({
        userName: username,
        firstName: firstname,
        secondName: lastname,
        currentAddress: currentaddress,
        gender: gender,
        dob: dob,
        fatherName: fathername,
        permanentAddress: permanentaddress,
        phoneNumber: phonenumber,
        aadhar: aadhar,
      })
    );

    // console.log(JSON.stringify({ aadhar, current_address: currentaddress, dob, father_name: fathername, first_name: firstname, gender, permanent_address: permanentaddress, phone_number: phonenumber, second_name: lastname, userid:"910293" }));
    const response = await axios.post(
      REGISTER_URL,
      JSON.stringify({
        userName: username,
        firstName: firstname,
        secondName: lastname,
        currentAddress: currentaddress,
        gender: gender,
        dob: dob,
        fatherName: fathername,
        permanentAddress: permanentaddress,
        phoneNumber: phonenumber,
        aadhar: aadhar,
        email: email,
      }),
      {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Aloow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      }
    );
    const credential_response = await axios.post(
      LOGIN_URL,
      JSON.stringify({ userName: username, password: password, isAdmin: "false" }),
      {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Aloow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      }
    );
    history.push("/login");
  };
  const uniqueUsername = async (e) => {
    if(e != ""){
      setUsername(e);
      const resp = await axios.get(USERNAME_URL + "/" + e, {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Aloow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      });

      const res = resp.data;
      if (res.valueOf() === "NO") {
        setCheckUsername(true);
      } else {
        setCheckUsername(false);
      }
    }
    else{
      setUsername(e);
      setCheckUsername(false);
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
            <p>Create your Account</p>
            <div className="auth-form-container text-start">
              <form
                className="auth-form"
                method="POST"
                onSubmit={handleSubmit}
                autoComplete={"off"}
              >
                <div className="username mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.username
                        ? "is-invalid "
                        : ""
                    }`}
                    id="username"
                    name="username"
                    value={username}
                    placeholder="Username"
                    onChange={(e) => uniqueUsername(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate &&
                      validate.validate.username &&
                      checkUsername
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.username
                      ? validate.validate.username[0]
                      : ""}
                  </div>
                </div>

                <div className="firstname mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.firstname
                        ? "is-invalid "
                        : ""
                    }`}
                    id="first name"
                    name="first name"
                    value={firstname}
                    placeholder="First Name"
                    onChange={(e) => setFirstName(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.firstname
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.firstname
                      ? validate.validate.firstname[0]
                      : ""}
                  </div>
                </div>
                <div className="lastname mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.lastname
                        ? "is-invalid "
                        : ""
                    }`}
                    id="lastname"
                    name="lastname"
                    value={lastname}
                    placeholder="Last Name"
                    onChange={(e) => setLastName(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.name
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.name
                      ? validate.validate.name[0]
                      : ""}
                  </div>
                </div>

                <div className="fathername mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.fathername
                        ? "is-invalid "
                        : ""
                    }`}
                    id="fathername"
                    name="fathername"
                    value={fathername}
                    placeholder="Father Name"
                    onChange={(e) => setFatherName(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.fathername
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.fathername
                      ? validate.validate.fathername[0]
                      : ""}
                  </div>
                </div>

                <div className="currentaddress mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.currentaddress
                        ? "is-invalid "
                        : ""
                    }`}
                    id="currentaddress"
                    name="currentaddress"
                    value={currentaddress}
                    placeholder="Current Address"
                    onChange={(e) => setCurrentAddress(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.currentaddress
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.currentaddress
                      ? validate.validate.currentaddress[0]
                      : ""}
                  </div>
                </div>
                <div className="permanentaddress mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.permanentaddress
                        ? "is-invalid "
                        : ""
                    }`}
                    id="permanentaddress"
                    name="permanentaddress"
                    value={permanentaddress}
                    placeholder="Permanent Address"
                    onChange={(e) => setPermanentAddress(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.permanentaddress
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.permanentaddress
                      ? validate.validate.permanentaddress[0]
                      : ""}
                  </div>
                </div>
                <div className="gender mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.gender
                        ? "is-invalid "
                        : ""
                    }`}
                    id="gender"
                    name="gender"
                    value={gender}
                    placeholder="Gender"
                    onChange={(e) => setGender(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.gender
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.gender
                      ? validate.validate.gender[0]
                      : ""}
                  </div>
                </div>
                <div className="dob mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.dob
                        ? "is-invalid "
                        : ""
                    }`}
                    id="dob"
                    name="dob"
                    value={dob}
                    placeholder="Date of Birth"
                    onChange={(e) => setDob(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.dob
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.dob
                      ? validate.validate.dob[0]
                      : ""}
                  </div>
                </div>

                <div className="phonenumber mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.phonenumber
                        ? "is-invalid "
                        : ""
                    }`}
                    id="phonenumber"
                    name="phonenumber"
                    value={phonenumber}
                    placeholder="Phone Number"
                    onChange={(e) => setPhoneNumber(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.phonenumber
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.phonenumber
                      ? validate.validate.phonenumber[0]
                      : ""}
                  </div>
                </div>
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

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.email
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.email
                      ? validate.validate.email[0]
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
                  </div>
                </div>
                <div className="aadhar mb-3">
                  <input
                    type="text"
                    className={`form-control ${
                      validate.validate && validate.validate.aadhar
                        ? "is-invalid "
                        : ""
                    }`}
                    id="aadhar"
                    name="aadhar"
                    value={aadhar}
                    placeholder="Aadhar Number"
                    onChange={(e) => setAadhar(e.target.value)}
                  />

                  <div
                    className={`invalid-feedback text-start ${
                      validate.validate && validate.validate.aadhar
                        ? "d-block"
                        : "d-none"
                    }`}
                  >
                    {validate.validate && validate.validate.aadhar
                      ? validate.validate.aadhar[0]
                      : ""}
                  </div>
                </div>

                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                    to="/login"
                  >
                    Sign Up
                  </button>
                </div>
              </form>

              <hr />
              <div className="auth-option text-center pt-2">
                Have an account?{" "}
                <Link className="text-link" to="/login">
                  Sign in
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
