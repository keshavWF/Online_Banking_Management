import React, { useState } from "react";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import axios from "axios";

const samplePayees = [
  { id: 1, name: "Payee 1" },
  { id: 2, name: "Payee 2" },
  { id: 3, name: "Payee 3" },
];

const PaymentForm = () => {
  const [payees, setPayees] = useState(samplePayees);
  const [selectedPayee, setSelectedPayee] = useState("");
  const [amount, setAmount] = useState("");
  const [transactionMode, setTransactionMode] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Get userName from sessionStorage
    const userName = sessionStorage.getItem("userName");

    // Create the data object for POST request
    const paymentData = {
      userName,
      payee: selectedPayee,
      amount,
      transactionMode,
      timestamp: new Date().toISOString(),
    };

    try {
      // Make POST request to submit payment (replace with your API endpoint)
      await axios.post("your_payment_api_endpoint", paymentData);
      alert("Payment submitted successfully!");
      // Clear form fields after successful submission
      setSelectedPayee("");
      setAmount("");
      setTransactionMode("");
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
              <option key={payee.id} value={payee.id}>
                {payee.name}
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
