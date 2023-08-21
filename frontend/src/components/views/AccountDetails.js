import React, { useState, useEffect } from 'react';
import axios from 'axios';

const sampleAccountData = {
    userName: 'John Doe',
    accountType: 'Savings',
    balance: 5000.0,
  };
  

const BankAccount = () => {
  const [accountData, setAccountData] = useState(sampleAccountData);

//   useEffect(() => {
//     // Make the Axios GET request here
//     axios.get('your_api_endpoint_here')
//       .then(response => {
//         setAccountData(response);
//       })
//       .catch(error => {
//         console.error('Error fetching data:', error);
//       });
//   }, []);


  return (
    <div className="bank-account">
      {accountData ? (
        <div>
          <h2>Bank Account Information</h2>
          <p>Name: {accountData.userName}</p>
          <p>Account Type: {accountData.accountType}</p>
          <p>Balance: ${accountData.balance}</p>
        </div>
      ) : (
        <p>Loading account information...</p>
      )}
    </div>
  );
};

export default BankAccount;

