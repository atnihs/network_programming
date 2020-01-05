package Server;

import Server.EmpConnection;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanhbm
 */
public class LocationDAO {
    /* selectAllLocation is a method for doing select all record form Locations table */
public static Socket bienSocket=null;
    public static List<Location> selectAllLocation() throws SQLException, ClassNotFoundException {
        /*Init Components*/
        List<Location> projectList = null;
        Connection conn = EmpConnection.getConnection();
        StringBuilder query = new StringBuilder();
        PreparedStatement pStmt = null;
        ResultSet resultSet1, resultSet2, resultSet3;

        /*Select Location ID, Location Name and Number Of Employees belong to*/
        query.append(" SELECT Locations.*,COUNT(Employees.EmployeeID) AS NumOfEmployee");
        query.append(" FROM Locations, Employees");
        query.append(" WHERE Locations.LocationID = Employees.LocationID");
        query.append(" GROUP BY Locations.LocationID, Locations.LocationName,Locations.LocationAllowance");
        query.append(" UNION");
        query.append(" SELECT Locations.*,0 AS NumOfEmployee");
        query.append(" FROM Locations,Employees");
        query.append(" WHERE Locations.LocationID not in(SELECT Employees.LocationID FROM Employees )");
        query.append(" GROUP BY Locations.LocationID, Locations.LocationName,Locations.LocationAllowance");

        pStmt = conn.prepareStatement(query.toString());
        resultSet1 = pStmt.executeQuery();//result(ProjectID,ProjectName,NumOfEmployee)

        /*Select number of TO transfer which has respective LocationID*/
        query = new StringBuilder();
        query.append(" SELECT Locations.LocationID, COUNT(Transfers.TransferID) AS ToLocationCount");
        query.append(" FROM Locations, Transfers");
        query.append(" WHERE Locations.LocationID = Transfers.ToLocationID");
        query.append(" GROUP BY Locations.LocationID");
        query.append(" UNION");
        query.append(" SELECT Locations.LocationID,0 AS ToLocationCount");
        query.append(" FROM Locations,Transfers");
        query.append(" WHERE Locations.LocationID not in(SELECT Transfers.ToLocationID FROM Transfers )");

        pStmt = conn.prepareStatement(query.toString());
        resultSet2 = pStmt.executeQuery();//result(LocationID,ToLocationCount)

        /*Select number of FROM transfer which has respective LocationID*/
        query = new StringBuilder();
        query.append(" SELECT Locations.LocationID, COUNT(Transfers.TransferID) AS FromLocationCount");
        query.append(" FROM Locations, Transfers");
        query.append(" WHERE Locations.LocationID = Transfers.FromLocationID");
        query.append(" GROUP BY Locations.LocationID");
        query.append(" UNION");
        query.append(" SELECT Locations.LocationID,0 AS FromLocationCount");
        query.append(" FROM Locations,Transfers");
        query.append(" WHERE Locations.LocationID not in(SELECT Transfers.FromLocationID FROM Transfers )");
        pStmt = conn.prepareStatement(query.toString());
        resultSet3 = pStmt.executeQuery();//result(TransferID,FromLocationCount)

        /*Push Data to BEAN OBJECTs*/
        projectList = new ArrayList<Location>();
        while (resultSet1.next()) {
            resultSet2.next();
            resultSet3.next();

            Location project = new Location();

            project.setLocationID(resultSet1.getInt("LocationID"));//
            project.setLocationName(resultSet1.getNString("LocationName"));
            project.setLocationAllowance(resultSet1.getDouble("LocationAllowance"));
            project.setNumberOfEmployees(resultSet1.getInt("NumOfEmployee"));

            project.setToTransferCount(resultSet2.getInt("ToLocationCount"));

            project.setFromTransferCount(resultSet3.getInt("FromLocationCount"));

            projectList.add(project);
        }
        EmpConnection.closePreparedStatement(pStmt);
        EmpConnection.closeConnection(conn);
        return projectList;
    }

    /*insertLocation method use to insert a new record into Locations table, it comprise Location ID and it's details. */
    public static int insertLocation(String Name) throws ClassNotFoundException, SQLException {
        int result = 0;
         ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();

        query.append(" INSERT INTO  [dbo].[Locations] ([LocationName],[LocationAllowance]) ");
        query.append(" VALUES (?,?)");
        PreparedStatement pStmt = conn.prepareStatement(query.toString());

        pStmt.setNString(1,Name);
        pStmt.setDouble(2,200);
        pStmt.executeUpdate();

        pStmt = conn.prepareStatement("SELECT MAX(LocationId) AS MaxId FROM Locations");
        ResultSet rs = pStmt.executeQuery();
        rs.next();
        result = rs.getInt("MaxId");

        conn.close();
        return result;
    }
    /* updateLocation method was created for updating each location record which was passed into by Location ID */

    public static void updateLocation(int Id, String name) throws SQLException, ClassNotFoundException {
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();

        query.append(" UPDATE  [dbo].[Locations]");
        query.append(" SET [LocationName] = ?");
        query.append(" WHERE [LocationID] = ?");

        PreparedStatement pStmt = conn.prepareStatement(query.toString());

        pStmt.setNString(1, name);
        //pStmt.setDouble(2, location.getLocationAllowance());
        pStmt.setInt(2,Id);
        pStmt.executeUpdate();

        conn.close();

    }
    /* deleteLocation medthod was created for deleting a Location following LocationID*/

    public static void deleteLocation(int LocationID) throws SQLException, ClassNotFoundException {
         ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Locations WHERE LocationID = " + LocationID);
        pStmt.executeUpdate();
        PreparedStatement pStmt1 = conn.prepareStatement("UPDATE Employees SET LocationID=0 WHERE LocationID = " + LocationID);
        pStmt1.executeUpdate();
        conn.close();
    }

    /*Creating Main Method for Testing all method were created*/
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    }
}
