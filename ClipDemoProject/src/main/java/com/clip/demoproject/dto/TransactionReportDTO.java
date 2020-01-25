/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Alan Rodriguez
 */
@JsonIgnoreProperties({ "start", "finish", "preCurrentLocalDate", "currentLocalDate", "isTheEndOfTheReport" })
public class TransactionReportDTO {
    
    private Integer id;
    private String weekStart;
    private LocalDate start;
    private String weekFinish;
    private LocalDate finish;
    private Integer quantity;
    private Double amount;
    private Double totalamount;
    private LocalDate preCurrentLocalDate;
    private LocalDate currentLocalDate;
    private Boolean isTheEndOfTheReport = Boolean.FALSE;

    public LocalDate getPreCurrentLocalDate() {
        return preCurrentLocalDate;
    }

    public void setPreCurrentLocalDate(LocalDate preCurrentLocalDate) {
        this.preCurrentLocalDate = preCurrentLocalDate;
    }
    
    public Boolean getIsTheEndOfTheReport() {
        return isTheEndOfTheReport;
    }

    public void setIsTheEndOfTheReport(Boolean isTheEndOfTheReport) {
        this.isTheEndOfTheReport = isTheEndOfTheReport;
    }
    

    public LocalDate getCurrentLocalDate() {
        return currentLocalDate;
    }

    public void setCurrentLocalDate(LocalDate currentLocalDate) {
        this.currentLocalDate = currentLocalDate;
    }
    
    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(String weekStart) {
        this.weekStart = weekStart;
    }

    public String getWeekFinish() {
        return weekFinish;
    }

    public void setWeekFinish(String weekFinish) {
        this.weekFinish = weekFinish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.weekStart);
        hash = 47 * hash + Objects.hashCode(this.weekFinish);
        hash = 47 * hash + Objects.hashCode(this.quantity);
        hash = 47 * hash + Objects.hashCode(this.amount);
        hash = 47 * hash + Objects.hashCode(this.totalamount);
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
        final TransactionReportDTO other = (TransactionReportDTO) obj;
        if (!Objects.equals(this.weekStart, other.weekStart)) {
            return false;
        }
        if (!Objects.equals(this.weekFinish, other.weekFinish)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.totalamount, other.totalamount)) {
            return false;
        }
        return true;
    }
    
}
