package com.pragati.libmange.librarymanagement.service;

import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.FinesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Service
public class ReaderFineService {

    @Autowired
    private DaoServiceUtil daoServiceUtil;
    private List<DocumentVO> documentVOList;
    private FinesVO finesVO;
    private String readerId;

    public FinesVO calculateFines(String readerId){
        this.readerId = readerId;
        this.finesVO = new FinesVO();
        findReaderInfo();
        if(this.documentVOList.size() > 0){
            doCalculation();
            calculateAvgAndTotalFine();
            calculateTotalFineAlreadyPaidAndAvg();
            return this.finesVO;
        }

        return null;
    }

    private void findReaderInfo(){
        this.documentVOList = daoServiceUtil.borrowsDao.findReaderForFines(readerId);
    }

    private void doCalculation(){
        for(DocumentVO documentVO : this.documentVOList){
            DocumentVO d = new DocumentVO();
            LocalDate borrowTime = convertStringToLocalDate(documentVO.getDocumentBorrowedDate());
            LocalDate returnedDate = convertStringToLocalDate(documentVO.getDocumentReturnDate());

            LocalDate dateToReturnDoc = borrowTime.plusDays(20);

            if ( returnedDate == null ) {
                if(dateToReturnDoc.isBefore(LocalDate.now())) {
                    long finesDatePeriodCount = calculateDays(dateToReturnDoc, LocalDate.now());
                    BigDecimal fine = (BigDecimal.valueOf(finesDatePeriodCount).multiply(BigDecimal.valueOf(20))).divide(BigDecimal.valueOf(100));
                    d.setFineToPay(fine);
                }
            }

            if(returnedDate != null && calculateDays(borrowTime,returnedDate) > 20){
                BigDecimal fineAlreadypaid = BigDecimal.valueOf(calculateDays(borrowTime,returnedDate)).multiply(BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(100));
                d.setFineAlreadyPaid(fineAlreadypaid);
            }
            if(returnedDate != null && calculateDays(borrowTime,returnedDate) <= 20){
                d.setFineAlreadyPaid(BigDecimal.ZERO);
            }
            d.setDocumentId(documentVO.getDocumentId());
            d.setBranchLocation(documentVO.getBranchLocation());
            d.setDocumentTitle(documentVO.getDocumentTitle());
            d.setDocumentReturnDate(documentVO.getDocumentReturnDate());
            d.setDocumentBorrowedDate(documentVO.getDocumentBorrowedDate());
            finesVO.addDocumentVO(d);
        }
    }

    private void calculateAvgAndTotalFine(){
        BigDecimal i = BigDecimal.valueOf(0);
        for(DocumentVO d : this.finesVO.getDocumentVO()){
            if(d.getFineToPay() != null){
                i = i.add(d.getFineToPay());
            }
        }
        finesVO.setTotalFinesToPay(i);
        BigDecimal avgFine = i.divide(BigDecimal.valueOf(this.finesVO.getDocumentVO().size()),2,HALF_UP);
        finesVO.setAvgFine(avgFine);
    }

    private void calculateTotalFineAlreadyPaidAndAvg(){
        BigDecimal i = BigDecimal.ZERO;
        int count = 0;
        for(DocumentVO d : this.finesVO.getDocumentVO()){
            if(d.getFineAlreadyPaid() != null && d.getDocumentReturnDate() != null){
                i = i.add(d.getFineAlreadyPaid());
                count ++;
            }
        }
        finesVO.setTotalFineAlreadyPaid(i);

        if( count != 0) {
            BigDecimal b = BigDecimal.valueOf(count);
            BigDecimal avg = i.divide(b, 2, HALF_UP);
            finesVO.setAvgFineAlreadyPaid(avg);
        }
    }




    private LocalDate convertStringToLocalDate(String date){
        if(date == null){
            return null;
        }
        String[] strArr = date.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDateTime = LocalDate.parse(strArr[0], formatter);
        return formatDateTime;
    }

    private String convertLocalDateToString(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }

    private long calculateDays(LocalDate borrowedDate,LocalDate dateToReturn){
//        Duration duration = Duration.between(borrowedDate,dateToReturn);
//        Period period = Period.between(borrowedDate,dateToReturn);
        long diff = ChronoUnit.DAYS.between(borrowedDate,dateToReturn);
        return diff;
    }
}
