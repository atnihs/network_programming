package Server;

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
public class EmployeeDAO {
    /*Select All By condition*/
public static Socket bienSocket=null;

    public static List<Employee> selectAllEmployeeByCondition(String condition) throws ClassNotFoundException, SQLException {
        List<Employee> employeeList = null;
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();

        StringBuilder query = new StringBuilder();
        query.append(" SELECT Employees.*,Departments.DepartmentName,");
        query.append(" Locations.LocationName,Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance,");
        query.append(" Count(Transfers.TransferID) as NumberOfTransfer");
        query.append(" FROM Employees,Departments,Locations,Projects,Roles,Transfers");
        query.append(" WHERE Departments.DepartmentID = Employees.DepartmentID ");
        query.append(" AND Locations.LocationID = Employees.LocationID");
        query.append(" AND Projects.ProjectID = Employees.ProjectID ");
        query.append(" AND Roles.RoleID = Employees.RoleID");
        query.append(" AND Transfers.EmployeeID = Employees.EmployeeID");
        if (condition.compareTo("") != 0) {
            query.append(" AND ");
            query.append(condition);
        }
        query.append(" GROUP BY Employees.EmployeeID, Employees.EmployeeName, Employees.[Password],Employees.[Username],");
        query.append(" Employees.RoleID, Employees.IsSystemAdmin, Employees.ProjectID,");
        query.append(" Employees.DepartmentID, Employees.LocationID,");
        query.append(" Departments.DepartmentName,Locations.LocationName,");
        query.append(" Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance");
        query.append(" union");
        query.append(" SELECT Employees.*,Departments.DepartmentName,");
        query.append(" Locations.LocationName,Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance,");
        query.append("  0 as NumberOfTransfer");
        query.append(" FROM Employees,Departments,Locations,Projects,Roles");
        query.append(" WHERE Departments.DepartmentID = Employees.DepartmentID ");
        query.append(" AND Locations.LocationID = Employees.LocationID");
        query.append(" AND Projects.ProjectID = Employees.ProjectID ");
        query.append(" AND Roles.RoleID = Employees.RoleID");
        query.append(" AND EmployeeID not in (Select EmployeeId from transfers)");
        if (condition.compareTo("") != 0) {
            query.append(" AND ");
            query.append(condition);
        }
        //System.out.println(query.toString());

        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        ResultSet rs = pStmt.executeQuery();

        employeeList = new ArrayList<Employee>();
        while (rs.next()) {
            Employee employee = new Employee();

            employee.setEmployeeID(rs.getInt("EmployeeID"));
            employee.setEmployeeName(rs.getNString("EmployeeName"));
            employee.setPassword(rs.getNString("Password"));
            employee.setUsername(rs.getNString("Username"));

            employee.getLocation().setLocationID(rs.getInt("LocationID"));
            employee.getLocation().setLocationName(rs.getNString("LocationName"));

            employee.getDepartment().setDepartmentID(rs.getInt("DepartmentID"));
            employee.getDepartment().setDepartmentName(rs.getNString("DepartmentName"));

            employee.getProject().setProjectID(rs.getInt("ProjectID"));
            employee.getProject().setProjectName(rs.getNString("ProjectName"));

            employee.getRole().setRoleID(rs.getInt("RoleID"));
            employee.getRole().setRoleName(rs.getNString("RoleName"));
            employee.getRole().setRoleAllowance(rs.getDouble("RoleAllowance"));

            employee.setIsSystemAdmin(rs.getBoolean("IsSystemAdmin"));
            employee.setNumberOfTransfer(rs.getInt("NumberOfTransfer"));

            //add Employee object into EmployeeList
            employeeList.add(employee);
        }

        EmpConnection.closePreparedStatement(pStmt);
        EmpConnection.closeConnection(conn);
        return employeeList;
    }

    /*Get All Employee record*/
    public static List<Employee> selectAllEmployee() throws ClassNotFoundException, SQLException {
        List<Employee> employeeList = selectAllEmployeeByCondition("");
        return employeeList;
    }

    /*Get One Employee record*/
    public static Employee selectOneEmployeeByID(int empId) throws ClassNotFoundException, SQLException {
        List<Employee> employeeList = selectAllEmployeeByCondition("Employees.EmployeeID=" + empId);
        if (employeeList.size() > 0) {
            return employeeList.get(0);
        } else {
            return null;
        }
    }

    /*Delete Employee*/
    public static void deleteEmployee(int EmployeeID) throws ClassNotFoundException, SQLException {
        Connection conn = EmpConnection.getConnection();
        PreparedStatement pStmt = conn.prepareStatement("DELETE FROM Employees WHERE EmployeeID = " + EmployeeID);
        pStmt.executeUpdate();
        EmpConnection.closePreparedStatement(pStmt);
        EmpConnection.closeConnection(conn);
    }

    /*use for Login Process*/
    public static Employee isValidUser(String userName, String password) throws ClassNotFoundException, SQLException {
        List<Employee> empLst = EmployeeDAO.selectAllEmployeeByCondition("Username = '" + userName + "' AND Password='" + password + "'");
        if (empLst.size() > 0) {
            return empLst.get(0);
        } else {
            return null;
        }
    }

    /*isAvaiableUsername*/
    public static boolean isAvaiableUsername(String username) throws ClassNotFoundException, SQLException {
        List<Employee> empLst = EmployeeDAO.selectAllEmployeeByCondition("[Username] = '" + username + "'");
        if (empLst.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**/
    public static int insertEmployee(String full,String user,String pass,int pro,int dep,int loca ) throws ClassNotFoundException, SQLException {
        int empID = 0;
        ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();
        query.append(" INSERT INTO  [dbo].[Employees]");
        query.append(" ([EmployeeName],[Username],[Password],[IsSystemAdmin],[RoleID],[ProjectID],[DepartmentID],[LocationID] )");
        query.append(" VALUES(?,?,?,?,?,?,?,?)");
        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        pStmt.setNString(1, full);
        pStmt.setNString(2, user);
        pStmt.setNString(3, pass);
        pStmt.setBoolean(4, false);
        pStmt.setInt(5, 0);
        pStmt.setInt(6, pro);
        pStmt.setInt(7, dep);
        pStmt.setInt(8, loca);
        pStmt.executeUpdate();
        pStmt = conn.prepareStatement("SELECT Max(EmployeeID)AS [MaxID] FROM Employees ");
        ResultSet rs = pStmt.executeQuery();
        rs.next();
        empID = rs.getInt("MaxID");

        conn.close();
        return empID;
    }

    /*Update Employee*/
    public static void updateEmployee(int id,String name) throws SQLException, ClassNotFoundException {
       ThreadSocket thre=new ThreadSocket(bienSocket);
       Connection conn = thre.getConnection();
        StringBuilder query = new StringBuilder();

        query.append(" UPDATE [Employees] SET");
        query.append(" [EmployeeName] = ?");
        query.append(" WHERE [EmployeeID] = ?");

        PreparedStatement pStmt = conn.prepareStatement(query.toString());
        pStmt.setNString(1, name);
        //pStmt.setNString(2, employee.getUsername());
//        pStmt.setNString(3, employee.getPassword());
//        pStmt.setBoolean(4, employee.isSystemAdmin());
//        pStmt.setInt(5, employee.getRole().getRoleID());
//        pStmt.setInt(6, employee.getProject().getProjectID());
//        pStmt.setInt(7, employee.getDepartment().getDepartmentID());
//        pStmt.setInt(8, employee.getLocation().getLocationID());
        pStmt.setInt(2,id);
        pStmt.executeUpdate();

        conn.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
    }
}
