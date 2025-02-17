package service.employee;

import models.Employee;
import repository.employee.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getEmployee() throws SQLException {
        return employeeDao.getEmployee();
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) throws SQLException {
        return employeeDao.getEmployeeByKeyword(keyword);
    }

    @Override
    public Integer getEmployeeCount() throws SQLException {
        return employeeDao.getEmployeeCount();
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public Employee getIdBySpecialityName(String name) throws SQLException {
        return employeeDao.getIdBySpecialityName(name);
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
      Employee employeeId = employeeDao.getIdBySpecialityName(employee.getSpeciality_name());

        if (employeeId != null) {
            employee.setSpeciality_id(employeeId.getSpeciality_id());
            employeeDao.insertEmployee(employee);
        } else {
            throw new SQLException("Not found " + employee.getSpeciality_name());
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        Employee employeeId = employeeDao.getIdBySpecialityName(employee.getSpeciality_name());

        if (employeeId != null) {
            employee.setSpeciality_id(employeeId.getSpeciality_id());
            employeeDao.updateEmployee(employee);
        } else {
            throw new SQLException("Not found " + employee.getSpeciality_name());
        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        employeeDao.deleteEmployee(id);
    }
}
