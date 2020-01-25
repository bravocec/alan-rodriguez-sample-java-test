/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author alan
 */
public class TransactionValidationsUtil {
    
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static boolean isValidFormat(String format, String value, Locale locale) {
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);
        try {
            LocalDate ld = LocalDate.parse(value, fomatter);
            String result = ld.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException dateTimeParseException) {

        }

        return false;
    }

}
