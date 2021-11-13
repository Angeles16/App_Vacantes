package net.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection {

    private String db = "empleo";
    private String user = "root";
    private String password = "ANGEL";
    private String url = "jdbc:mysql://localhost/" + db;

    Connection conn = null;

    public dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            if(conn != null){
                System.out.println("Connection Successful");
            }
            }  catch (SQLException ex) {
                System.err.println("Error en la coneccion ==> " + ex);
        } catch (ClassNotFoundException ex) {
            System.err.println("Error en la coneccion Class Not Found ==> " + ex);
        }
    }
    
    public Connection getConnection (){
        return conn;
    }
    
    public void closeConn() {
        if(conn != null){
            try{
                conn.close();
                System.out.println("database is disconnect successfilly");
            } catch(SQLException ex){
                System.out.println("Error en la desconeccion ==> " + ex);
            }
        }
    }
}
