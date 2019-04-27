package com.pragati.libmange.librarymanagement.vo;

import com.pragati.libmange.librarymanagement.vo.enums.DocumentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DocumentVO {
    private long documentId;
    private String documentTitle;
    private String docPublishedDate;
    private String docPublisherName;
    private String branchLocation;
    private String documentCopyPosition;
    private int totalAvailableCopies;
    private long libId;
    private String branchName;
    private Long copyNo;
    private String documentBorrowedDate;
    private String documentReturnDate;
    private DocumentStatusEnum documentStatusEnum;
    private String documentReservedDate;
    private Long borrowsNumber;
    private int numberOfBookBorrowed;
    // for fines
    private BigDecimal fineToPay = BigDecimal.ZERO;
    private BigDecimal fineAlreadyPaid = BigDecimal.ZERO;



    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getDocPublishedDate() {
        return docPublishedDate;
    }

    public void setDocPublishedDate(String docPublishedDate) {
        this.docPublishedDate = docPublishedDate;
    }

    public String getDocPublisherName() {
        return docPublisherName;
    }

    public void setDocPublisherName(String docPublisherName) {
        this.docPublisherName = docPublisherName;
    }

    public int getTotalAvailableCopies() {
        return totalAvailableCopies;
    }

    public void setTotalAvailableCopies(int totalAvailableCopies) {
        this.totalAvailableCopies = totalAvailableCopies;
    }

    public long getLibId() {
        return libId;
    }

    public void setLibId(long libId) {
        this.libId = libId;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    public String getDocumentCopyPosition() {
        return documentCopyPosition;
    }

    public void setDocumentCopyPosition(String documentCopyPosition) {
        this.documentCopyPosition = documentCopyPosition;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(Long copyNo) {
        this.copyNo = copyNo;
    }

    public String getDocumentBorrowedDate() {
        return documentBorrowedDate;
    }

    public void setDocumentBorrowedDate(String documentBorrowedDate) {
        this.documentBorrowedDate = documentBorrowedDate;
    }

    public String getDocumentReturnDate() {
        return documentReturnDate;
    }

    public void setDocumentReturnDate(String documentReturnDate) {
        this.documentReturnDate = documentReturnDate;
    }

    public DocumentStatusEnum getDocumentStatusEnum() {
        return documentStatusEnum;
    }

    public void setDocumentStatusEnum(DocumentStatusEnum documentStatusEnum) {
        this.documentStatusEnum = documentStatusEnum;
    }

    public String getDocumentReservedDate() {
        return documentReservedDate;
    }

    public void setDocumentReservedDate(String documentReservedDate) {
        this.documentReservedDate = documentReservedDate;
    }

    public Long getBorrowsNumber() {
        return borrowsNumber;
    }

    public void setBorrowsNumber(Long borrowsNumber) {
        this.borrowsNumber = borrowsNumber;
    }

    public BigDecimal getFineToPay() {
        return fineToPay;
    }

    public void setFineToPay(BigDecimal fineToPay) {
        this.fineToPay = fineToPay;
    }

    public BigDecimal getFineAlreadyPaid() {
        return fineAlreadyPaid;
    }

    public void setFineAlreadyPaid(BigDecimal fineAlreadyPaid) {
        this.fineAlreadyPaid = fineAlreadyPaid;
    }

    public int getNumberOfBookBorrowed() {
        return numberOfBookBorrowed;
    }

    public void setNumberOfBookBorrowed(int numberOfBookBorrowed) {
        this.numberOfBookBorrowed = numberOfBookBorrowed;
    }
}
