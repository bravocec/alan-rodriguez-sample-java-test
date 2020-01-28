/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.dto;

import java.io.Serializable;

/**
 *
 * @author Alan Rodriguez
 */
public class SumDTO implements Serializable {

    private Integer user_id;
    private Double sum;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public SumDTO addUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public SumDTO addSum(Double sum) {
        this.sum = sum;
        return this;
    }

}
