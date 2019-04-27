package com.pragati.libmange.librarymanagement.dao.daoimpl;

import com.pragati.libmange.librarymanagement.dao.BranchDao;
import com.pragati.libmange.librarymanagement.util.DBConnection;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import com.pragati.libmange.librarymanagement.vo.DocumentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BranchDaoImpl implements BranchDao {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @Autowired
    private DBConnection dbConnection;

    @Override
    public List<DocumentVO> findBranchAll() {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findBranchAll);
            rs = ps.executeQuery();
            if (rs != null){
                while(rs.next()){
                    DocumentVO documentVO = new DocumentVO();
                    documentVO.setLibId(rs.getLong("LIBID"));
                    documentVO.setBranchLocation(rs.getString("LLOCATION"));
                    documentVO.setBranchName(rs.getString("LNAME"));
                    documentVOList.add(documentVO);
                }
                return documentVOList;
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error in getting Branch info");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<DocumentVO> findMostBorrowedBook(Long libId) {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.mostBorrowedBook);
            ps.setLong(1,libId);
            rs = ps.executeQuery();
            if (rs != null){
                while(rs.next()){
                    DocumentVO documentVO = new DocumentVO();
                    documentVO.setDocumentTitle(rs.getString("TITLE"));
                    documentVO.setNumberOfBookBorrowed(rs.getInt("no_times_borrowed"));
                    documentVOList.add(documentVO);
                }
                return documentVOList;
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException("Error in getting info");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

}
