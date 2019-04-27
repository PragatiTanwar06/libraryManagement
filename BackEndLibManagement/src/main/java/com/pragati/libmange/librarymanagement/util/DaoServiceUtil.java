package com.pragati.libmange.librarymanagement.util;

import com.pragati.libmange.librarymanagement.dao.*;
import com.pragati.libmange.librarymanagement.service.AdminDocumentSearchService;
import com.pragati.libmange.librarymanagement.service.ReaderFineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DaoServiceUtil {

//    @Autowired
//    public PasswordEncoder passwordEncoder;
    @Autowired
    public UserDao userDao;
    @Autowired
    public DocumentDao documentDao;
    @Autowired
    public ReserveDao reserveDao;
    @Autowired
    public BranchDao branchDao;
    @Autowired
    public FindLatestId findLatestId;
    @Autowired
    public BorrowsDao borrowsDao;
    @Autowired
    public ReaderFineService readerFineService;
    @Autowired
    public AdminDocumentSearchService adminDocumentSearchService;

    // sql property value
    @Value("${findUserByReaderId}")
    public String findUserByUsername;

    @Value("${findUserByIdAndPassword}")
    public String findUserByIdAndPassword;

    @Value("${findDocument}")
    public String findDocument;
    @Value("${saveIntoReserve}")
    public String insertIntoReserveSQL;

    @Value("${checkBorrowsAndReserveCount}")
    public String checkReserveAndBorrowsCount;

//    @Value("${findCopyNoToSaveIntoReserve}")
//    public String findCopyNoToInsertForReserve;

    @Value("${findDocumentTitleAndId}")
    public String findDocumentTitleAndId;

    @Value("${findBranchAll}")
    public String findBranchAll;

    @Value("${insertDocumentCopy}")
    public String insertDocumentCopy;

    @Value("${findCopyNoForInsertingDocCopy}")
    public String findNextCopyNumerForInsertingDocCopy;

    @Value("${insertReader}")
    public String insertReader;

    @Value("${showAllBranch}")
    public String showAllBranch;

    @Value("${insertBorrows}")
    public String insertIntoBorrows;

//    @Value("${findCopyNoToSaveIntoBorrow}")
//    public String findCopyNoToSaveInBorrows;

    @Value("${totalCountCopies}")
    public String totalNoCopiesWithDocidAndLibId;

    @Value("${findReservesDocument}")
    public String findReservedDocuments;

    @Value("${findBorrowedDocument}")
    public String findBorrowedDocuments;

    @Value("${deleteFromReserve}")
    public String deleteFromReserve;

    @Value("${updateDocumentInBorrows}")
    public String updateDocInBorrows;

    @Value("${findReaderInfoForFine}")
    public String findReaderInfoFroFine;

    @Value("${adminDocumentSearch}")
    public String findAdminDocumentSearch;

    @Value("${frequentBorrowers}")
    public String frequentBorrowers;

    @Value("${mostBorrowedBook}")
    public String mostBorrowedBook;

    @Value("${mostPopularBook}")
    public String mostPopularBook;

    @Value("${getAllReaderId}")
    public String allReaderIds;



}
