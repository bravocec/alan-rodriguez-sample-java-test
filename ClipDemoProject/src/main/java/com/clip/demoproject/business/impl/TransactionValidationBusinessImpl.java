/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.business.impl;

import com.clip.demoproject.business.TransactionValidationBusiness;
import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionValidationsDTO;
import com.clip.demoproject.util.TransactionValidationsUtil;
import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

/**
 *
 * @author alan
 */
@Service
public class TransactionValidationBusinessImpl implements TransactionValidationBusiness {

    final Integer ceroInteger = 0;
    final Double ceroDouble = 0.0;

    @Override
    public TransactionValidationsDTO validateAddParams(TransactionDTO request) {

        final TransactionValidationsDTO validations = new TransactionValidationsDTO();

        if (request == null) {
            validations.getErrorMessages().add("The request cannot be null");
        }

        if (request.getAmount() == null || request.getAmount().compareTo(ceroDouble) <= ceroInteger) {
            validations.getErrorMessages().add("The amount cannot be null, negative or 0");
        }
        
        if (request.getDate() == null || !TransactionValidationsUtil.isValidFormat(TransactionValidationsUtil.DATE_PATTERN, request.getDate(), Locale.getDefault())) {
            validations.getErrorMessages().add("The transaction date cannot be null or does not have a valid format (" + TransactionValidationsUtil.DATE_PATTERN + ")");
        }

        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            validations.getErrorMessages().add("The description is necessary");
        }

        if (request.getUser_id() == null || request.getUser_id() <= ceroInteger) {
            validations.getErrorMessages().add("The user_id cannot be null, negative or 0");
        }

        if (!validations.getErrorMessages().isEmpty()) {
            validations.setIsValidOperation(Boolean.FALSE);
        }
        request.setTransaction_id(null);
        return validations;
    }

    @Override
    public TransactionValidationsDTO validateShowParams(TransactionDTO request) {
        final TransactionValidationsDTO validations = new TransactionValidationsDTO();
        if (request == null) {
            validations.getErrorMessages().add("The request cannot be null");
        }
        if (request.getUser_id() == null || request.getUser_id() <= ceroInteger) {
            validations.getErrorMessages().add("The user_id cannot be null, negative or 0");
        }

        if (request.getTransaction_id() == null || request.getTransaction_id().trim().isEmpty()) {
            validations.getErrorMessages().add("The transaction_id cannot be null");
        }

        if (!validations.getErrorMessages().isEmpty()) {
            validations.setIsValidOperation(Boolean.FALSE);
        }
        return validations;
    }

    @Override
    public TransactionValidationsDTO validateShowIfExist(TransactionDTO request) {
        final TransactionValidationsDTO validations = new TransactionValidationsDTO();
        if (request == null) {
            validations.getErrorMessages().add("Transaction not found");
        }
        if (!validations.getErrorMessages().isEmpty()) {
            validations.setIsValidOperation(Boolean.FALSE);
        }
        return validations;
    }

    @Override
    public TransactionValidationsDTO validateListParams(TransactionDTO request) {
        final TransactionValidationsDTO validations = new TransactionValidationsDTO();
        commonValidationsForUserId(validations, request);
        return validations;
    }

    @Override
    public TransactionValidationsDTO validateSumParams(TransactionDTO request) {
        final TransactionValidationsDTO validations = new TransactionValidationsDTO();
        commonValidationsForUserId(validations, request);
        return validations;
    }

    @Override
    public TransactionValidationsDTO validateReportParams(TransactionDTO request) {
        final TransactionValidationsDTO validations = new TransactionValidationsDTO();
        commonValidationsForUserId(validations, request);
        return validations;
    }

    private void commonValidationsForUserId(TransactionValidationsDTO validations, TransactionDTO request) {
        if (request == null) {
            validations.getErrorMessages().add("The request cannot be null");
        }
        if (request.getUser_id() == null || request.getUser_id() <= ceroInteger) {
            validations.getErrorMessages().add("The user_id cannot be null, negative or 0");
        }
        if (!validations.getErrorMessages().isEmpty()) {
            validations.setIsValidOperation(Boolean.FALSE);
        }
    }

}
