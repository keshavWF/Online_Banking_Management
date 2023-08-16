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
    }

    @GetMapping("/fetchPayee/{userID}")
    public Payee fetchDetails(@PathVariable int userID){
        final Payee fetchedPayee = payeeService.getPayeeDetailsByUserID(userID);
        return fetchedPayee;
    }

    @PutMapping("/updatePayee")
    public void updatePayeeDetails(@RequestBody Payee payee){
        payeeService.updatePayeeDetails(payee);
    }
}
