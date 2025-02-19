package main;

import connection.DBConnection;
import repository.doctor.DoctorDao;
import service.doctor.DoctorServiceImpl;
import view.doctor.DoctorViewImpl;
import view.employee.EmployeeView;
import view.employee.EmployeeViewImpl;
import view.user.UserViewImpl;
import repository.doctor.DoctorDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserViewImpl menu = new UserViewImpl();

        Scanner scanner = new Scanner(System.in);

        Connection c = null;

        try {
            c = DBConnection.getConnection();

//            EmployeeViewImpl empl = new EmployeeViewImpl();
//
//            empl.employeeView(scanner);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
