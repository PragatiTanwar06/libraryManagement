package com.pragati.libmange.librarymanagement.dao.daoimpl;

import com.pragati.libmange.librarymanagement.dao.BorrowsDao;
import com.pragati.libmange.librarymanagement.util.DBConnection;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.ReaderVO;
import com.pragati.libmange.librarymanagement.vo.enums.DocumentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.Document;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BorrowsDaoImp implements BorrowsDao {
    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @Autowired
    private DBConnection dbConnection;

    @Override
    public void insertIntoBorrows(String readerId, Long docId, Long libId,Long availableCopies, Long copyNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("insert into borrows failed");
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.insertIntoBorrows);
            ps.setString(1,readerId);
            ps.setLong(2,docId);
            if(availableCopies != null && copyNo == null) {
                ps.setLong(3, daoServiceUtil.documentDao.findCopyNoToInsertInResAndBorrows(docId, libId, availableCopies));
            }
            if(copyNo != null && availableCopies == null){
                ps.setLong(3,copyNo);
            }
            ps.setLong(4,libId);
            Date date = new Date();
            Object param = new java.sql.Timestamp(date.getTime());
            ps.setObject(5,param);
            ps.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException("Error in inserting into borrows");
        }finally {
            dbConnection.close(con,ps,null);
        }
    }

    @Override
    public List<DocumentVO> findBorrowedDocuments(String readerId) {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findBorrowedDocuments);
            ps.setString(1,readerId);
            rs = ps.executeQuery();
            if (rs!=null){
                while(rs.next()){
                    DocumentVO d = new DocumentVO();
                    d.setDocumentId(rs.getLong("DOCID"));
                    d.setDocumentTitle(rs.getString("TITLE"));
                    d.setLibId(rs.getLong("LIBID"));
                    d.setCopyNo(rs.getLong("COPYNO"));
                    d.setDocumentBorrowedDate(rs.getString("BDTIME"));
                    d.setBorrowsNumber(rs.getLong("BORNUMBER"));
                    d.setBranchLocation(rs.getString("LLOCATION"));
                    d.setDocumentStatusEnum(DocumentStatusEnum.BORROWED);
                    documentVOList.add(d);
                }
            }
            return documentVOList;
        }catch(Exception e){
            throw new RuntimeException("Error in getting borrowed documents");
        }finally {
            dbConnection.close(con,ps,null);
        }
    }

    @Override
    public void transferDocFromResToBorrow(String readerId,Long docId, Long copyId, Long libId) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dbConnection.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(daoServiceUtil.deleteFromReserve);
            ps.setLong(1,docId);
            ps.setLong(2,copyId);
            ps.setLong(3,libId);
            ps.setString(4,readerId);
            int i = ps.executeUpdate();
            insertIntoBorrows(readerId,docId,libId,null,copyId);
            con.commit();
        }catch (Exception e){
            dbConnection.rollback(con);
            throw new RuntimeException("Error in deleting and inserting");
        }finally {
            dbConnection.close(con,ps,null);
        }
    }

    @Override
    public void returnDocument(Long borrowNumber) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(daoServiceUtil.updateDocInBorrows);
            Date date = new Date();
            Object param = new java.sql.Timestamp(date.getTime());
            ps.setObject(1,param);
            ps.setLong(2,borrowNumber);
            int i = ps.executeUpdate();
            con.commit();
        }catch(Exception e){
            dbConnection.rollback(con);
            throw new RuntimeException("Error in updating");
        }finally {
            dbConnection.close(con,ps,null);
        }
    }

    @Override
    public List<DocumentVO> findReaderForFines(String readerId) {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findReaderInfoFroFine);
            ps.setString(1,readerId);
            rs = ps.executeQuery();
            if (rs != null){
                while(rs.next()) {
                    DocumentVO d = new DocumentVO();
                    d.setDocumentTitle(rs.getString("TITLE"));
                    d.setDocumentId(rs.getLong("DOCID"));
                    d.setDocumentReturnDate(rs.getString("RDTIME"));
                    d.setDocumentBorrowedDate(rs.getString("BDTIME"));
                    d.setBranchLocation(rs.getString("LLOCATION"));
                    documentVOList.add(d);
                }
            }
            return documentVOList;

        }catch (Exception e){
            throw new RuntimeException("Error getting inforation about fines");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }
}
