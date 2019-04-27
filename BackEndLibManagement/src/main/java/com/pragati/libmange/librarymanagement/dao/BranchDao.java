package com.pragati.libmange.librarymanagement.dao;

import com.pragati.libmange.librarymanagement.vo.DocumentVO;

import java.util.List;

public interface BranchDao {
    public List<DocumentVO> findBranchAll();
    public List<DocumentVO> findMostBorrowedBook(Long libId);
}
