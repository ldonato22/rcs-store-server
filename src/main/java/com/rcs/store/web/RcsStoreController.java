package com.rcs.store.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.rcs.store.data.models.Expenses;
import com.rcs.store.data.models.Income;
import com.rcs.store.data.models.Inventory;
import com.rcs.store.data.models.MessageResponse;
import com.rcs.store.service.RcsStoreService;

@CrossOrigin
@RestController
@RequestMapping
@Validated
public class RcsStoreController {
    
    @Autowired
    RcsStoreService rcsStoreService;

    @GetMapping("/inventory/all")
    public ResponseEntity<List<Inventory>> getAllInventory(){
        List<Inventory> inventory = rcsStoreService.getAllInventory();
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/income/all")
    public ResponseEntity<List<Income>> getAllIncome(){
        List<Income> income = rcsStoreService.getAllIncome();
        return new ResponseEntity<>(income, HttpStatus.OK);
    }

    @GetMapping("/expenses/all")
    public ResponseEntity<List<Expenses>> getAllExpenses(){
        List<Expenses> expenses = rcsStoreService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("/inventory/add")
    public ResponseEntity<MessageResponse> addInventory(@RequestBody @Valid Inventory inventory){
        MessageResponse messageInventory = rcsStoreService.createInventory(inventory);
        return new ResponseEntity<>(messageInventory, HttpStatus.CREATED);    
    }

    @PutMapping("/inventory/update")
    public ResponseEntity<MessageResponse> updateInventory(@RequestBody @Valid Inventory inventory){
        MessageResponse messageInventory = rcsStoreService.modifyInventory(inventory);
        return new ResponseEntity<>(messageInventory, HttpStatus.OK);    
    }

    @PostMapping("/income/add")
    public ResponseEntity<MessageResponse> addIncome(@RequestBody @Valid Income income){
        MessageResponse newIncome = rcsStoreService.createIncome(income);
        return new ResponseEntity<>(newIncome, HttpStatus.CREATED);    
    }

    @PostMapping("/expenses/add")
    public ResponseEntity<MessageResponse> addExpenses(@RequestBody @Valid Expenses expenses){
        MessageResponse newExpenses = rcsStoreService.createExpenses(expenses);
        return new ResponseEntity<>(newExpenses, HttpStatus.CREATED);    
    }

    @PutMapping("/deposit-to-local")
    public ResponseEntity<MessageResponse> depositToLocal(@RequestBody @Valid Inventory inventory){
        MessageResponse newExpenses = rcsStoreService.depositToLocal(inventory);
        return new ResponseEntity<>(newExpenses, HttpStatus.OK);    
    }
}
