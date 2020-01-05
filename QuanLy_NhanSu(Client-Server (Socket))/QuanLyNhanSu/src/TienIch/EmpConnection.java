/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TienIch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoang Dong Tien
 */
public class EmpConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
       if(con == null){
        try {
          String userName ="sa";
          String password = "123456";
           String url = "jdbc:sqlserver://DESKTOP-G2GES0F\\SQLEXPRESS;databaseName=EmployeeTransferManagement;";
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           con = DriverManager.getConnection(url,userName,password);
           return con;
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Kết nối CSDL thất bại","Thông báo",1);
        } 
            }
             return con;
    }

    public static void closeConnection(Connection oConn) throws SQLException {
        if (oConn != null) {
            oConn.close();
        }
    }

    public static boolean testConnection() {
        Connection oConn = null;
        try {
            oConn = EmpConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EmpConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (oConn != null) {
            try {
                EmpConnection.closeConnection(oConn);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(EmpConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeCallableStatement(CallableStatement callableStatement) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }
    }

    public static void main(String[] args) {

    }
}
