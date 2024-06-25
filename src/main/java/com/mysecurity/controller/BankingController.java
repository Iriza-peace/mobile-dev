package com.mysecurity.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mysecurity.entity.Banking;
import com.mysecurity.service.BankingService;

@RestController
@RequestMapping("/banking")
public class BankingController {

    @Autowired
    private BankingService service;

    @PostMapping("/addTransaction")
    public Banking addTransaction(@RequestBody Banking banking) {
        return service.addTransaction(banking);
    }

    @PutMapping("/updateTransaction/{id}")
    public Banking updateTransaction(@PathVariable int id, @RequestBody Banking banking) {
        return service.updateTransaction(id, banking);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public Banking withdraw(@PathVariable int id, @PathVariable double amount) {
        return service.withdraw(id, amount);
    }

    @PutMapping("/deposit/{id}/{amount}")
    public Banking deposit(@PathVariable int id, @PathVariable double amount) {
        return service.deposit(id, amount);
    }

    @PutMapping("/transfer/{id}/{amount}/{id2}")
    public Banking transfer(@PathVariable int id, @PathVariable int amount, @PathVariable int id2) {
        return service.transfer(id, amount, id2);
    }

}
