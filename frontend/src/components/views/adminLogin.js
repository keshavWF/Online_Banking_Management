import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "../../utilities/axios";
import { Button } from "reactstrap";

const ADMIN_REG_URL = "/user/addUser";

const AdminLogin = () => {
  const [adminUsername, setAdminUsername] = useState("");
  const [adminPassword, setAdminPassword] = useState("");
  const [error, setError] = useState(null);

  const history = useHistory();

  const authenticateAdmin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        ADMIN_REG_URL,
        JSON.stringify({
          userName: adminUsername,
          password: adminPassword,
          role: "ADMIN",
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

      // Successful login, set tokens or session data if needed
      history.push("/login");
    } catch (error) {
      setError("Invalid admin credentials");
      console.error("Error authenticating admin:", error);
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
            <p>Register for Admin account</p>
            <div className="col-12 col-md-7 col-lg-8 auth-main-col text-center auth-form-container text-start">
              <form onSubmit={authenticateAdmin}>
                <div className="username mb-3">
                  <input
                    type="text"
                    placeholder="Admin Username"
                    value={adminUsername}
                    onChange={(e) => setAdminUsername(e.target.value)}
                    required
                  />
                </div>
                <div className="password mb-3">
                  <input
                    type="password"
                    placeholder="Admin Password"
                    value={adminPassword}
                    onChange={(e) => setAdminPassword(e.target.value)}
                    required
                  />
                </div>
                {error && <div className="admin-error">{error}</div>}
                <div className="admin-button">
                  <Button type="submit">Register</Button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminLogin;
