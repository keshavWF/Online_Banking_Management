package com.banking.app.service.Interfaces;

import com.banking.app.model.Payee;
import java.util.List;

public interface IPayeeService {
    public void savePayee(Payee payee, String userName);

    public Payee getPayeeDetailsByUserID(int userID);

    public Payee updatePayeeDetails(Payee payee);

    public List<Payee> getPayeesByUserName(String userName);
}
