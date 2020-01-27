/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clip.demoproject.business.impl;

import com.clip.demoproject.business.TransactionBusiness;
import com.clip.demoproject.dto.TransactionDTO;
import com.clip.demoproject.dto.TransactionReportDTO;
import com.clip.demoproject.entities.TransactionEntity;
import com.clip.demoproject.exceptions.TransactionException;
import com.clip.demoproject.mapper.TransactionMapper;
import com.clip.demoproject.repository.TransactionRepository;
import com.clip.demoproject.util.TransactionValidationsUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alan Rodriguez
 */
@Service
public class TransactionBusinessImpl implements TransactionBusiness {

    private Logger log = LoggerFactory.getLogger(TransactionBusinessImpl.class);

    private final Long daysToAddOrSubstract = 1L;

    private DateFormat dateFormat;

    @PostConstruct
    public void init() {
        this.dateFormat = new SimpleDateFormat(TransactionValidationsUtil.DATE_PATTERN);
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionDTO addTransaction(TransactionDTO param) {
        TransactionEntity transactionEntity = TransactionMapper.transactionEntityToDTO(param);
        transactionEntity = this.transactionRepository.save(transactionEntity);
        return TransactionMapper.transactionDTOToEntity(transactionEntity);
    }

    @Override
    public TransactionDTO showTransaction(TransactionDTO param) {
        TransactionEntity transaction = this.transactionRepository.findById(param.getTransaction_id()).orElse(null);
        TransactionDTO transactionDTO = null;
        if (transaction != null) {
            this.log.info("Transaction found it with the id {}", param.getTransaction_id());
            if (transaction.getUserId().equals(param.getUser_id())) {
                transactionDTO = TransactionMapper.transactionDTOToEntity(transaction);
            } else {
                this.log.warn("The transaction {} exist but it not belongs to the user {}, the user id for the specific transaction is {}", transaction.getId(), param.getUser_id(), transaction.getUserId());
            }
        }
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> listTransactions(TransactionDTO param) {
        List<TransactionEntity> allTransactions = this.transactionRepository.findByUserId(param.getUser_id());
        return allTransactions
                .stream()
                .map(transaction -> TransactionMapper.transactionDTOToEntity(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO sumTransactions(TransactionDTO param) {
        List<TransactionEntity> allTransactions = this.transactionRepository.findByUserId(param.getUser_id());
        Double sum = 0.0;
        sum = allTransactions.stream().map(transaction -> transaction.getAmount()).reduce(sum, Double::sum);
        sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        return new TransactionDTO().addUser_id(param.getUser_id()).addAmount(sum);
    }

    @Override
    public List<TransactionReportDTO> showTransactionReporteService(TransactionDTO param) {
        List<TransactionEntity> allTransactions = this.transactionRepository.findByUserId(param.getUser_id());
        List<TransactionEntity> sortedTransactions = getSortedTransactions(allTransactions);
        return buildTransactionReport(sortedTransactions);
    }

    private List<TransactionReportDTO> buildTransactionReport(List<TransactionEntity> sortedTransactions) {
        List<TransactionReportDTO> report = new LinkedList<>();
        Double totalAmount = 0.0;
        for (TransactionEntity transactionEntity : sortedTransactions) {
            if (!existWeekOnTheReport(report, transactionEntity)) {
                this.log.info("The date {} was not found it on the array, init a new row",transactionEntity.getDate());
                report.add(getFirstRowOfTheWeek(transactionEntity, totalAmount));
            } else {
                addAmountAndQuantityToCurrent(report, transactionEntity);
            }
            totalAmount += transactionEntity.getAmount();
        }
        return report;
    }

    private void addAmountAndQuantityToCurrent(List<TransactionReportDTO> report, TransactionEntity transactionEntity) {
        LocalDate localDate = LocalDate.parse(transactionEntity.getDate());
        report.stream().filter(elementReport -> {
            return localDate.compareTo(elementReport.getStart()) >= 0 && localDate.compareTo(elementReport.getFinish()) <= 0;
        }).findFirst().ifPresent(rowReport -> {
            rowReport.setAmount(rowReport.getAmount() + transactionEntity.getAmount());
            rowReport.setQuantity(rowReport.getQuantity() + 1);
        });
    }

    private Boolean existWeekOnTheReport(List<TransactionReportDTO> report, TransactionEntity transactionEntity) {
        this.log.info("Searching {} on the report list ",transactionEntity.getDate());
        return report.stream().anyMatch(reportElement -> {
            return LocalDate.parse(transactionEntity.getDate()).compareTo(reportElement.getStart()) >= 0
                    && LocalDate.parse(transactionEntity.getDate()).compareTo(reportElement.getFinish()) <= 0;
        });
    }

    private TransactionReportDTO getFirstRowOfTheWeek(TransactionEntity transactionEntity, Double totalAmount) {
        Integer oneElement = 1;
        TransactionReportDTO transactionReportDTO = new TransactionReportDTO();
        transactionReportDTO.setId(transactionEntity.getUserId());
        transactionReportDTO.setAmount(transactionEntity.getAmount());
        transactionReportDTO.setQuantity(oneElement);
        transactionReportDTO.setTotalamount(totalAmount);
        buildStarAndFinish(transactionEntity.getDate(), transactionReportDTO, Boolean.TRUE);
        transactionReportDTO.setWeekStart(transactionReportDTO.getStart().format(DateTimeFormatter.ofPattern(TransactionValidationsUtil.DATE_PATTERN.concat(" EEEE"))));
        transactionReportDTO.setWeekFinish(transactionReportDTO.getFinish().format(DateTimeFormatter.ofPattern(TransactionValidationsUtil.DATE_PATTERN.concat(" EEEE"))));
        return transactionReportDTO;
    }

    private void validateIfRowReportCanFinishWell(TransactionReportDTO transactionReportDTO, Boolean isSumOperation) {
        if (transactionReportDTO.getCurrentLocalDate() == null || transactionReportDTO.getPreCurrentLocalDate() == null) {
            return;
        }
        if (!transactionReportDTO.getPreCurrentLocalDate().getMonth().equals(transactionReportDTO.getCurrentLocalDate().getMonth())) {
            LocalDate localDateToBeSet = isSumOperation ? transactionReportDTO.getCurrentLocalDate().minusDays(this.daysToAddOrSubstract) : transactionReportDTO.getCurrentLocalDate().plusDays(this.daysToAddOrSubstract);
            if (transactionReportDTO.getStart() != null) {
                transactionReportDTO.setFinish(localDateToBeSet);
                transactionReportDTO.setIsTheEndOfTheReport(Boolean.TRUE);
            } else if (transactionReportDTO.getFinish() != null) {
                transactionReportDTO.setStart(localDateToBeSet);
                transactionReportDTO.setIsTheEndOfTheReport(Boolean.TRUE);
            } else {
                throw new RuntimeException("The current repor algorithum doesn't support this feature");
            }
        }
    }

    private void buildStarAndFinish(String transactionDate, TransactionReportDTO transactionReportDTO, Boolean isSumOperation) {
        LocalDate currentLocalDate = transactionDate != null ? LocalDate.parse(transactionDate) : transactionReportDTO.getCurrentLocalDate();
        validateIfRowReportCanFinishWell(transactionReportDTO, isSumOperation);
        if (!transactionReportDTO.getIsTheEndOfTheReport()) {
            switch (currentLocalDate.getDayOfWeek()) {
                case FRIDAY:
                    transactionReportDTO.setStart(currentLocalDate);
                    if (transactionReportDTO.getFinish() == null) {
                        transactionReportDTO.setPreCurrentLocalDate(currentLocalDate);
                        transactionReportDTO.setCurrentLocalDate(currentLocalDate.plusDays(this.daysToAddOrSubstract));
                        buildStarAndFinish(null, transactionReportDTO, Boolean.TRUE);
                    }
                    break;
                case THURSDAY:
                    transactionReportDTO.setFinish(currentLocalDate);
                    if (transactionReportDTO.getStart() == null) {
                        transactionReportDTO.setPreCurrentLocalDate(currentLocalDate);
                        transactionReportDTO.setCurrentLocalDate(currentLocalDate.minusDays(this.daysToAddOrSubstract));
                        buildStarAndFinish(null, transactionReportDTO, Boolean.FALSE);
                    }
                    break;
                default:
                    if (isSumOperation) {
                        transactionReportDTO.setPreCurrentLocalDate(currentLocalDate);
                        transactionReportDTO.setCurrentLocalDate(currentLocalDate.plusDays(this.daysToAddOrSubstract));
                        buildStarAndFinish(null, transactionReportDTO, Boolean.TRUE);
                    } else {
                        transactionReportDTO.setPreCurrentLocalDate(currentLocalDate);
                        transactionReportDTO.setCurrentLocalDate(currentLocalDate.minusDays(this.daysToAddOrSubstract));
                        buildStarAndFinish(null, transactionReportDTO, Boolean.FALSE);
                    }
                    break;
            }
        }
    }

    private List<TransactionEntity> getSortedTransactions(List<TransactionEntity> allTransactions) {
        return allTransactions
                .stream()
                .sorted((TransactionEntity te1, TransactionEntity te2) -> parseTransactionDate(te1.getDate()).compareTo(parseTransactionDate(te2.getDate())))
                .collect(Collectors.toList());
    }

    private Date parseTransactionDate(String strDate) {
        Date parsedDatew = null;
        try {
            parsedDatew = this.dateFormat.parse(strDate);
        } catch (ParseException e) {
            this.log.error("It was an error at the moment to parse the date", e);
            throw new TransactionException(e);
        }
        return parsedDatew;
    }

    @Override
    public TransactionDTO getRandomSingleTransaction() {
        TransactionDTO transactionDTO = null;
        List<TransactionEntity> transactionEntity = this.transactionRepository.findAll();
        if(!transactionEntity.isEmpty()){
            Integer randomIndex = ThreadLocalRandom.current().nextInt(0, transactionEntity.size());
            this.log.info("Random index {} ",randomIndex);
            transactionDTO = TransactionMapper.transactionDTOToEntity(transactionEntity.get(randomIndex));
        }
        return transactionDTO;
    }

}
