// AccountCreationSuccessful.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Card, CardBody, CardTitle } from 'reactstrap';

const sampleAccountCreationSuccessful = {"userName": "John Doe", "accountNumber": "123456789012", 
"accountType": "Checking", "initialBalance": "$50.00"};

function AccountCreationSuccessful() {
  const [accountData, setAccountData] = useState(sampleAccountCreationSuccessful);

//   useEffect(() => {
//     // Make the Axios GET request here
//     axios.get('URL_TO_YOUR_API_ENDPOINT')
//       .then(response => {
//         setAccountData(response.data);
//       })
//       .catch(error => {
//         console.error('Error fetching account data:', error);
//       });
//   }, []);

  return (
    <Container className="my-5">
      <Card className="text-white bg-success">
        <CardBody>
          <CardTitle tag="h1">Account Creation Successful</CardTitle>
          {accountData ? (
            <div className="account-details">
              <p><strong>User's Name:</strong> {accountData.userName}</p>
              <p><strong>Account Number:</strong> {accountData.accountNumber}</p>
              <p><strong>Account Type:</strong> {accountData.accountType}</p>
              <p><strong>Initial Balance:</strong> {accountData.initialBalance}</p>
            </div>
          ) : (
            <p>Loading account details...</p>
          )}
        </CardBody>
      </Card>
    </Container>
  );
}

export default AccountCreationSuccessful;
