/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.mapper;

import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.entities.TransactionEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author roal7004
 */
public class TestTransactionMapper {

    @Test
    public void transactionEntityToDTO() {
        TransactionEntity transactionEntity = TransactionMapper.transactionEntityToDTO(new TransactionDTO().addAmount(100.00).addUser_id(1));
        assertEquals(100.00, transactionEntity.getAmount());
    }

    @Test
    public void transactionDTOToEntity() {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(100.00);
        transactionEntity.setUserId(1);
        TransactionDTO transactionDTO = TransactionMapper.transactionDTOToEntity(transactionEntity);
        assertEquals(100.00, transactionDTO.getAmount());
    }

}
