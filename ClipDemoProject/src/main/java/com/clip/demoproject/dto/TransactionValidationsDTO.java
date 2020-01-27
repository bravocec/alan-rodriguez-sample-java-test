/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author alan
 */
public class TransactionValidationsDTO implements Serializable{
    
    private Boolean isValidOperation = Boolean.TRUE;
    private List<String> errorMessages = new ArrayList<>();

    public Boolean getIsValidOperation() {
        return isValidOperation;
    }

    public void setIsValidOperation(Boolean isValidOperation) {
        this.isValidOperation = isValidOperation;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.isValidOperation);
        hash = 59 * hash + Objects.hashCode(this.errorMessages);
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
        final TransactionValidationsDTO other = (TransactionValidationsDTO) obj;
        if (!Objects.equals(this.isValidOperation, other.isValidOperation)) {
            return false;
        }
        if (!Objects.equals(this.errorMessages, other.errorMessages)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransactionValidationsDTO{" + "isValidOperation=" + isValidOperation + ", errorMessages=" + errorMessages + '}';
    }
    
}
