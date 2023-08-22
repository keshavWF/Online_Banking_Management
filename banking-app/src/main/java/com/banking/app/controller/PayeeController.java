package com.banking.app.controller;

import com.banking.app.model.Account;
import com.banking.app.model.Payee;
import com.banking.app.service.Interfaces.IPayeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payee")
public class PayeeController {
    @Autowired
    private IPayeeService payeeService;

    @PostMapping("/addPayee/{userName}")
    public void add(@RequestBody Payee payee, @PathVariable String userName) {
        payeeService.savePayee(payee, userName);
    }

    @GetMapping("/fetchPayee/{payeeID}")
    public Payee fetchDetails(@PathVariable int payeeID){
        final Payee fetchedPayee = payeeService.getPayeeDetailsByUserID(payeeID);
        return fetchedPayee;
    }

    @PutMapping("/updatePayee")
    public void updatePayeeDetails(@RequestBody Payee payee){
        payeeService.updatePayeeDetails(payee);
    }

    @GetMapping("/getPayee/{userName}")
	public List<Payee> getPayeeDetailsByUserName(@PathVariable String userName){
		return payeeService.getPayeesByUserName(userName);
	}
}
