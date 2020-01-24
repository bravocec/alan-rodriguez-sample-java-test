/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.repository;

import com.clip.demoproject.entities.TransactionEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alan Rodriguez
 */
@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String>{
    
    List<TransactionEntity> findByUserId(Integer userId);
    
}
