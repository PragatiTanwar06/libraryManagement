package com.pragati.libmange.librarymanagement.dao;

import com.pragati.libmange.librarymanagement.vo.ReaderVO;

import java.util.List;

public interface UserDao {
    public ReaderVO findByReaderId(String readerId);
    public ReaderVO findByIdAndPassword(String id, String password);
    public void addReader(ReaderVO readerVO);
    public List<ReaderVO> findFrequentBorrowers(Long libId);
    public List<ReaderVO> getAllReaderID();

}
