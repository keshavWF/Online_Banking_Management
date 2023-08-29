import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "../../utilities/axios";

const ADMIN_REG_URL = "/user/addUser";

const AdminLogin = () => {
  const [adminUsername, setAdminUsername] = useState("");
  const [adminPassword, setAdminPassword] = useState("");
  const [error, setError] = useState(null);

  const history = useHistory();

  const authenticateAdmin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(ADMIN_REG_URL,
      JSON.stringify({ userName: adminUsername, password: adminPassword, role: "ADMIN" }),
     {
       headers: {
         "Content-Type": "application/json",
         "Access-Control-Aloow-Headers": "Content-Type",
         "Access-Control-Allow-Credentials": true,
         "Access-Control-Allow-Origin": "*",
         "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
       },
       withCredentials: false,
     });

      // Successful login, set tokens or session data if needed
      history.push("/login");
    } catch (error) {
      setError("Invalid admin credentials");
      console.error("Error authenticating admin:", error);
    }
  };

  return (
    <div className="admin-login-container">
      <h2>Admin Register</h2>
      <form onSubmit={authenticateAdmin}>
        <div className="admin-input">
          <input
            type="text"
            placeholder="Admin Username"
            value={adminUsername}
            onChange={(e) => setAdminUsername(e.target.value)}
            required
          />
        </div>
        <div className="admin-input">
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
          <button type="submit">Register</button>
        </div>
      </form>
    </div>
  );
};

export default AdminLogin;
