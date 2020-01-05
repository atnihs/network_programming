/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Thanh.BM
 */
import java.sql.Connection;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanhbm
 */
public class DepartmentDAO {
    /*selectAllDepartment method get all record from Departments Table*/
    public static Socket bienSocket=null;
    

    
    /* insertDepartment method insert a new record into Deparments table*/

    public static int insertDepartment(String name) throws ClassNotFoundException, SQLException {
        int result = 0;
       // Connection conn = EmpConnection.getConnection();
       ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        //Connection conn=new ConnectData();
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO  [dbo].[Departments]( [DepartmentName] ) VALUES(?) ");

        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        pStmt.setNString(1, name);
        pStmt.executeUpdate();

        //Get Max ID and Return
        pStmt = conn.prepareStatement("SELECT MAX(DepartmentID) AS MaxID FROM Departments");
        ResultSet rs = pStmt.executeQuery();
        rs.next();
        result = rs.getInt("MaxID");

       /* EmpConnection.closePreparedStatement(pStmt);
        EmpConnection.closeConnection(conn);*/
       conn.close();
        return result;
    }

    /*Update Department method was created for Update a record from Department, folowing DepartmentID*/
    public static void updateDepartment(String Id,String Name) throws SQLException, ClassNotFoundException {
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("UPDATE [dbo].[Departments] SET [DepartmentName] = ?");
        query.append(" WHERE [DepartmentID] = ?");

        PreparedStatement pStmt = conn.prepareStatement(query.toString());

        pStmt.setNString(1,Name);
        pStmt.setInt(2,Integer.parseInt(Id));

        pStmt.executeUpdate();

        conn.close();

    }

    /*deleteDepartment delete a Department*/
    public static void deleteDepartment(int DepartmentID) throws SQLException, ClassNotFoundException {
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Departments WHERE DepartmentID = " + DepartmentID);
        pStmt.executeUpdate();
        PreparedStatement pStmt1 = conn.prepareStatement("update FROM Transfers set FromDepartmentID=0 WHERE FromDepartmentID = " + DepartmentID);
       conn.close();
    }

}

