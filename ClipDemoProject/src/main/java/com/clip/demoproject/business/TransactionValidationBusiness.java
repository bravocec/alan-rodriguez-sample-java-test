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

    /**
     * Validate all params required for an add transaction.
     *
     * @param request
     * @return
     */
    TransactionValidationsDTO validateAddParams(TransactionDTO request);

    /**
     * Validate all params required for a show transaction.
     *
     * @param request
     * @return
     */
    TransactionValidationsDTO validateShowParams(TransactionDTO request);

    /**
     * Validate if the transaction exist.
     *
     * @param request
     * @return
     */
    TransactionValidationsDTO validateShowIfExist(TransactionDTO request);

    /**
     * Validate all params for list operation.
     * 
     * @param request
     * @return
     */
    TransactionValidationsDTO validateListParams(TransactionDTO request);

    /**
     * Validate all params for sum operation.
     * 
     * @param request
     * @return
     */
    TransactionValidationsDTO validateSumParams(TransactionDTO request);

    /**
     * Validate all params for report operation.
     * 
     * @param request
     * @return
     */
    TransactionValidationsDTO validateReportParams(TransactionDTO request);

}
