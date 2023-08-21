package com.banking.app.service;

import com.banking.app.model.Payee;
import com.banking.app.repository.PayeeRepository;
import com.banking.app.service.Interfaces.IPayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayeeService implements IPayeeService {
    @Autowired
    private PayeeRepository payeeRepository;

    @Override
    public void savePayee(Payee payee){
        payeeRepository.save(payee);
    }

    @Override
    public Payee getPayeeDetailsByUserID(int payeeID){
        return payeeRepository.findById(payeeID).orElse(null);
    }

    @Override
    public Payee updatePayeeDetails(Payee payee){
        final Payee payeeData = payeeRepository
                .findById(payee.getPayeeID()).orElse(null);

        if(payeeData != null){
            payeeData.setPayeeID(payee.getPayeeID());
            payeeData.setAccountNumber(payee.getAccountNumber());
            payeeData.setFirstName(payee.getFirstName());
            payeeData.setNickName(payee.getNickName());
            payeeData.setLastName(payee.getLastName());
            payeeRepository.save(payeeData);
            return payeeData;
        }

        return null;
    }
}
