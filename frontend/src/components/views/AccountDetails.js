import React, { useState, useEffect } from "react";
import { Container } from "reactstrap";
import axios from "../../utilities/axios";

const ACC_URL = "/account/getAccount/";

const BankAccount = () => {
  const [accountData, setAccountData] = useState([]);

  useEffect(() => {
    const auth = "Bearer " + sessionStorage.getItem("token").toString();
    console.log(auth);
    axios
      .get(ACC_URL + sessionStorage.getItem("username"),
       {headers: {
                "Content-Type": "application/json",
                "Authorization": auth,
                "Access-Control-Allow-Headers": "Content-Type",
                "Access-Control-Allow-Credentials": true,
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
              },
              withCredentials: false,
            })
      .then((response) => {
        setAccountData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <Container>
      <div className="bank-account">
        <h3>Bank Account Information for {sessionStorage.getItem("username")}</h3> <hr/>
        {accountData.length > 0 ? (
          accountData.map((account, index) => (
            <div key={index} >
              <table align="center" className="account-table">
                <tr>
                  <th>Account Number: </th>
                  <td>{account.accountNumber}</td>
                </tr>
                <tr>
                  <th>Account Type: </th>
                  <td>{account.accountType}</td>
                </tr>
                <tr>
                  <th>Balance: </th>
                  <td>${account.accountBalance}</td>
                </tr>
              </table> <hr/>
            </div>
          ))
        ) : (
          <p>Loading account information...</p>
        )}
      </div>
    </Container>
  );
};

export default BankAccount;
