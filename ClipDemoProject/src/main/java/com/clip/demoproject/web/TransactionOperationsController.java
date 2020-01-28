/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.web;

import com.clip.demoproject.config.Response;
import com.clip.demoproject.dto.SumDTO;
import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionReportDTO;
import com.clip.demoproject.facade.TransactionFacade;
import java.util.List;
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
    private TransactionFacade transactionFacade;

    @PostMapping("/add")
    public ResponseEntity<Response<TransactionDTO>> add(@RequestBody TransactionDTO request) {
        Response<TransactionDTO> response = this.transactionFacade.add(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/show")
    public ResponseEntity<Response<TransactionDTO>> show(@RequestParam("user_id") Integer userId, @RequestParam("transaction_id") String transactionId) {
        Response<TransactionDTO> response = this.transactionFacade.show(userId, transactionId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/list")
    public ResponseEntity<Response<List<TransactionDTO>>> list(@RequestParam("user_id")Integer userId) {
        Response<List<TransactionDTO>> response = this.transactionFacade.list(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/sum")
    public ResponseEntity<Response<SumDTO>> sum(@RequestParam("user_id")Integer userId) {
        Response<SumDTO> response = this.transactionFacade.sum(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/report")
    public ResponseEntity<Response<List<TransactionReportDTO>>> report(@RequestParam("user_id")Integer userId) {
        Response<List<TransactionReportDTO>> response = this.transactionFacade.report(userId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
    
    @GetMapping("/randomTransaction")
    public ResponseEntity<Response<TransactionDTO>> random() {
        Response<TransactionDTO> response = this.transactionFacade.random();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

}
