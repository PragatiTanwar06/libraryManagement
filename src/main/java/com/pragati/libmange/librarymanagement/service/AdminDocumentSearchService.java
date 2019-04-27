package com.pragati.libmange.librarymanagement.service;

import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.enums.DocumentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDocumentSearchService {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    public List<DocumentVO> searchDocument(Long docId){
        List<DocumentVO> documentVOList = new ArrayList<>();
        for(DocumentVO doc : daoServiceUtil.documentDao.findAdminDocumentSearch(docId)){
            DocumentVO d = new DocumentVO();
            if(doc.getDocumentReservedDate() == null && doc.getDocumentBorrowedDate() == null && doc.getDocumentReturnDate() == null){
                d.setDocumentStatusEnum(DocumentStatusEnum.AVAILABLE);
            }
            if(doc.getDocumentReservedDate() != null && doc.getDocumentBorrowedDate() == null && doc.getDocumentReturnDate() == null){
                d.setDocumentStatusEnum(DocumentStatusEnum.RESERVED);
            }
            if(doc.getDocumentBorrowedDate() != null && doc.getDocumentReservedDate() ==null && doc.getDocumentReturnDate() == null){
                d.setDocumentStatusEnum(DocumentStatusEnum.BORROWED);
            }
            if(doc.getDocumentReturnDate() != null && doc.getDocumentBorrowedDate() != null && doc.getDocumentReservedDate() == null){
                d.setDocumentStatusEnum(DocumentStatusEnum.AVAILABLE);
            }
            d.setDocumentId(doc.getDocumentId());
            d.setCopyNo(doc.getCopyNo());
            d.setBranchLocation(doc.getBranchLocation());
            documentVOList.add(d);
        }
        return documentVOList;
    }
}
