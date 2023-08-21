import React, { useState, useEffect } from "react";
import axios from "axios";

const ACC_URL = "/getAccounts/";


const BankAccount = () => {
  const [accountData, setAccountData] = useState([]);

  useEffect(() => {
    
    axios.get(ACC_URL + sessionStorage.getItem("username"))
      .then(response => {
        setAccountData(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div className="bank-account">
      <h2>Bank Account Information</h2>
      {accountData.length > 0 ? (
        accountData.map((account, index) => (
          <div key={index}>
            <p>Name: {account.userName}</p>
            <p>Account Type: {account.accountType}</p>
            <p>Balance: ${account.balance}</p>
          </div>
        ))
      ) : (
        <p>Loading account information...</p>
      )}
    </div>
  );
};

export default BankAccount;
