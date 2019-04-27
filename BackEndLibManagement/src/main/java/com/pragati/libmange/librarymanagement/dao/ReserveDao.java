package com.pragati.libmange.librarymanagement.dao;

import com.pragati.libmange.librarymanagement.vo.DocumentVO;

import java.util.List;

public interface ReserveDao {

    public void insertReserve(String readerId,Long docId,Long libId,Long availableCopies);
    public boolean checkReserveAndBorrowsCount(String readerId);
    public List<DocumentVO> findReservedDocuments(String readerId);

}
