import React, { useState, useEffect } from "react";
import axios from "../../utilities/axios";

const ACC_URL = "/account/getAccount/";

const BankAccount = () => {
  const [accountData, setAccountData] = useState([]);

  useEffect(() => {
    axios
      .get(ACC_URL + sessionStorage.getItem("username"))
      .then((response) => {
        setAccountData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div className="bank-account">
      <h2>Bank Account Information for {sessionStorage.getItem("username")}</h2>
      {accountData.length > 0 ? (
        accountData.map((account, index) => (
          <div key={index}>
            <p>Account Number: {account.accountNumber}</p>
            <p>Account Type: {account.accountType}</p>
            <p>Balance: ${account.accountBalance}</p>
          </div>
        ))
      ) : (
        <p>Loading account information...</p>
      )}
    </div>
  );
};

export default BankAccount;
