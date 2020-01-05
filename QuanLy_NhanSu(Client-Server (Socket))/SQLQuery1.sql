SELECT Employees.*,Departments.DepartmentName,Locations.LocationName,Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance,
Count(Transfers.TransferID) as NumberOfTransfer FROM Employees,Departments,Locations,Projects,Roles,Transfers 
WHERE Departments.DepartmentID = Employees.DepartmentID

AND Locations.LocationID = Employees.LocationID AND Projects.ProjectID = Employees.ProjectID AND Roles.RoleID = Employees.RoleID AND Transfers.EmployeeID = Employees.EmployeeID
GROUP BY Employees.EmployeeID, Employees.EmployeeName, Employees.[Password],Employees.[Username],Employees.RoleID, Employees.IsSystemAdmin, Employees.ProjectID,
Employees.DepartmentID, Employees.LocationID,Departments.DepartmentName,Locations.LocationName,Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance


SELECT Employees.*,Departments.DepartmentName,Locations.LocationName,Projects.ProjectName,Roles.RoleName,Roles.RoleAllowance,0 as NumberOfTransfer
FROM Employees,Departments,Locations,Projects,Roles WHERE Departments.DepartmentID = Employees.DepartmentID
AND Locations.LocationID = Employees.LocationID AND Projects.ProjectID = Employees.ProjectID AND Roles.RoleID = Employees.RoleID
 AND EmployeeID not in (Select EmployeeId from Transfers)

 -----------------
 SELECT Employees.EmployeeID,Employees.Username,Employees.EmployeeName,Locations.LocationName,Departments.DepartmentName,Projects.ProjectName
  FROM Employees,Departments,Locations,Projects 
 WHERE Departments.DepartmentID = Employees.DepartmentID AND Locations.LocationID = Employees.LocationID
AND Projects.ProjectID = Employees.ProjectID
--GROUP BY Employees.EmployeeID, Employees.EmployeeName, Employees.[Password],Employees.[Username],Employees.RoleID, Employees.IsSystemAdmin, Employees.ProjectID,
--Employees.DepartmentID, Employees.LocationID,Departments.DepartmentName,Locations.LocationName,Projects.ProjectName

SELECT Projects.*, Employees.* FROM Projects, Employees

select * from Employees