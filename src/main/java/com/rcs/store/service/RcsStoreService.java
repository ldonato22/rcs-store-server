package com.rcs.store.service;

import java.util.List;

import com.rcs.store.data.models.Expenses;
import com.rcs.store.data.models.Income;
import com.rcs.store.data.models.Inventory;
import com.rcs.store.data.models.MessageResponse;

import org.springframework.stereotype.Component;

@Component
public interface RcsStoreService {

    List<Inventory> getAllInventory();
    List<Income> getAllIncome();
    List<Expenses> getAllExpenses();

    MessageResponse createInventory(Inventory inventoryRequest);
    MessageResponse createIncome(Income incomeRequest);
    MessageResponse createExpenses(Expenses expensesRequest);

    MessageResponse modifyInventory(Inventory inventory);

    MessageResponse depositToLocal(Inventory inventory);
//    Optional<Inventory> updateInventario(Integer inventario, Inventory inventarioRequest);
//    void deleteInventario(Integer inventario);
//    Inventory getASingleInventario(Integer inventario);
    
}
