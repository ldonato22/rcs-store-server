package com.rcs.store.data.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "income_gen")
    private Integer id;
    private Date date;
    private Integer productId;
    private String invoice;
    private Integer amountDeposit;
    private Integer amount;
    private Float unitPrice;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getInvoice() {
        return invoice;
    }
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
    public Integer getAmountDeposit() {
        return amountDeposit;
    }
    public void setAmountDeposit(Integer amountDeposit) {
        this.amountDeposit = amountDeposit;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
