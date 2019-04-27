package com.pragati.libmange.librarymanagement.dao.daoimpl;

import com.pragati.libmange.librarymanagement.dao.UserDao;
import com.pragati.libmange.librarymanagement.util.DBConnection;
import com.pragati.libmange.librarymanagement.vo.ReaderVO;
import com.pragati.libmange.librarymanagement.vo.enums.ReaderTypeEnum;
import com.pragati.libmange.librarymanagement.util.DaoServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @Autowired
    private DBConnection dbConnection;

    @Override
    public ReaderVO findByReaderId(String readerId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ReaderVO readerVO = null;
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findUserByUsername);
            ps.setString(1,readerId);
            rs = ps.executeQuery();
            while (rs.next()){
                readerVO = new ReaderVO();
                readerVO.setReaderId(rs.getString("READERID"));
                readerVO.setName(rs.getString("RNAME"));
                readerVO.setAddress(rs.getString("ADDRESS"));
                readerVO.setReaderType(ReaderTypeEnum.valueOf(rs.getString("RTYPE")));
            }

            return readerVO;
        }catch (Exception e){
            System.out.println("Error in fetching user "+e.getMessage());
            dbConnection.rollback(con);
        }finally {
            dbConnection.close(con,ps,rs);
        }
        return null;
    }

    @Override
    public ReaderVO findByIdAndPassword(String id, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ReaderVO readerVO = null;
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findUserByIdAndPassword);
            ps.setString(1,id);
            ps.setString(2,password);
            rs = ps.executeQuery();
            while (rs.next()){
                readerVO = new ReaderVO();
                readerVO.setReaderId(rs.getString("READERID"));
                readerVO.setName(rs.getString("RNAME"));
                readerVO.setPassword(rs.getString("RPASSWORD"));
                readerVO.setAddress(rs.getString("ADDRESS"));
                readerVO.setReaderType(ReaderTypeEnum.valueOf(rs.getString("RTYPE")));
            }

            return readerVO;
        }catch (Exception e){
            System.out.println("Error in fetching user "+e.getMessage());
            dbConnection.rollback(con);
        }finally {
            dbConnection.close(con,ps,rs);
        }
        return null;
    }

    @Override
    public void addReader(ReaderVO readerVO) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps =con.prepareStatement(daoServiceUtil.insertReader);
            ps.setString(1,daoServiceUtil.findLatestId.getLatestId("READERID","READER"));
            ps.setString(2,readerVO.getPassword());
            ps.setString(3,readerVO.getReaderType().name());
            ps.setString(4,readerVO.getName());
            ps.setString(5,readerVO.getAddress());
            ps.executeUpdate();

        }catch (Exception ex){
            throw new RuntimeException("Error in inserting");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<ReaderVO> findFrequentBorrowers(Long libId) {
        List<ReaderVO> readerVoLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.frequentBorrowers);
            ps.setLong(1,libId);
            rs = ps.executeQuery();
            if(rs != null){
                while (rs.next()){
                    ReaderVO r = new ReaderVO();
                    r.setReaderId(rs.getString("READERID"));
                    r.setName(rs.getString("RNAME"));
                    r.setNumOfCopiesBorrowed(rs.getInt("No_book_borrowed"));
                    readerVoLst.add(r);
                }
            }
            return readerVoLst;
        }catch (Exception e){
            throw new RuntimeException("Error getting inforation");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<ReaderVO> getAllReaderID() {
        List<ReaderVO> readerVoLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.allReaderIds);
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    ReaderVO r = new ReaderVO();
                    r.setReaderId(rs.getString("READERID"));
                    readerVoLst.add(r);
                }
            }
            return readerVoLst;
        }catch (Exception e){
            throw new RuntimeException("Error getting inforation");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }
}
