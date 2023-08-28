import React, { useState, useEffect } from "react";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import axios from "../../utilities/axios";
import { useHistory } from "react-router";
import Sidebar from "../Sidebar";
const PAYEE_URL = "/payee/getPayee/";
const ACC_URL = "/account/getAccount/";
const PAYEE_USERNAME = "/account/getUsername";
const DATE_URL = "/transaction/currentDateTime";
const TRANSACTION_URL = "/transaction/makeTransaction";
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
  const [selectedPayee, setSelectedPayee] = useState([]);
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
    console.log(selectedPayee);
    const items = selectedPayee.split(',').map((item)=>item.trim());
    console.log(items[0]);
    console.log(items[1]);
    console.log(items[2]);


    const fromUserName = sessionStorage.getItem("username");
    const res = await axios.get(PAYEE_USERNAME + "/" + items[2], {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Aloow-Headers": "Content-Type",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
      },
      withCredentials: false,
    });
    console.log(res.data);
    if (res.data.valueOf() === "noAccount") {
      const pun = items[0]+" "+items[1];
      console.log(pun);
      setPayeeUsername(pun);
    } else {
      setPayeeUsername(res.data);
    }
    const res_date = await axios.get(DATE_URL, {
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Aloow-Headers": "Content-Type",
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, OPTIONS, PUT, DELETE",
      },
      withCredentials: false,
    });



    // Create the data object for POST request
    const paymentData = {
      userName: fromUserName,
      amount: amount,
      payee: PayeeUserName,
      date: res_date.data,
      remarks: transactionMode,
      fromAccountNumber: selectedAccount,
      toAccountNumber: items[2],
    };
    console.log(paymentData);
    try {
      const response = await axios.post(
        TRANSACTION_URL,
        JSON.stringify(paymentData),
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
      alert("Payment submitted successfully!");
      history.push("/dashboard");
    } catch (error) {
      console.error("Error submitting payment:", error);
      alert("Error submitting payment. Please try again.");
    }
  };

  return (
    <div>
      <div className="col-12 col-lg-3"> 
        <Sidebar/>
      </div>
    <div className="col-12 col-lg-9">
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
              <option
                key={payee.payeeID}
                value={[payee.firstName, payee.lastName, payee.accountNumber]}
              >
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
    </div>
  );
};

export default PaymentForm;
