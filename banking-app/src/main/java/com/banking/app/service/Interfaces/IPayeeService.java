package com.banking.app.service.Interfaces;

import com.banking.app.model.Payee;

public interface IPayeeService {
    public void savePayee(Payee payee);

    public Payee getPayeeDetailsByUserID(int userID);

    public Payee updatePayeeDetails(Payee payee);

    public List<Payee> getPayeesByUserName(String userName);
}
