package com.pragati.libmange.librarymanagement.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class DBConnection {

    @Autowired
    private DataSource dataSource;

    public Connection getConnection(){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            return con;
        }catch (Exception e){
            System.out.println("Unable to get Connection "+e.getMessage());
            close(con,null,null);
        }
        return null;
    }

    public void close(Connection connection,PreparedStatement ps,ResultSet rs){
        try{
            if(connection != null) {
                connection.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(rs != null) {
                rs.close();
            }
        }catch (Exception e){
            System.out.println("Unable to close connections " +e.getMessage() );
        }
    }

    public void rollback(Connection con){
        try{
            con.rollback();
        }catch (Exception e){
            System.out.println("unable to rollback "+e.getMessage());
        }
    }


}
