/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.facade;

import com.clip.demoproject.business.TransactionBusiness;
import com.clip.demoproject.business.TransactionValidationBusiness;
import com.clip.demoproject.config.Response;
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
    
    public Response<TransactionDTO> add(TransactionDTO request){
        Response<TransactionDTO> response = new Response<>();
        TransactionValidationsDTO validations = this.transactionValidationBusiness.validateAddParams(request);
        if(validations.getIsValidOperation()){
            response.setResponseBody(this.transactionBusiness.addTransaction(request));
        }else{
            getErrorMessagesFormated(response, validations);
        }
        return response;
    }
    
    
    public Response<TransactionDTO> show(Integer userId,String transactionId){
        TransactionDTO transactionDTO = new TransactionDTO().addUser_id(userId).addTransaction_id(transactionId);
        return null;
    }
    
    public Response<List<TransactionDTO>> list(Integer userId){
        return null;
    }
    
    public Response<TransactionDTO> sum(Integer userId){
        return null;
    }
    
    public Response<List<TransactionReportDTO>> report(Integer userId){
        return null;
    }
    
    
    public Response<TransactionDTO> random(){
        return null;
    }
    
    private void getErrorMessagesFormated(Response<TransactionDTO> response,TransactionValidationsDTO validations){
        response.setMessage(validations.getErrorMessages().stream().collect(Collectors.joining("\n")));
        response.setStatus(HttpStatus.NOT_ACCEPTABLE);
    }
    
}
