package com.pragati.libmange.librarymanagement.dao.daoimpl;

import com.pragati.libmange.librarymanagement.dao.DocumentDao;
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
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private DaoServiceUtil daoServiceUtil;

    @Autowired
    private DBConnection dbConnection;

    @Override
    public List<DocumentVO> findDocumentByIdOrTitleOrPubName(DocumentVO documentVO) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(documentVO == null){
            return null;
        }
        List<DocumentVO> documentVOList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(daoServiceUtil.findDocument);
        try {
            con = dbConnection.getConnection();

//            String groupBy = " AND not exists (select * from RESERVES where DOCID = doc.DOCID and  libid=cp.LIBID  and COPYNO=CP.COPYNO) AND not exists (select * from BORROWS where DOCID = doc.DOCID and  libid=cp.LIBID  and COPYNO=CP.COPYNO and RDTIME = null) group by br.LIBID,doc.DOCID, res.RESUMBER ORDER BY doc.DOCID ASC";
            String groupBy = " group by br.LIBID,doc.DOCID, doc.title, br.LLOCATION";
            if(documentVO.getDocumentId() != 0 && (documentVO.getDocumentTitle().equals("") && documentVO.getDocPublisherName().equals(""))){
                sb.append("doc.DOCID = ?" + groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setLong(1,documentVO.getDocumentId());
            }

            if(!documentVO.getDocumentTitle().equals("") && (documentVO.getDocumentId() == 0 && documentVO.getDocPublisherName().equals(""))){
                sb.append("doc.TITLE = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setString(1,documentVO.getDocumentTitle());
            }

            if(!documentVO.getDocPublisherName().equals("") && (documentVO.getDocumentTitle().equals("") && documentVO.getDocumentId() == 0)){
                sb.append("pub.PUBNAME = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setString(1,documentVO.getDocPublisherName());
            }

            if(documentVO.getDocumentId() != 0 && !documentVO.getDocumentTitle().equals("") && (documentVO.getDocPublisherName().equals(""))){
                sb.append("doc.DOCID = ? AND doc.TITLE = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setLong(1,documentVO.getDocumentId());
                ps.setString(2,documentVO.getDocumentTitle());
            }

            if(documentVO.getDocumentId() != 0 && !documentVO.getDocPublisherName().equals("") && (documentVO.getDocumentTitle().equals(""))){
                sb.append("doc.DOCID = ? AND pub.PUBNAME = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setLong(1,documentVO.getDocumentId());
                ps.setString(2,documentVO.getDocPublisherName());

            }

            if(!documentVO.getDocumentTitle().equals("") && !documentVO.getDocPublisherName().equals("") && (documentVO.getDocumentId() == 0)){
                sb.append("doc.TITLE = ? AND pub.PUBNAME = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setString(1,documentVO.getDocumentTitle());
                ps.setString(2,documentVO.getDocPublisherName());
            }

            if(!documentVO.getDocumentTitle().equals("") && !documentVO.getDocPublisherName().equals("") && documentVO.getDocumentId() != 0){
                sb.append("doc.TITLE = ? AND pub.PUBNAME = ? AND doc.DOCID = ?"+groupBy);
                ps = con.prepareStatement(sb.toString());
                ps.setString(1,documentVO.getDocumentTitle());
                ps.setString(2,documentVO.getDocPublisherName());
                ps.setLong(3,documentVO.getDocumentId());
            }

//            if(documentVO.getDocumentTitle().equals("") && documentVO.getDocPublisherName().equals("") && documentVO.getDocumentId() == 0){
//                sb.append("doc.DOCID = ? AND doc.TITLE = ? AND pub.PUBNAME = ?"+groupBy);
//                ps = con.prepareStatement(sb.toString());
//                ps.setLong(1,documentVO.getDocumentId());
//                ps.setString(2,documentVO.getDocumentTitle());
//                ps.setString(3,documentVO.getDocPublisherName());
//
//            }

            rs = ps.executeQuery();
            while (rs.next()){
                DocumentVO d = new DocumentVO();
                d.setDocumentId(rs.getLong("DOCID"));
                d.setDocumentTitle(rs.getString("TITLE"));
//                d.setDocPublishedDate(rs.getString("PDATE"));
                d.setLibId(rs.getLong("LIBID"));
                d.setBranchLocation(rs.getString("LLOCATION"));
//                d.setDocPublisherName(rs.getString("PUBNAME"));
                d.setTotalAvailableCopies(rs.getInt("COPIES_TOTAL"));
                documentVOList.add(d);
            }

            return documentVOList;

        }catch(Exception e){
            System.out.println("Error in fetching user "+e.getMessage());
            dbConnection.rollback(con);
        }finally {
            dbConnection.close(con,ps,rs);
        }
        return null;
    }

    @Override
    public List<DocumentVO> findAllDocumentTitleAndId() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocumentVO> documentVOList = new ArrayList<>();
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findDocumentTitleAndId);
            rs = ps.executeQuery();
            if (rs!=null){
                while(rs.next()){
                    DocumentVO d = new DocumentVO();
                    d.setDocumentId(rs.getLong("DOCID"));
                    d.setDocumentTitle(rs.getString("TITLE"));
                    documentVOList.add(d);
                }
                return documentVOList;
            }
            return null;

        }catch (Exception ex){
            throw new RuntimeException("Error in fetching Documents");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public void insertDocumentCopy(DocumentVO documentVO) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.insertDocumentCopy);
            ps.setLong(1,documentVO.getDocumentId());
            ps.setLong(2,getNextCopyNumberForDocCopy(documentVO.getDocumentId(),documentVO.getLibId()));
            ps.setLong(3,documentVO.getLibId());
            ps.setString(4,documentVO.getDocumentCopyPosition());
            ps.executeUpdate();
        }catch (Exception ex){
            throw new RuntimeException("Error in inserting document copy");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public Long getNextCopyNumberForDocCopy(Long docId, Long libId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findNextCopyNumerForInsertingDocCopy);
            ps.setLong(1,docId);
            ps.setLong(2,libId);
            rs = ps.executeQuery();
            System.out.println(daoServiceUtil.findNextCopyNumerForInsertingDocCopy);
            if(rs != null){
                while(rs.next()){
                    Long copyNo = rs.getLong("TOTAL");
                    if( copyNo != null){
                        return copyNo+1;
                    }
                }
                if(!rs.next()){
                    return Long.valueOf("1");
                }
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException("Unbale to get Copy Number");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public Long findCopyNoToInsertInResAndBorrows(Long docId, Long libId,Long availableCopies) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.totalNoCopiesWithDocidAndLibId);
            ps.setLong(1,docId);
            ps.setLong(2,libId);
            rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    Long totalCount = rs.getLong("TOTAL_COUNT") + 1;
                    Long result = totalCount - availableCopies;
                    if (result != 0){
                        return result;
                    }
                }
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException("Unbale to get Copy Number");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<DocumentVO> findAdminDocumentSearch(Long docId) {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.findAdminDocumentSearch);
            ps.setLong(1,docId);
            rs = ps.executeQuery();
            if(rs != null){
                while (rs.next()){
                    DocumentVO d = new DocumentVO();
                    d.setDocumentId(rs.getLong("DOCID"));
                    d.setCopyNo(rs.getLong("COPYNO"));
                    d.setBranchLocation(rs.getString("LLOCATION"));
                    d.setDocumentReservedDate(rs.getString("DTIME"));
                    d.setDocumentBorrowedDate(rs.getString("BDTIME"));
                    d.setDocumentReturnDate(rs.getString("RDTIME"));
                    documentVOList.add(d);
                }
            }
            return documentVOList;
        }catch (Exception e){
            throw new RuntimeException("Error in Searching Document");
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    @Override
    public List<DocumentVO> findMostPopularBook() {
        List<DocumentVO> documentVOList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            ps = con.prepareStatement(daoServiceUtil.mostPopularBook);
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
