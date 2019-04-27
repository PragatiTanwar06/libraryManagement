package com.pragati.libmange.librarymanagement.dao;

import com.pragati.libmange.librarymanagement.vo.DocumentVO;

import java.util.List;

public interface BorrowsDao {
    public void insertIntoBorrows(String readerId,Long docId, Long libId,Long availableCopies, Long copyNo);
    public List<DocumentVO> findBorrowedDocuments(String readerId);
    public void transferDocFromResToBorrow(String readerId,Long docId, Long copyId,Long libId);
    public void returnDocument(Long borrowNumber);
    public List<DocumentVO> findReaderForFines(String readerIds);

}
