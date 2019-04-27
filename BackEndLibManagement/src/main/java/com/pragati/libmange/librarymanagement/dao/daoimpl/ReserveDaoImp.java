package com.pragati.libmange.librarymanagement.dao.daoimpl;

import com.pragati.libmange.librarymanagement.util.DBConnection;
import com.pragati.libmange.librarymanagement.dao.ReserveDao;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.util.FindLatestId;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import com.pragati.libmange.librarymanagement.vo.enums.DocumentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReserveDaoImp implements ReserveDao {
    @Autowired
    private DaoServiceUtil daoServiceUtil;
    @Autowired
    private FindLatestId findLatestId;
    @Autowired
    private DBConnection dbConnection;

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insertReserve(String readerId,Long docId, Long libId,Long availableCopies) {
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.insertIntoReserveSQL);
            ps.setString(1,readerId);
            ps.setLong(2,docId);
            ps.setLong(3,daoServiceUtil.documentDao.findCopyNoToInsertInResAndBorrows(docId,libId,availableCopies));
            ps.setLong(4,libId);
            Date date = new Date();
            Object param = new java.sql.Timestamp(date.getTime());
            ps.setObject(5,param);
            int i = ps.executeUpdate();
            System.out.print("");

        }catch (Exception e){
            throw new RuntimeException("Unable to insert reserve");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public boolean checkReserveAndBorrowsCount(String readerId) {
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.checkReserveAndBorrowsCount);
            ps.setString(1,readerId);
            ps.setString(2,readerId);
            rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    int num = rs.getInt("TOTAL");
                    if(num < 10)
                    return true;
                }
            }

            return false;
        }catch (Exception e){
            throw new RuntimeException("Unable to insert reserve");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<DocumentVO> findReservedDocuments(String readerId) {
        List<DocumentVO> documentVOList = new ArrayList<>();
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findReservedDocuments);
            ps.setString(1,readerId);
            rs = ps.executeQuery();
            if (rs!=null){
                while(rs.next()){
                    DocumentVO d = new DocumentVO();
                    d.setDocumentId(rs.getLong("DOCID"));
                    d.setDocumentTitle(rs.getString("TITLE"));
                    d.setLibId(rs.getLong("LIBID"));
                    d.setCopyNo(rs.getLong("COPYNO"));
                    d.setDocumentReservedDate(rs.getString("DTIME"));
                    d.setDocumentStatusEnum(DocumentStatusEnum.RESERVED);
                    documentVOList.add(d);
                }
            }
            return documentVOList;

        }catch (Exception e){
            throw new RuntimeException("Error in getting reserve documents");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

}
