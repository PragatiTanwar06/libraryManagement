package com.pragati.libmange.librarymanagement.dao;

import com.pragati.libmange.librarymanagement.vo.DocumentVO;

import java.util.List;

public interface DocumentDao {
    public List<DocumentVO> findDocumentByIdOrTitleOrPubName(DocumentVO documentVO);
    public List<DocumentVO> findAllDocumentTitleAndId();
    public void insertDocumentCopy(DocumentVO documentVO);
    public Long getNextCopyNumberForDocCopy(Long docId,Long libId);

    public Long findCopyNoToInsertInResAndBorrows(Long docId, Long libId, Long availableCopies);
    public List<DocumentVO> findAdminDocumentSearch(Long docId);
    public List<DocumentVO> findMostPopularBook();

}
