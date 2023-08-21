import { useState } from "react";
import Form from "../../utilities/Forms";

const AddPayee = () => {
  const [payeeFirstName, setPayeeFirstName] = useState("");
  const [payeeLastName, setPayeeLastName] = useState("");
  const [payeeAccount, setPayeeAccount] = useState("");
  const [payeeNickName, setPayeeNickName] = useState("");
  const [validate, setValidate] = useState({});

  const validateAddPayee = () => {
    let isValid = true;

    let validator = Form.validator({
      payeeFirstName: {
        value: payeeFirstName,
        isRequired: true,
      },
      payeeLastName: {
        value: payeeLastName,
        isRequired: true,
      },
      payeeAccount: {
        value: payeeAccount,
        isRequired: true,
      },
      payeeNickName: {
        value: payeeNickName,
      },
    });

    if (validator !== null) {
      setValidate({
        validate: validator.errors,
      });

      isValid = false;
    }
    return isValid;
  };

  const addPayee = (e) => {
    e.preventDefault();

    const validate = validateAddPayee();

    if (validate) {
      setPayeeFirstName({});
      setPayeeLastName("");
      setPayeeAccount("");
      setPayeeNickName("");
      alert("Successfully Registered Payee");
    }
  };

  return (
    <div className="row g-0 auth-wrapper">
      <div className="col-12 col-md-7 col-lg-6 auth-main-col text-center">
        <div className="d-flex flex-column align-content-end">
          <div className="auth-body mx-auto">
            <h2>Add Payee Details</h2>
            <div className="auth-form-container text-start">
              <form
                className="auth-form"
                method="POST"
                onSubmit={addPayee}
                autoComplete={"off"}
              >
                <div className="name mb-3">
                  <input
                    type="text"
                    id="payeeFirstName"
                    name="payeeFirstName"
                    value={payeeFirstName}
                    placeholder="First Name"
                    onChange={(e) => setPayeeFirstName(e.target.value)}
                  />
                </div>

                <div className="payeeLastName mb-3">
                  <input
                    type="text"
                    id="payeeLastName"
                    name="payeeLastName"
                    value={payeeLastName}
                    placeholder="Last Name"
                    onChange={(e) => setPayeeLastName(e.target.value)}
                  />
                </div>
                <div className="payeeAccount mb-3">
                  <input
                    type="text"
                    id="payeeAccount"
                    name="payeeAccount"
                    value={payeeAccount}
                    placeholder="Account Number"
                    onChange={(e) => setPayeeAccount(e.target.value)}
                  />
                </div>
                <div className="payeeNickName mb-3">
                  <input
                    type="text"
                    id="payeeNickName"
                    name="payeeNickName"
                    value={payeeNickName}
                    placeholder="Payee Nick Name"
                    onChange={(e) => setPayeeNickName(e.target.value)}
                  />
                </div>
                <div className="text-center">
                  <button
                    type="submit"
                    className="btn btn-primary w-100 theme-btn mx-auto"
                  >
                    Add Payee
                  </button>
                </div>
              </form>

              <hr />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddPayee;
