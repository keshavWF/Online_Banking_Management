import { useState } from "react";
import Form from '../../utilities/Forms'

// Account Number is auto-generated

// userID to be pulled in from the logged in state.

const AccountCreation = () => {

    const [accountType, setAccountType] = useState('');
    const [aadhar, setAadhar] = useState('');    
    const [validate, setValidate] = useState({});

    const validateAccountCreation = () => {
        let isValid = true;

        let validator = Form.validator({
            accountType: {
                value: accountType,
                isRequired: true,
            },
            aadhar: {
                value: aadhar,
                isRequired: true,
            }
        });

        if (validator !== null) {
            setValidate({
                validate: validator.errors
            })

            isValid = false
        }
        return isValid;
    }

    const accountCreation = (e) => {
        e.preventDefault();

        const validate = validateAccountCreation();

        if (validate) {
            setAccountType({});
            setAadhar('');
            alert('Account created successfully');
        }
    }


    return (
        <div className="row g-0 auth-wrapper">

            <div className="col-12 col-md-7 col-lg-6 auth-main-col text-center">
                <div className="d-flex flex-column align-content-end">
                    <div className="auth-body mx-auto">
                        <h2>Create New Account</h2>
                        <div className="auth-form-container text-start">
                            <form className="auth-form" method="POST" onSubmit={accountCreation} autoComplete={'off'}>

                                <div className="accountType mb-3">
                                    <input type="text"
                                        id="accountType"
                                        name="accountType"
                                        value={accountType}
                                        placeholder="Account Type"
                                        onChange={(e) => setAccountType(e.target.value)}
                                    />
                                </div>

                                <div className="aadhar mb-3">
                                    <input type="text"
                                        id="aadhar"
                                        name="aadhar"
                                        value={aadhar}
                                        placeholder="Aadhar Number"
                                        onChange={(e) => setAadhar(e.target.value)}
                                    />
                                </div> 
                                
                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary w-100 theme-btn mx-auto">Add Payee</button>
                                </div>
                            </form>

                            <hr />
                        </div>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default AccountCreation;