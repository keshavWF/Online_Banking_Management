import React, { useState, useEffect } from "react";
import axios from "axios";

const AdminPage = () => {
  const [searchInput, setSearchInput] = useState("");
  const [userList, setUserList] = useState([]);
  const [filteredUsers, setFilteredUsers] = useState([]);

  useEffect(() => {
    // Fetch user list from backend
    axios.get("/user/unapproved",
    {
            headers: {
              "Content-Type": "application/json",
              "Access-Control-Aloow-Headers": "Content-Type",
              "Access-Control-Allow-Credentials": true,
              "Access-Control-Allow-Origin": "*",
              "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
            },
            withCredentials: false,
          }).then((response) => {
      setUserList(response.data);
      setFilteredUsers(response.data);
    });
  }, []);

  // Function to handle search input change
  const handleSearchInputChange = (event) => {
    const inputValue = event.target.value;
    setSearchInput(inputValue);

    // Filter users based on search input
    const filtered = userList.filter((user) =>
      user.username.toLowerCase().includes(inputValue.toLowerCase())
    );
    setFilteredUsers(filtered);
  };

  // Function to handle user approval or rejection
  const handleAction = (userId, action) => {
    // Send request to backend to update user's status
    // Reload user list after successful action
    // Implement this part based on your backend logic
  };

  return (
    <div>
      <h2>Admin Page</h2>
      <div>
        <input
          type="text"
          placeholder="Search users..."
          value={searchInput}
          onChange={handleSearchInputChange}
        />
      </div>
      <div>
        {filteredUsers.map((user) => (
          <div key={user.id}>
            <span>{user.username}</span>
            <button onClick={() => handleAction(user.id, "accept")}>Accept</button>
            <button onClick={() => handleAction(user.id, "reject")}>Reject</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminPage;
