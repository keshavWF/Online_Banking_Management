import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Card, CardBody, CardTitle } from 'reactstrap';
const sampleTransaction = {
    "referenceID": "ABC123XYZ", "userName": "John Doe", "payeeName": "Jane Smith", "amount": 100.00
  }

function TransactionSuccessful() {
  const [transactionData, setTransactionData] = useState(sampleTransaction);

//   useEffect(() => {
//     // Make the Axios GET request here
//     axios.get('URL_TO_YOUR_API_ENDPOINT')
//       .then(response => {
//         setTransactionData(response.data);
//       })
//       .catch(error => {
//         console.error('Error fetching transaction data:', error);
//       });
//   }, []);

  return (
    <Container className="my-5">
      <Card className="text-white bg-success">
        <CardBody>
          <CardTitle tag="h1">Transaction Successful</CardTitle>
          {transactionData ? (
            <div className="transaction-details">
              <p><strong>Reference ID:</strong> {transactionData.referenceID}</p>
              <p><strong>User's Name:</strong> {transactionData.userName}</p>
              <p><strong>Payee Name:</strong> {transactionData.payeeName}</p>
              <p><strong>Amount:</strong> {transactionData.amount}</p>
            </div>
          ) : (
            <p>Loading transaction details...</p>
          )}
        </CardBody>
      </Card>
    </Container>
  );
}

export default TransactionSuccessful;
