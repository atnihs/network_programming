package Server;

import java.net.Socket;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DoanNhatNam
 */
public class ThreadSocket extends Thread {

    Socket socket = null;
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    private String sql;
    public static String connectionIpAddress = null;

    public ThreadSocket(Socket socket) {

        System.out.println("Call to thread socket. ");
        System.out.println("Socket is connected: " + socket.isConnected());
        System.out.println("Socket address: " + socket.getInetAddress().getHostAddress());
        System.out.println("Socket port: " + socket.getPort());
        this.socket = socket;
        DepartmentDAO.bienSocket = socket;
        EmployeeDAO.bienSocket = socket;
        LocationDAO.bienSocket = socket;
        ProjectDAO.bienSocket = socket;
        RoleDAO.bienSocket = socket;
    }

    public void run() {
        try {
            // tao luong gui du lieu toi client
            String[] arr = {null};
            String st = "";
            //tao luong nhan du lieu tu client
            //BufferedReader din= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream din = new DataInputStream(socket.getInputStream());
            // tao luong gui du lieu toi client
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Xu ly du lieu. ");

            while (din.available() != 0) {
                st = din.readUTF();
                System.out.println("Client gui:" + st);
                arr = st.split("#");
                String students = null;
                String result = "";
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("addpm")) {
                        DepartmentDAO.insertDepartment(arr[0]);
                        //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("editpm")) {
                        DepartmentDAO.updateDepartment(arr[0], arr[1]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("deletepm")) {
                        DepartmentDAO.deleteDepartment(Integer.parseInt(arr[0]));
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("addloca")) {
                        LocationDAO.insertLocation(arr[0]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("deleteloca")) {
                        LocationDAO.deleteLocation(Integer.parseInt(arr[0]));
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("editloca")) {
                        LocationDAO.updateLocation(Integer.parseInt(arr[0]), arr[1]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("addpro")) {
                        ProjectDAO.insertProject(arr[0]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("editPro")) {
                        ProjectDAO.updateProject(Integer.parseInt(arr[0]), arr[1]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("delePro")) {
                        ProjectDAO.deleteProject(Integer.parseInt(arr[0]));
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }
                }
                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("editEmploy")) {
                        EmployeeDAO.updateEmployee(Integer.parseInt(arr[0]), arr[1]);
                         //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                        
                    }
                }
                if (null != arr && arr.length == 7) {
                    if (arr[6].equalsIgnoreCase("addEmploy")) {
                        EmployeeDAO.insertEmployee(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
                    //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF("OK");
                    }
                }
                if (null != arr && arr.length == 5) {
                    if (arr[4].equalsIgnoreCase("chamcong")) {
                        //EmployeeDAO.insertEmployee(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
                        String resultss = ChamCong(Integer.parseInt(arr[0]), Boolean.parseBoolean(arr[1]), Boolean.parseBoolean(arr[2]), Boolean.parseBoolean(arr[3]));
                        //System.out.print(resultss);
                        //Thread.sleep(1000);
                        dos.writeUTF(resultss);// gui du lieu xuong sever
                        dos.flush();
                    }
                }
                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("login")) {
                        //LocationDAO.updateLocation(Integer.parseInt(arr[0]),arr[1]);
                        String IsAdmin = "";

                        try {
                            // students = xemData();// gan ham xemdata cho bien sv
                            IsAdmin = Login(arr[0], arr[1]);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == IsAdmin || IsAdmin.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != IsAdmin && !IsAdmin.isEmpty())
                                && !result.equals("DBError")) {
                            result = IsAdmin;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("xemDataDepM")) {
                        try {
                            // students = xemData();// gan ham xemdata cho bien sv
                            students = XemDataDepartment();
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("xemDataProject")) {
                        try {
                            // students = xemData();// gan ham xemdata cho bien sv
                            students = XemDataProject();
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("xemDataLocatiom")) {
                        try {
                            // students = xemData();// gan ham xemdata cho bien sv
                            students = XemDataLocation();
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }
                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("xemDataEmploy")) {
                        try {
                            students = XemDataEmployee();
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }

                if (null != arr && arr.length == 5) {
                    if (arr[4].equalsIgnoreCase("searchEmploy")) {
                        try {
                            students = searchEmploy(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("searchthongke")) {
                        try {
                            students = searchEmployThongKe(arr[0]);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }

                        if ((null == students || students.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != students && !students.isEmpty())
                                && !result.equals("DBError")) {
                            result = students;
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(result);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if (null != arr && arr.length == 2) {
                    if (arr[1].equalsIgnoreCase("thongke")) {
                        try {
                            students = ThongKe(arr[0]);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối đến database: " + e);
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(students);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                 if (null != arr && arr.length == 4) {
                    if (arr[3].equalsIgnoreCase("thongkengaythang")) {
                        try {
                            students = ThongKeTheoNgayThang(arr[0],arr[1],arr[2]);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Lỗi kết nối database: " + e);
                        }
                        System.out.print(result);
                        //Thread.sleep(1000);
                        dos.writeUTF(students);// gui du lieu xuong sever
                        dos.flush();
                    }

                }
                if(null!=st && st.equalsIgnoreCase("showAll"))
                {
                    try {
                    // students = xemData();// gan ham xemdata cho bien sv
                } catch (Exception e) {
                    result = "DBError";
                    System.out.println("Lỗi kết nối database: " + e);
                }

                if ((null == students || students.isEmpty())
                        && !result.equals("DBError")) {
                    result = "OK";
                } else if ((null != students && !students.isEmpty())
                        && !result.equals("DBError")) {
                    result = students;
                }
                System.out.print(result);
                //Thread.sleep(1000);
                dos.flush();
                dos.writeUTF(result);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        if (con == null) {
            try {
                String userName = "sa";
                String password = "123456";
                String url = "jdbc:sqlserver://DESKTOP-G2GES0F\\SQLEXPRESS;databaseName=EmployeeTransferManagement;";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(url, userName, password);
                return con;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Kết nối CSDL thất bại", "Thông báo", 1);
            }
        }
        return con;
    }
    PreparedStatement pst = null;
            
    public String Login(String UserName, String Password) throws SQLException, ClassNotFoundException {
        //StringBuilder students = new StringBuilder();
        Connection con = getConnection();
        String sql = "select * from Employees where Username =? and Password=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, UserName);
            pst.setString(2, Password);

            rs = pst.executeQuery();

            String result = "";
            if (!rs.next()) {
                return result = "3";
            } else {
                boolean isAdmin = rs.getBoolean("IsSystemAdmin");
                //String DepartmentName = rs.getString("DepartmentName");
                //String NumOfEmployee = rs.getString("NumOfEmployee");
                if (isAdmin) {
                    return result = "1";
                }
                return result = "2";
            }

        } catch (Exception e) {
        }
        return null;
    }

    public String ChamCong(int mnv, boolean chinh, boolean ngay, boolean dem) {

        try {

            Connection con = getConnection();
            CallableStatement call = con.prepareCall("{call InsertData(?,?,?,?)}");
            call.setInt(1, mnv);
            call.setBoolean(2, chinh);
            call.setBoolean(3, ngay);
            call.setBoolean(4, dem);
            if (call.executeUpdate() > 0) {
                return "1";
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public String ThongKe(String Id) {

        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            sql = "select em.EmployeeName,em.Username,pay.DateCreate ,de.DepartmentName,pay.lchinh,pay.lngay,pay.ldem,pay.TotalPrice\n" +
"from Employees em,PaymentDetails pay,Locations lo,Departments de \n" +
"where em.EmployeeID=pay.EmployeeID and em.LocationID=lo.LocationID \n" +
"and em.DepartmentID=de.DepartmentID and em.EmployeeID=?";
            PreparedStatement pStmt = con.prepareStatement(sql.toString());
            pStmt.setInt(1, Integer.parseInt(Id));
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String EmployeeName = rs.getString("EmployeeName");
                String Username = rs.getString("Username");
                Date ngayda = rs.getDate("DateCreate");
                String DepartmentName = rs.getString("DepartmentName");
                Boolean chinh = rs.getBoolean("lchinh");
                Boolean ngay = rs.getBoolean("lngay");
                Boolean dem = rs.getBoolean("ldem");
                Float TongTien = rs.getFloat("TotalPrice");
                if (TongTien == null) {
                    TongTien = null;
                }
                if (EmployeeName == null) {
                    EmployeeName = "";
                }
                if (DepartmentName == null) {
                    DepartmentName = "";
                }
                if (Username == null) {
                    Username = "";
                }
                String student = Username.trim() +","+ EmployeeName.trim() + "," +ngayda.toString()+","+ DepartmentName.trim() + "," +chinh.booleanValue()+"," +ngay.booleanValue()+"," +dem.booleanValue()+","+ TongTien + ";";
                students.append(student);
            }
        } catch (Exception e) {
        }
        return students.toString();
    }
public String ThongKeTheoNgayThang(String tu,String den,String Id) {

        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            sql = "select em.EmployeeName,em.Username,pay.DateCreate ,de.DepartmentName,pay.lchinh,pay.lngay,pay.ldem,pay.TotalPrice\n" +
"from Employees em,PaymentDetails pay,Locations lo,Departments de \n" +
"where em.EmployeeID=pay.EmployeeID and em.LocationID=lo.LocationID \n" +
"and em.DepartmentID=de.DepartmentID and em.EmployeeID=?  and DateCreate>=? and DateCreate<=?";
            PreparedStatement pStmt = con.prepareStatement(sql.toString());
            pStmt.setInt(1, Integer.parseInt(Id));
            pStmt.setString(2, tu);
            pStmt.setString(3, den);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String EmployeeName = rs.getString("EmployeeName");
                String Username = rs.getString("Username");
                Date ngayda = rs.getDate("DateCreate");
                String DepartmentName = rs.getString("DepartmentName");
                Boolean chinh = rs.getBoolean("lchinh");
                Boolean ngay = rs.getBoolean("lngay");
                Boolean dem = rs.getBoolean("ldem");
                Float TongTien = rs.getFloat("TotalPrice");
                if (TongTien == null) {
                    TongTien = null;
                }
                if (EmployeeName == null) {
                    EmployeeName = "";
                }
                if (DepartmentName == null) {
                    DepartmentName = "";
                }
                if (Username == null) {
                    Username = "";
                }
                String student = Username.trim() +","+ EmployeeName.trim() + "," +ngayda.toString()+","+ DepartmentName.trim() + "," +chinh.booleanValue()+"," +ngay.booleanValue()+"," +dem.booleanValue()+","+ TongTien + ";";
                students.append(student);
            }
        } catch (Exception e) {
        }
        return students.toString();
    }
    public String XemDataLocation() throws SQLException {
        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            stm = con.createStatement();
            sql = "SELECT Locations.*,COUNT(Employees.EmployeeID) AS NumOfEmployee \n"
                    + "FROM Locations, Employees WHERE Locations.LocationID = Employees.LocationID \n"
                    + "GROUP BY Locations.LocationID, Locations.LocationName,Locations.LocationAllowance \n"
                    + "UNION \n"
                    + "SELECT Locations.*,0 AS NumOfEmployee \n"
                    + "FROM Locations,Employees \n"
                    + "WHERE Locations.LocationID not in(SELECT Employees.LocationID FROM Employees) \n"
                    + "GROUP BY Locations.LocationID, Locations.LocationName,Locations.LocationAllowance";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String LocationID = rs.getString("LocationID");
                String LocationName = rs.getString("LocationName");
                String NumOfEmployee = rs.getString("NumOfEmployee");
                if (LocationID == null) {
                    LocationID = "";
                }
                if (LocationName == null) {
                    LocationName = "";
                }
                String student = LocationID.trim() + "," + LocationName.trim() + "," + NumOfEmployee + ";";
                students.append(student);
                System.out.print(student.toString());
                System.out.print('\n');

            }
            //System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }

    public String searchEmploy(String name, int lo, int de, int pro) throws SQLException {

        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            sql = "select em.EmployeeID,em.EmployeeName,em.Username,LocationName,\n"
                    + "DepartmentName,ProjectName from Employees em ,Locations lo,Projects pro,Departments de \n"
                    + "where em.DepartmentID=de.DepartmentID and em.LocationID=lo.LocationID and em.ProjectID=pro.ProjectID\n"
                    + "and (em.EmployeeName like '%' + replace(?, '%', '[%]') + '%' or lo.LocationID=? or de.DepartmentID=? or pro.ProjectID=?)";
            PreparedStatement pStmt = con.prepareStatement(sql.toString());
            pStmt.setNString(1, name);
            pStmt.setInt(2, lo);
            pStmt.setInt(3, de);
            pStmt.setInt(4, pro);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String EmployeeID = rs.getString("EmployeeID");
                String EmployeeName = rs.getString("EmployeeName");
                String Username = rs.getString("Username");
                String LocationName = rs.getString("LocationName");
                String DepartmentName = rs.getString("DepartmentName");
                String ProjectName = rs.getString("ProjectName");
                if (EmployeeID == null) {
                    EmployeeID = "";
                }
                if (DepartmentName == null) {
                    DepartmentName = "";
                }
                if (EmployeeName == null) {
                    EmployeeName = "";
                }
                if (ProjectName == null) {
                    ProjectName = "";
                }
                if (LocationName == null) {
                    LocationName = "";
                }
                if (Username == null) {
                    Username = "";
                }
                String student = EmployeeID.trim() + "," + Username.trim() + "," + EmployeeName.trim() + "," + LocationName.trim() + "," + DepartmentName.trim() + "," + ProjectName.trim() + ";";
                students.append(student);
                System.out.print(student.toString());
                System.out.print('\n');
            }
            // System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }

    public String searchEmployThongKe(String name) throws SQLException {

        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            sql = "select EmployeeID,EmployeeName,Username from employees where EmployeeName like '%' + replace(?, '%', '[%]') + '%'";
            PreparedStatement pStmt = con.prepareStatement(sql.toString());
            pStmt.setNString(1, name);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String EmployeeID = rs.getString("EmployeeID");
                String EmployeeName = rs.getString("EmployeeName");
                String Username = rs.getString("Username");
                if (EmployeeID == null) {
                    EmployeeID = "";
                }

                if (EmployeeName == null) {
                    EmployeeName = "";
                }

                if (Username == null) {
                    Username = "";
                }
                String student = EmployeeID.trim() + "," + EmployeeName.trim() + "," + Username.trim() + ";";
                students.append(student);
            }
            // System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }

    public String XemDataEmployee() throws SQLException {
        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            stm = con.createStatement();
            sql = " SELECT Employees.EmployeeID,Employees.Username,Employees.EmployeeName,Locations.LocationName,Departments.DepartmentName,Projects.ProjectName\n" +
"  FROM Employees,Departments,Locations,Projects \n" +
" WHERE Departments.DepartmentID = Employees.DepartmentID AND Locations.LocationID = Employees.LocationID\n" +
"AND Projects.ProjectID = Employees.ProjectID";
            rs = stm.executeQuery(sql);
            // System.out.println(rs.getRow());
            while (rs.next()) {
                String EmployeeID = rs.getString("EmployeeID");
                String EmployeeName = rs.getString("EmployeeName");
                String Username = rs.getString("Username");
                String LocationName = rs.getString("LocationName");
                String DepartmentName = rs.getString("DepartmentName");
                String ProjectName = rs.getString("ProjectName");
                if (EmployeeID == null) {
                    EmployeeID = "";
                }
                if (DepartmentName == null) {
                    DepartmentName = "";
                }
                if (EmployeeName == null) {
                    EmployeeName = "";
                }
                if (ProjectName == null) {
                    ProjectName = "";
                }
                if (LocationName == null) {
                    LocationName = "";
                }
                if (Username == null) {
                    Username = "";
                }
                String student = EmployeeID.trim() + "," + Username.trim() + "," + EmployeeName.trim() + "," + LocationName.trim() + "," + DepartmentName.trim() + "," + ProjectName.trim() + ";";
                students.append(student);
                System.out.print(student.toString());
                System.out.print('\n');
            }
            // System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }

    public String XemDataDepartment() throws SQLException {
        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            stm = con.createStatement();
            sql = "SELECT dbo.Departments.DepartmentID, dbo.Departments.DepartmentName, "
                    + "COUNT(dbo.Employees.EmployeeID) AS NumOfEmployee "
                    + "FROM dbo.Departments, dbo.Employees "
                    + "WHERE dbo.Departments.DepartmentID = "
                    + "dbo.Employees.DepartmentID GROUP BY dbo.Departments.DepartmentID, "
                    + "dbo.Departments.DepartmentName UNION SELECT dbo.Departments.DepartmentID, "
                    + "dbo.Departments.DepartmentName,0 AS NumOfEmployee "
                    + "FROM dbo.Departments,dbo.Employees WHERE dbo.Departments.DepartmentID "
                    + "not in(select dbo.Employees.DepartmentID from dbo.Employees ) GROUP BY dbo.Departments.DepartmentID, "
                    + "dbo.Departments.DepartmentName";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String DepartmentID = rs.getString("DepartmentID");
                String DepartmentName = rs.getString("DepartmentName");
                String NumOfEmployee = rs.getString("NumOfEmployee");
                if (DepartmentID == null) {
                    DepartmentID = "";
                }
                if (DepartmentName == null) {
                    DepartmentName = "";
                }
                if (NumOfEmployee == null) {
                    NumOfEmployee = "";
                }

                String student = DepartmentID.trim() + "," + DepartmentName.trim() + "," + NumOfEmployee.trim() + ";";
                students.append(student);

            }
            // System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }

    public String XemDataProject() throws SQLException {
        StringBuilder students = new StringBuilder();
        try {

            Connection con = getConnection();
            stm = con.createStatement();
            sql = "SELECT dbo.Projects.ProjectID, dbo.Projects.ProjectName, "
                    + "COUNT(dbo.Employees.EmployeeID) AS NumOfEmployee "
                    + "         FROM dbo.Projects, dbo.Employees "
                    + "         WHERE dbo.Projects.ProjectID = dbo.Employees.ProjectID "
                    + "         GROUP BY dbo.Projects.ProjectID, dbo.Projects.ProjectName "
                    + "         UNION "
                    + "         SELECT dbo.Projects.ProjectID, dbo.Projects.ProjectName,0 AS NumOfEmployee "
                    + "         FROM dbo.Projects,dbo.Employees "
                    + "         WHERE dbo.Projects.ProjectID not in(select dbo.Employees.ProjectID from dbo.Employees ) "
                    + "         GROUP BY dbo.Projects.ProjectID, dbo.Projects.ProjectName";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ProjectID = rs.getString("ProjectID");
                String ProjectName = rs.getString("ProjectName");
                String NumOfEmployee = rs.getString("NumOfEmployee");
                if (ProjectID == null) {
                    ProjectID = "";
                }
                if (ProjectName == null) {
                    ProjectName = "";
                }
                if (NumOfEmployee == null) {
                    NumOfEmployee = "";
                }

                String student = ProjectID.trim() + "," + ProjectName.trim() + "," + NumOfEmployee.trim() + ";";
                students.append(student);

            }
            System.out.print(students.toString());

        } catch (Exception e) {
        }
        return students.toString();
    }
}
