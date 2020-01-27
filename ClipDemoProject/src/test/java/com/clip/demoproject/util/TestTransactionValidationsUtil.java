/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.util;

import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alan
 */
public class TestTransactionValidationsUtil {
    
    public TestTransactionValidationsUtil() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        Locale.setDefault(Locale.US);
    }
    
    
     @Test
     public void dateFormatOk() {
         boolean validDateFormat = TransactionValidationsUtil.isValidFormat("yyyy-MM-dd", "2020-01-01", Locale.getDefault());
         assertTrue(validDateFormat);
     }
     
     @Test
     public void wrongDateFormatOk() {
         boolean validDateFormat = TransactionValidationsUtil.isValidFormat("yyyy-MM-dd", "2020/31/31", Locale.getDefault());
         assertTrue(!validDateFormat);
     }
}
