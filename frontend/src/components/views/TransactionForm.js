import React, { useState, useEffect } from "react";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import axios from "../../utilities/axios";
import { useHistory } from "react-router";
const PAYEE_URL = "/payee/getPayee/";
const ACC_URL = "/account/getAccount/";
const PAYEE_ACC_URL = "/account/getAccount";

// const samplePayees = [
//   { id: 1, name: "Payee 1" },
//   { id: 2, name: "Payee 2" },
//   { id: 3, name: "Payee 3" },
// ];

const PaymentForm = () => {
  const history = useHistory();
  const [payees, setPayees] = useState([]);
  const [accounts, setAccounts] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState("");
  const [selectedPayee, setSelectedPayee] = useState("");
  const [PayeeUserName, setPayeeUsername] = useState("");
  const [amount, setAmount] = useState("");
  const [transactionMode, setTransactionMode] = useState("");

  useEffect(() => {
    axios
      .get(PAYEE_URL + sessionStorage.getItem("username"))
      .then((response) => {
        setPayees(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);
  useEffect(() => {
    axios
      .get(ACC_URL + sessionStorage.getItem("username"))
      .then((response) => {
        setAccounts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);
  const handleSubmit = async (event) => {
    event.preventDefault();

    // Get userName from sessionStorage
    const fromUserName = sessionStorage.getItem("userName");
    const res = await axios.get(
      PAYEE_ACC_URL + "/" + selectedPayee,
      {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Aloow-Headers": "Content-Type",
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
        },
        withCredentials: false,
      }
    );
    setPayeeUsername(res.data);
    // Create the data object for POST request
    const paymentData = {
      fromUserName,
      toUserName: PayeeUserName,
      fromAccountNumber: selectedAccount,
      toAccountNumber: selectedPayee,
      amount,
      transactionMode,
      timestamp: new Date().toISOString(),
    };

    try {
      // Make POST request to submit payment (replace with your API endpoint)
      // await axios.post("your_payment_api_endpoint", paymentData);
      alert("Payment submitted successfully!");
      // Clear form fields after successful submission
      // setSelectedPayee("");
      // setAmount("");
      // setTransactionMode("");
      history.push("/dashboard");
    } catch (error) {
      console.error("Error submitting payment:", error);
      alert("Error submitting payment. Please try again.");
    }
  };

  return (
    <div>
      <h2>Make a Payment</h2>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for="payeeSelect">Select Payee : </Label>
          <Input
            type="select"
            id="payeeSelect"
            value={selectedPayee}
            onChange={(e) => setSelectedPayee(e.target.value)}
          >
            <option value="">Select a payee</option>
            {payees.map((payee) => (
              <option key={payee.payeeID} value={payee.accountNumber}>
                {payee.nickName}
              </option>
            ))}
          </Input>
        </FormGroup>
        <FormGroup>
          <Label for="accSelect">Select Your Account : </Label>
          <Input
            type="select"
            id="accSelect"
            value={selectedAccount}
            onChange={(e) => setSelectedAccount(e.target.value)}
          >
            <option value="">Select a account</option>
            {accounts.map((acc) => (
              <option key={acc.accountNumber} value={acc.accountNumber}>
                {acc.accountNumber}
              </option>
            ))}
          </Input>
        </FormGroup>
        <FormGroup>
          <Label for="transactionModeSelect">Select Transaction Mode:</Label>
          <Input
            type="select"
            id="transactionModeSelect"
            value={transactionMode}
            onChange={(e) => setTransactionMode(e.target.value)}
          >
            <option value="">Select a transaction mode</option>
            <option value="NEFT">NEFT</option>
            <option value="RTGS">RTGS</option>
            <option value="IMPS">IMPS</option>
          </Input>
        </FormGroup>
        <FormGroup>
          <Label for="amountInput">Amount:</Label>
          <Input
            type="number"
            id="amountInput"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </FormGroup>
        <Button color="primary" type="submit">
          Submit Payment
        </Button>
      </Form>
    </div>
  );
};

export default PaymentForm;
