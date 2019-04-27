package com.pragati.libmange.librarymanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class FindLatestId {

    @Autowired
    private DBConnection dbConnection;

    private String resultId;
    private String idColumn;
    private String className;
    private String lastId;

    public String getLatestId(String idColumn,String className){
        this.className = className;
        this.idColumn = idColumn;
        try{
            findId();
            if(this.lastId == null || this.lastId.equals("")){
                throw new RuntimeException("Insert First Record Manually");
            }
            getResult();
            return  resultId;
        }catch (Exception e){
            throw new RuntimeException("Error in getting column id");
        }
    }

    private void findId(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dbConnection.getConnection();
            String SQL = "select "+ idColumn + " from "+className + " order by " + idColumn + " desc LIMIT 1";
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                this.lastId = rs.getString(idColumn);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbConnection.close(con,ps,rs);
        }
    }

    private void getResult(){
        String[] strArr = this.lastId.split("-");
        int num = Integer.parseInt(strArr[1]) + 1;
        this.resultId = strArr[0] + "-" + String.valueOf(num);
    }
}
