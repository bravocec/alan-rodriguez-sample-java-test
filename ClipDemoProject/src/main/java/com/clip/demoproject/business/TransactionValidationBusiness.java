/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.business;

import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionValidationsDTO;

/**
 *
 * @author alan
 */
public interface TransactionValidationBusiness {
    
    TransactionValidationsDTO validateAddParams(TransactionDTO request);
    
}
