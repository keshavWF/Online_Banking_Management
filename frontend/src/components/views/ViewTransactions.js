// src/components/TransactionList.js
import React, { useState, useEffect } from "react";
import { Container, Table } from "reactstrap";
import axios from "../../utilities/axios";
const TRANSACTION_URL = "/transaction/getTransactions/";
// const sampleTransaction = [
//   {
//     id: 1,
//     payeeName: "John Doe",
//     time: "2023-08-20 10:00 AM",
//     amount: "$100",
//     remarks: "Grocery shopping",
//   },
//   {
//     id: 2,
//     payeeName: "Jane Smith",
//     time: "2023-08-19 3:30 PM",
//     amount: "$50",
//     remarks: "Dinner at a restaurant",
//   },
// ];

const ViewTransactions = () => {
  const [transactions, setTransactions] = useState([]);

  // useEffect(() => {
  //   axios
  //     .get("API_ENDPOINT_URL") // Replace with API endpoint
  //     .then((response) => {
  //       setTransactions(response.data);
  //     })
  //     .catch((error) => {
  //       console.error("Error fetching transactions:", error);
  //     });
  // }, []);

  useEffect(() => {
    const auth = "Bearer " + sessionStorage.getItem("token").toString();
    axios
      .get(TRANSACTION_URL + sessionStorage.getItem("username"),
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
        setTransactions(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);


  return (
    <Container>
      <Table>
        <thead>
          <tr>
            <th>Payee Name</th>
            <th>Time</th>
            <th>Amount</th>
            <th>Remarks</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.referenceID}>
              <td>{transaction.payee}</td>
              <td>{transaction.date}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.remarks}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default ViewTransactions;
