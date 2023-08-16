package com.banking.app.controller;

import com.banking.app.model.Account;
import com.banking.app.model.Payee;
import com.banking.app.service.Interfaces.IPayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payee")
public class PayeeController {
    @Autowired
    private IPayeeService payeeService;

    @PostMapping("/addPayee")
    public void add(@RequestBody Payee payee) {
        payeeService.savePayee(payee);
        System.out.println("Payee added successfully!!");
    }

    @GetMapping("/fetchPayee/{userID}")
    public void fetchDetails(@PathVariable int userID){
        final Payee fetchedPayee = payeeService.getPayeeDetailsByUserID(userID);
        if(fetchedPayee != null) {
            System.out.println("Payee ID: " + fetchedPayee.getPayeeID());
            System.out.println("First Name: " + fetchedPayee.getFirstName());
            System.out.println("Last Name: " + fetchedPayee.getLastName());
            System.out.println("Nick Name: " + fetchedPayee.getNickName());
            System.out.println("Payee Account Number: " + fetchedPayee.getAccountNumber());
        }
        else
            System.out.println("Cannot fetch payee details!");
    }

    @PutMapping("/updatePayee")
    public void updatePayeeDetails(@RequestBody Payee payee){
        payeeService.updatePayeeDetails(payee);
        System.out.println("Payee details updated successfully!!");
    }
}
