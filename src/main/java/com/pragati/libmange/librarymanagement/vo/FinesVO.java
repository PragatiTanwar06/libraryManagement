package com.pragati.libmange.librarymanagement.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinesVO {
    private BigDecimal totalFinesToPay = BigDecimal.ZERO;
    private BigDecimal avgFine = BigDecimal.ZERO;

    private BigDecimal avgFineAlreadyPaid = BigDecimal.ZERO;
    private BigDecimal totalFineAlreadyPaid = BigDecimal.ZERO;

    private List<DocumentVO> documentVO = new ArrayList<>();

    public BigDecimal getTotalFinesToPay() {
        return totalFinesToPay;
    }

    public void setTotalFinesToPay(BigDecimal totalFinesToPay) {
        this.totalFinesToPay = totalFinesToPay;
    }

    public List<DocumentVO> getDocumentVO() {
        return documentVO;
    }

    public void addDocumentVO(DocumentVO documentVO) {
        this.documentVO.add(documentVO);
    }

    public BigDecimal getAvgFine() {
        return avgFine;
    }

    public void setAvgFine(BigDecimal avgFine) {
        this.avgFine = avgFine;
    }

    public BigDecimal getAvgFineAlreadyPaid() {
        return avgFineAlreadyPaid;
    }

    public void setAvgFineAlreadyPaid(BigDecimal avgFineAlreadyPaid) {
        this.avgFineAlreadyPaid = avgFineAlreadyPaid;
    }

    public BigDecimal getTotalFineAlreadyPaid() {
        return totalFineAlreadyPaid;
    }

    public void setTotalFineAlreadyPaid(BigDecimal totalFineAlreadyPaid) {
        this.totalFineAlreadyPaid = totalFineAlreadyPaid;
    }
}
