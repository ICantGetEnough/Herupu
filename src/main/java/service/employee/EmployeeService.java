package service.employee;

import models.doctor_employee.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee() throws SQLException;

    List<Employee> getEmployeeByKeyword(String keyword) throws SQLException;

    Integer getEmployeeCount() throws SQLException;

    Employee getEmployeeById(int id) throws SQLException;

    Employee getIdBySpecialityName(String name) throws SQLException;

    void insertEmployee(Employee employee) throws SQLException;

    void updateEmployee(Employee employee) throws SQLException;

    void deleteEmployee(int id) throws SQLException;

}
