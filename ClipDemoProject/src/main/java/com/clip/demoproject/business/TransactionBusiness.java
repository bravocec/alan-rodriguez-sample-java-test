/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.business;

import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionReportDTO;
import java.util.List;

/**
 *
 * @author Alan Rodriguez
 */
public interface TransactionBusiness {
    
    TransactionDTO addTransaction(TransactionDTO param);
    
    TransactionDTO showTransaction(TransactionDTO param);
    
    List<TransactionDTO> listTransactions(TransactionDTO param);
    
    TransactionDTO sumTransactions(TransactionDTO param);
    
    List<TransactionReportDTO> showTransactionReporteService(TransactionDTO param);
    
    TransactionDTO getRandomSingleTransaction();
    
}
