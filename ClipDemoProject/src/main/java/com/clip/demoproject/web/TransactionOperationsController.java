/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.web;

import com.clip.demoproject.business.TransactionBusiness;
import com.clip.demoproject.config.Response;
import com.clip.demoproject.dto.TransactionDTO;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alan Rodriguez
 */
@RestController
@RequestMapping("/transactions")
public class TransactionOperationsController {

    @Autowired
    private TransactionBusiness transactionBusiness;

    @PostMapping("/add")
    public ResponseEntity<Response<TransactionDTO>> add(@RequestBody TransactionDTO request) {
        TransactionDTO responseDTO = this.transactionBusiness.addTransaction(request);
        Response<TransactionDTO> response = new Response<>(responseDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/show")
    public ResponseEntity<Response<TransactionDTO>> show(@RequestParam("user_id") Integer userId, @RequestParam("transaction_id") String transactionId) {
        TransactionDTO responseDTO = this.transactionBusiness.showTransaction(new TransactionDTO().addUser_id(userId).addTransaction_id(transactionId));
        Response<TransactionDTO> response = responseDTO == null ? new Response<>(HttpStatus.NOT_FOUND, "Transaction not found") : new Response<>(responseDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/list")
    public ResponseEntity<Response<List<TransactionDTO>>> list(@RequestParam("user_id")Integer userId) {
        List<TransactionDTO> listResponseDTO = this.transactionBusiness.listTransactions(new TransactionDTO().addUser_id(userId));
        Response<List<TransactionDTO>> response = new Response<>(listResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/sum")
    public ResponseEntity<Response<TransactionDTO>> sum(@RequestParam("user_id")Integer userId) {
        TransactionDTO responseDTO = this.transactionBusiness.sumTransactions(new TransactionDTO().addUser_id(userId));
        Response<TransactionDTO> response = new Response<>(responseDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/report")
    public ResponseEntity<Response<List<TransactionDTO>>> report(@RequestParam("user_id")Integer userId) {
        return null;
    }
    
    @GetMapping("/randomTransaction")
    public ResponseEntity<Response<TransactionDTO>> random() {
        return null;
    }

}
