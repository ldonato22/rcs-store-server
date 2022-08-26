package com.rcs.store.data.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "inventory_gen")
    private Integer id;
    private Date date;
    private String providerName;
    private String providerCode;
    private String productName;
    private String productDesc;
    public Integer amountIncomeDeposit;
    public Integer amountIncome;
    public Integer amountExpenses;
    public Integer amountTotal;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProviderName() {
        return providerName;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    public String getProviderCode() {
        return providerCode;
    }
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    public Integer getAmountIncomeDeposit() {
        return amountIncomeDeposit;
    }
    public void setAmountIncomeDeposit(Integer amountIncomeDeposit) {
        this.amountIncomeDeposit = amountIncomeDeposit;
    }
    public Integer getAmountIncome() {
        return amountIncome;
    }
    public void setAmountIncome(Integer amountIncome) {
        this.amountIncome = amountIncome;
    }
    public Integer getAmountExpenses() {
        return amountExpenses;
    }
    public void setAmountExpenses(Integer amountExpenses) {
        this.amountExpenses = amountExpenses;
    }
    public Integer getAmountTotal() {
        return amountTotal;
    }
    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", amountIncomeDeposit='" + amountIncomeDeposit + '\'' +
                ", amountIncome=" + amountIncome +
                ", amountExpenses=" + amountExpenses +
                ", amountTotal=" + amountTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory product = (Inventory) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
