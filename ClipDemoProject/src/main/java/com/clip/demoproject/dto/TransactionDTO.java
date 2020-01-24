/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Alan Rodriguez
 */
public class TransactionDTO implements Serializable{
    
    private String transaction_id;
    private Integer user_id;
    private Double amount;
    private String description;
    private String date;

    
    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    public TransactionDTO addTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    public TransactionDTO addUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionDTO addAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public TransactionDTO addDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public TransactionDTO addDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.transaction_id);
        hash = 97 * hash + Objects.hashCode(this.user_id);
        hash = 97 * hash + Objects.hashCode(this.amount);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionDTO other = (TransactionDTO) obj;
        if (!Objects.equals(this.transaction_id, other.transaction_id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" + "transaction_id=" + transaction_id + ", user_id=" + user_id + ", amount=" + amount + ", description=" + description + ", date=" + date + '}';
    }
    
    
}
