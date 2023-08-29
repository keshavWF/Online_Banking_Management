import React, { useState, useEffect } from "react";
import axios from "../../utilities/axios";
import { Button } from "reactstrap";
const USER_LIST = "/user/unapproved";
const ACCEPT_URL = "/bank/updateStatus/";
const DECLINE_URL = "/bank/decline/";

const AdminPage = () => {
  // const [searchInput, setSearchInput] = useState("");
  const [userList, setUserList] = useState([]);
  // const [filteredUsers, setFilteredUsers] = useState([]);

  useEffect(() => {
    // Fetch user list from backend
    axios
      .get("/user/unapproved", {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Aloow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      })
      .then((response) => {
        setUserList(response.data);
      });
  }, []);

  // Function to handle search input change
  const handleRefresh = async (e) => {
    const res = await axios.get(USER_LIST, {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Aloow-Headers": "Content-Type",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
      },
      withCredentials: false,
    });
    setUserList(res.data);
    // setSearchInput(inputValue);

    // Filter users based on search input
    // const filtered = userList.filter((user) =>
    //   user.username.toLowerCase().includes(inputValue.toLowerCase())
    // );
    // setFilteredUsers(filtered);
  };

  // Function to handle user approval or rejection
  const handleAccept = async (username) => {
    const res = await axios.post(ACCEPT_URL + username, {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Aloow-Headers": "Content-Type",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
      },
      withCredentials: false,
    });
    handleRefresh();
    // Send request to backend to update user's status
    // Reload user list after successful action
    // Implement this part based on your backend logic
  };
  const handleDecline = async (username) => {
    const res = await axios.post(DECLINE_URL + username, {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Aloow-Headers": "Content-Type",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
      },
      withCredentials: false,
    });
    handleRefresh();
  };
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh",
      }}
    >
      <div style={{ display: 'flex', alignItems: 'center' }}>
        <h2>unapproved Users' List</h2>
        <Button
          color="primary"
          onClick={handleRefresh}
          style={{ marginLeft: "10px" }}
        >
          Refresh
        </Button>
      </div>
      <ul style={{ listStyle: 'none', padding: '1' }} >
        {userList.map((user) => (
          <li key={user.username} style={{ marginBottom: '10px', display: 'flex', alignItems: 'center' }}>
          <span style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>{user.username}</span>
            <Button color="primary" onClick={() => handleAccept(user.username)} style={{ marginLeft: "20px" }}>Accept </Button>
            <Button onClick={() => handleDecline(user.username)} style={{ marginLeft: "20px" }}>Decline</Button>  
        </li>
          // <li key={user.username}>
          //   {user.username}
          //   <Button
          //     color="primary"
          //     onClick={() => handleAccept(user.username)}
          //     style={{ marginLeft: "20px" }}
          //   >
          //     Accept
          //   </Button>
          //   <Button
          //     color="primary"
          //     onClick={() => handleDecline(user.username)}
          //     style={{ marginLeft: "20px" }}
          //   >
          //     Decline
          //   </Button>
          // </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminPage;
