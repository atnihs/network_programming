/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XyLyDuLieu;

/**
 *
 * @author Thanh.BM
 */
public class Department {

    private int departmentID;
    private String departmentName;
    private int numOfEmployee;
    private int toDepartmentCount;
    private int fromDepartmentCount;

    public Department() {
    }

    public Department(int departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getFromDepartmentCount() {
        return fromDepartmentCount;
    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public int getToDepartmentCount() {
        return toDepartmentCount;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setFromDepartmentCount(int fromLocationCount) {
        this.fromDepartmentCount = fromLocationCount;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public void setToDepartmentCount(int toLocationCount) {
        this.toDepartmentCount = toLocationCount;
    }
}
