/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.EmpConnection;
import static Server.LocationDAO.bienSocket;
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
public class ProjectDAO {
    /*selectAllProject method select all record from Projects table*/
public static Socket bienSocket=null;
    public static List<Project> selectAllProject() throws ClassNotFoundException, SQLException {
        /*Init Components*/
        List<Project> projectList = null;
        Connection conn = EmpConnection.getConnection();
        StringBuilder query = new StringBuilder();
        PreparedStatement pStmt = null;
        ResultSet resultSet1, resultSet2, resultSet3;

        /*Select Project ID, Project Name and Number Of Employees belong to*/
        query.append(" SELECT dbo.Projects.ProjectID, dbo.Projects.ProjectName, COUNT(dbo.Employees.EmployeeID) AS NumOfEmployee");
        query.append(" FROM dbo.Projects, dbo.Employees");
        query.append(" WHERE dbo.Projects.ProjectID = dbo.Employees.ProjectID");
        query.append(" GROUP BY dbo.Projects.ProjectID, dbo.Projects.ProjectName");
        query.append(" UNION");
        query.append(" SELECT dbo.Projects.ProjectID, dbo.Projects.ProjectName,0 AS NumOfEmployee");
        query.append(" FROM dbo.Projects,dbo.Employees");
        query.append(" WHERE dbo.Projects.ProjectID not in(select dbo.Employees.ProjectID from dbo.Employees )");
        query.append(" GROUP BY dbo.Projects.ProjectID, dbo.Projects.ProjectName");

        pStmt = conn.prepareStatement(query.toString());
        resultSet1 = pStmt.executeQuery();//result(ProjectID,ProjectName,FromDepartmentCount)

        /*Select number of TO transfer which has respective ProjectID*/
        query = new StringBuilder();
        query.append(" SELECT dbo.Projects.ProjectID,COUNT(dbo.Transfers.ToProjectID) AS ToDepartmentCount");
        query.append(" FROM dbo.Projects,dbo.Transfers");
        query.append(" WHERE Transfers.ToProjectID = Projects.ProjectID");
        query.append(" GROUP BY dbo.Projects.ProjectID");
        query.append(" UNION");
        query.append(" SELECT dbo.Projects.ProjectID,0 AS ToDepartmentCount");
        query.append(" FROM dbo.Projects,dbo.Transfers");
        query.append(" WHERE Projects.ProjectID not in(select Transfers.ToProjectID from Transfers )");

        pStmt = conn.prepareStatement(query.toString());
        resultSet2 = pStmt.executeQuery();//result(ProjectID,ToDepartmentCount)

        /*Select number of FROM transfer which has respective ProjectID*/
        query = new StringBuilder();
        query.append(" SELECT dbo.Projects.ProjectID,COUNT(dbo.Transfers.ToProjectID) AS FromDepartmentCount");
        query.append(" FROM dbo.Projects,dbo.Transfers");
        query.append(" WHERE Transfers.FromProjectID = Projects.ProjectID");
        query.append(" GROUP BY dbo.Projects.ProjectID");
        query.append(" UNION");
        query.append(" SELECT dbo.Projects.ProjectID,0 AS FromDepartmentCount");
        query.append(" FROM dbo.Projects,dbo.Transfers");
        query.append(" WHERE Projects.ProjectID not in(select Transfers.FromProjectID from Transfers )");
        pStmt = conn.prepareStatement(query.toString());
        resultSet3 = pStmt.executeQuery();//result(ProjectID,FromDepartmentCount)

        /*Push Data to BEAN OBJECTs*/
        projectList = new ArrayList<Project>();
        while (resultSet1.next()) {
            resultSet2.next();
            resultSet3.next();

            Project project = new Project();

            project.setProjectID(resultSet1.getInt("ProjectID"));//
            project.setProjectName(resultSet1.getNString("ProjectName"));
            project.setNumberOfEmployees(resultSet1.getInt("NumOfEmployee"));

            project.setToTransferCount(resultSet2.getInt("ToDepartmentCount"));

            project.setFromTransferCount(resultSet3.getInt("FromDepartmentCount"));

            projectList.add(project);
        }
        EmpConnection.closePreparedStatement(pStmt);
        EmpConnection.closeConnection(conn);
        return projectList;
    }

    /*insertProject method insert a new record into Projects table*/
    public static int insertProject(String name) throws SQLException, ClassNotFoundException {
        int result = 0;
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO  [dbo].[Projects]([ProjectName])");
        query.append(" VALUES(?)");

        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        pStmt.setNString(1, name);
        pStmt.executeUpdate();

        pStmt = conn.prepareStatement("SELECT Max(ProjectID) AS MaxID FROM Projects");

        ResultSet rs = pStmt.executeQuery();
        rs.next();
        result = rs.getInt("MaxID");
        conn.close();
        return result;
    }

    /*updateProject method update a project record, folowing ProjectID*/
    public static void updateProject(int Id,String Name) throws SQLException, ClassNotFoundException {
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();

        query.append(" UPDATE  [dbo].[Projects] SET [ProjectName] = ?");
        query.append(" WHERE [ProjectID] = ?");
        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        pStmt.setNString(1, Name);
        pStmt.setInt(2,Id);
        pStmt.executeUpdate();

       conn.close();
    }

    /*deleteProject delete a project Record which be specified.*/
    public static void deleteProject(int ProjectID) throws SQLException, ClassNotFoundException {
         ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Projects WHERE ProjectID = " + ProjectID);
        pStmt.executeUpdate();
        conn.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Project> projectList = ProjectDAO.selectAllProject();


    }
}
