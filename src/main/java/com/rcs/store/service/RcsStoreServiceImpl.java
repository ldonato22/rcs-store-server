package com.rcs.store.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.rcs.store.data.models.Expenses;
import com.rcs.store.data.models.Income;
import com.rcs.store.data.models.Inventory;
import com.rcs.store.data.models.MessageResponse;
import com.rcs.store.data.repository.ExpensesRepository;
import com.rcs.store.data.repository.IncomeRepository;
import com.rcs.store.data.repository.InventoryRepository;
import com.rcs.store.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RcsStoreServiceImpl implements RcsStoreService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ExpensesRepository expensesRepository;

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    @Override
    public List<Expenses> getAllExpenses() {
        return expensesRepository.findAll();
    }

    @Override
    public MessageResponse createInventory(Inventory inventoryRequest){
        Inventory newInventory = new Inventory();
        newInventory.setDate(new Date(System.currentTimeMillis()));
        newInventory.setProviderName(inventoryRequest.getProviderName());
        newInventory.setProviderCode(inventoryRequest.getProviderCode());
        newInventory.setProductName(inventoryRequest.getProductName());
        newInventory.setProductDesc(inventoryRequest.getProductDesc());
        newInventory.setAmountIncomeDeposit(0);
        newInventory.setAmountIncome(0);
        newInventory.setAmountExpenses(0);
        newInventory.setAmountTotal(0);
        inventoryRepository.save(newInventory);
        return new MessageResponse("The product was successfully registered.");
    }

    @Override
    public MessageResponse modifyInventory(Inventory inventoryRequest) throws ResourceNotFoundException{
        Optional<Inventory> product = inventoryRepository.findById(inventoryRequest.getId());
        if (product.isEmpty()){
            throw new ResourceNotFoundException("Product", "id", product);
        }
        else{
            product.get().setDate(new Date(System.currentTimeMillis()));
            product.get().setProviderName(inventoryRequest.getProviderName());
            product.get().setProviderCode(inventoryRequest.getProviderCode());
            product.get().setProductName(inventoryRequest.getProductName());
            product.get().setProductDesc(inventoryRequest.getProductDesc());
            inventoryRepository.save(product.get());
            return new MessageResponse("The product was successfully modified.");
        }
    }

    @Override
    public MessageResponse depositToLocal(Inventory inventoryRequest) throws ResourceNotFoundException{
        Integer amount = inventoryRequest.getAmountIncome();

        Optional<Inventory> product = inventoryRepository.findById(inventoryRequest.getId());
        if (product.isEmpty()){
            throw new ResourceNotFoundException("Product", "id", product);
        }
        else{
            product.get().setAmountIncomeDeposit(product.get().getAmountIncomeDeposit() - amount);
            product.get().setAmountIncome(product.get().getAmountIncome() + amount);
            inventoryRepository.save(product.get());
            return new MessageResponse("The deposit went to the local.");
        }
    }

    @Override
    public MessageResponse createIncome(Income incomeRequest) throws ResourceNotFoundException {
        Income newIncome = new Income();
        newIncome.setDate(new Date(System.currentTimeMillis()));
        newIncome.setProductId(incomeRequest.getProductId());
        newIncome.setInvoice(incomeRequest.getInvoice());
        newIncome.setUnitPrice(incomeRequest.getUnitPrice());
        newIncome.setAmountDeposit(incomeRequest.getAmountDeposit());
        newIncome.setAmount(incomeRequest.getAmount());

        updateAmountInventory(incomeRequest.getProductId(), incomeRequest.getAmount(), incomeRequest.getAmountDeposit(), "income");

        incomeRepository.save(newIncome);
        return new MessageResponse("Successfully registered and updated.");
    }

    @Override
    public MessageResponse createExpenses(Expenses expensesRequest) throws ResourceNotFoundException {
        Expenses newExpenses = new Expenses();
        newExpenses.setDate(new Date(System.currentTimeMillis()));
        newExpenses.setProductId(expensesRequest.getProductId());
        newExpenses.setInvoice(expensesRequest.getInvoice());
        newExpenses.setUnitPrice(expensesRequest.getUnitPrice());
        newExpenses.setAmount(expensesRequest.getAmount());

        updateAmountInventory(expensesRequest.getProductId(), expensesRequest.getAmount(), 0, "expenses");

        expensesRepository.save(newExpenses);
        return new MessageResponse("Successfully registered and updated.");
    }

    public void updateAmountInventory(Integer productId, Integer ammount, Integer amountDeposit, String flag){
        Optional<Inventory> product = inventoryRepository.findById(productId);
        if (product.isEmpty()){
            throw new ResourceNotFoundException("Product", "id", productId);
        }
        else{
            if (flag.equals("income")){
                product.get().setAmountIncome(ammount + product.get().getAmountIncome());
                product.get().setAmountIncomeDeposit(amountDeposit + product.get().getAmountIncomeDeposit());
            }else{
                product.get().setAmountExpenses(ammount + product.get().getAmountExpenses());
            }
            product.get().setAmountTotal((product.get().getAmountIncomeDeposit() + product.get().getAmountIncome()) - product.get().getAmountExpenses());
            inventoryRepository.save(product.get());
        }
    }


/** 
    @Override
    public Optional<Inventory> updateInventario(Integer inventarioId, Inventory inventarioRequest) throws ResourceNotFoundException{
        Optional<Inventory> inventario = inventarioRepository.findById(inventarioId);
        if (inventario.isEmpty()){
            throw new ResourceNotFoundException("Employee", "id", inventarioId);
        }
        else{
            inventario.get().setProducto(inventarioRequest.getProducto());
            inventario.get().setDescripcion(inventarioRequest.getDescripcion());
            inventario.get().setStockInicial(inventarioRequest.getStockInicial());
            inventarioRepository.save(inventario.get());
        }
        return inventario;
    }

    @Override
    public Inventory getASingleInventario(Integer inventarioId) throws ResourceNotFoundException{
        return inventarioRepository.findById(inventarioId).orElseThrow(() -> new ResourceNotFoundException("Inventario", "id", inventarioId));
    }


    @Override
    public void deleteInventario(Integer inventarioId) throws ResourceNotFoundException {
        if (inventarioRepository.getById(inventarioId).getId().equals(inventarioId)){
            inventarioRepository.deleteById(inventarioId);
        }
        else throw new ResourceNotFoundException("Inventario", "id", inventarioId);
    }
*/

}
