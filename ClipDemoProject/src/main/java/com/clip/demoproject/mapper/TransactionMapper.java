/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.mapper;

import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.entities.TransactionEntity;

/**
 *
 * @author Alan Rodriguez
 */
public class TransactionMapper {
    
    public static TransactionEntity transactionEntityToDTO(TransactionDTO transactionDTO){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setDate(transactionDTO.getDate());
        transactionEntity.setDescription(transactionDTO.getDescription());
        transactionEntity.setId(transactionDTO.getTransaction_id());
        transactionEntity.setUserId(transactionDTO.getUser_id());
        return transactionEntity;
    }
    
    public static TransactionDTO transactionDTOToEntity(TransactionEntity transactionEntity){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setDate(transactionEntity.getDate());
        transactionDTO.setDescription(transactionEntity.getDescription());
        transactionDTO.setTransaction_id(transactionEntity.getId());
        transactionDTO.setUser_id(transactionEntity.getUserId());
        return transactionDTO;
    }
    
}
