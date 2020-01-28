/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.facade;

import com.clip.demoproject.business.TransactionBusiness;
import com.clip.demoproject.business.TransactionValidationBusiness;
import com.clip.demoproject.config.Response;
import com.clip.demoproject.dto.SumDTO;
import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionReportDTO;
import com.clip.demoproject.dto.TransactionValidationsDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author alan
 */
@Service
public class TransactionFacade {

    @Autowired
    private TransactionBusiness transactionBusiness;

    @Autowired
    private TransactionValidationBusiness transactionValidationBusiness;

    public Response<TransactionDTO> add(TransactionDTO request) {
        Response<TransactionDTO> response = new Response<>();
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateAddParams(request);
        if (validations.getIsValidOperation()) {
            response.setResponseBody(this.transactionBusiness.addTransaction(request));
        } else {
            getErrorMessagesFormated(response, validations);
        }
        return response;
    }

    public Response<TransactionDTO> show(Integer userId, String transactionId) {
        Response<TransactionDTO> response = new Response<>();
        TransactionDTO transactionRequestDTO = new TransactionDTO().addUser_id(userId).addTransaction_id(transactionId);
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateShowParams(transactionRequestDTO);
        if (!validations.getIsValidOperation()) {
            getErrorMessagesFormated(response, validations);
            return response;
        }
        TransactionDTO transactionResponseDTO = this.transactionBusiness.showTransaction(transactionRequestDTO);
        validations = this.transactionValidationBusiness.validateShowIfExist(transactionResponseDTO);
        if (!validations.getIsValidOperation()) {
            getErrorMessagesFormated(response, validations);
        } else {
            response.setResponseBody(transactionResponseDTO);;
        }
        return response;
    }

    public Response<List<TransactionDTO>> list(Integer userId) {
        Response<List<TransactionDTO>> response = new Response<>();
        TransactionDTO transactionRequestDTO = new TransactionDTO().addUser_id(userId);
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateListParams(transactionRequestDTO);
        if (validations.getIsValidOperation()) {
            response.setResponseBody(this.transactionBusiness.listTransactions(transactionRequestDTO));
        } else {
            getErrorMessagesFormated(response, validations);
        }
        return response;
    }

    public Response<SumDTO> sum(Integer userId) {
        Response<SumDTO> response = new Response<>();
        TransactionDTO transactionRequestDTO = new TransactionDTO().addUser_id(userId);
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateSumParams(transactionRequestDTO);
        if (validations.getIsValidOperation()) {
            response.setResponseBody(this.transactionBusiness.sumTransactions(transactionRequestDTO));
        } else {
            getErrorMessagesFormated(response, validations);
        }
        return response;
    }

    public Response<List<TransactionReportDTO>> report(Integer userId) {
        Response<List<TransactionReportDTO>> response = new Response<>();
        TransactionDTO transactionRequestDTO = new TransactionDTO().addUser_id(userId);
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateReportParams(transactionRequestDTO);
        if (validations.getIsValidOperation()) {
            response.setResponseBody(this.transactionBusiness.showTransactionReporteService(transactionRequestDTO));
        } else {
            getErrorMessagesFormated(response, validations);
        }
        return response;
    }

    public Response<TransactionDTO> random() {
        Response<TransactionDTO> response = new Response<>();
        response.setResponseBody(this.transactionBusiness.getRandomSingleTransaction());
        return response;
    }

    private void getErrorMessagesFormated(Response response, TransactionValidationsDTO validations) {
        response.setMessage(validations.getErrorMessages().stream().collect(Collectors.joining("; ")));
        response.setStatus(HttpStatus.NOT_ACCEPTABLE);
    }

}
