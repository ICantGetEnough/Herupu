package repository.employee;

import connection.DBConnection;
import models.doctor_employee.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getEmployee() throws SQLException {
        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.employees e " +
                "inner join hospital.datas on e.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = e.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id";
        List<Employee> employees = new ArrayList<>();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                getData(rs, employee);
                employees.add(employee);
            }

        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) throws SQLException {
        List<Employee> employees = new ArrayList<>();

        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.employees d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id " +
                "where lower(name) like lower(?) or lower(surname) like lower(?) or lower(fin) like lower(?)" +
                " or lower(speciality_name) like lower(?) or lower(speciality_role_name) like lower(?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                getData(rs, employee);
                employees.add(employee);
            }
        }

        return employees;
    }

    @Override
    public Integer getEmployeeCount() throws SQLException {
        String sql = "select count(*) from hospital.employees";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return null;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        Employee employee = new Employee();

        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.employees d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id " +
                "where datas.data_id = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    getData(rs, employee);
                    return employee;
                }
            }

        }

        return employee;
    }

    @Override
    public Employee getIdBySpecialityName(String name) throws SQLException {
        String sql = "select speciality_id from hospital.specialities where speciality_name = ?";
        Employee employee = new Employee();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    employee.setSpeciality_id(rs.getInt("speciality_id"));

                    return employee;
                }
            }
        }

        return employee;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        String sqlData = "INSERT INTO hospital.datas (name, surname, fin, gender, birth_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        String sqlDoctor = "INSERT INTO hospital.employees (data_id, speciality_id, salary, experience) " +
                "VALUES ((SELECT MAX(data_id) FROM hospital.datas), ?, ?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement psData = c.prepareStatement(sqlData);
             PreparedStatement psDoctor = c.prepareStatement(sqlDoctor)) {

            // Вставляем данные в hospital.datas
            psData.setString(1, employee.getName());
            psData.setString(2, employee.getSurname());
            psData.setString(3, employee.getFin());
            psData.setString(4, employee.getGender());
            psData.setString(5, employee.getBirth_date().toString());
            psData.executeUpdate();

            // Вставляем данные в hospital.doctors
            psDoctor.setInt(1, employee.getSpeciality_id());
            psDoctor.setInt(2, employee.getSalary());
            psDoctor.setInt(3, employee.getExperience());
            psDoctor.executeUpdate();
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sqlData = "update hospital.datas " +
                "set name = ?, surname = ?, fin = ?, gender = ?, birth_date = ? " +
                "where data_id = ? ;";

        String sqlEmployee =
                "update hospital.employees " +
                        "set data_id = ?, speciality_id = ?, salary = ?, experience = ? " +
                        "where employee_id = ?";

        try (Connection c = DBConnection.getConnection();) {
            PreparedStatement ps = c.prepareStatement(sqlData);
            PreparedStatement ps1 = c.prepareStatement(sqlEmployee);

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getFin());
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getBirth_date().toString());
            ps.setInt(6, employee.getData_id());

            ps1.setInt(1, employee.getData_id());
            ps1.setInt(2, employee.getSpeciality_id());
            ps1.setInt(3, employee.getSalary());
            ps1.setInt(4, employee.getExperience());
            ps1.setLong(5, employee.getId());

            ps.executeUpdate();
            ps1.executeUpdate();

        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sqlEmployee = "delete from hospital.employees where employee_id = ?";
        String sqlData = "delete from hospital.datas where data_id = ?";

        try (Connection c = DBConnection.getConnection();) {
            PreparedStatement  psdEmployee = c.prepareStatement(sqlEmployee);
            psdEmployee.setInt(1, id);
            psdEmployee.executeUpdate();

            PreparedStatement psData = c.prepareStatement(sqlData);
            psData.setInt(1, id);
            psData.executeUpdate();

        }
    }

    @Override
    public Employee getEmployeeIdById(int id) throws SQLException {
        String sql = "select employee_id from hospital.employees where data_id = ?";
        Employee employee = new Employee();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    employee.setId(rs.getLong("employee_id"));

                    return employee;
                }
            }
        }

        return employee;
    }

    public static void getData(ResultSet rs, Employee employee) throws SQLException {
        employee.setId(rs.getLong("data_id"));
        employee.setName(rs.getString("name"));
        employee.setSurname(rs.getString("surname"));
        employee.setGender(rs.getString("gender"));
        employee.setFin(rs.getString("fin"));
        employee.setSpeciality_name(rs.getString("speciality_name"));
        employee.setSpeciality_role_name(rs.getString("speciality_role_name"));
        employee.setSalary(rs.getInt("salary"));
        employee.setExperience(rs.getInt("experience"));
        employee.setBirth_date(rs.getDate("birth_date"));
    }
}
