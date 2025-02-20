package main;

import connection.DBConnection;
import repository.doctor.DoctorDao;
import service.doctor.DoctorServiceImpl;
import view.doctor.DoctorViewImpl;
import view.employee.EmployeeView;
import view.employee.EmployeeViewImpl;
import view.room.RoomView;
import view.room.RoomViewImpl;
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

            System.out.println("1.Doctor\n2.Employee\n3.Room");
            String chooseId = scanner.nextLine();

            switch (chooseId) {
                case "1":
                    DoctorViewImpl doctorView = new DoctorViewImpl();
                    doctorView.doctorView(scanner);
                    break;
                case "2":
                    EmployeeViewImpl employeeView = new EmployeeViewImpl();
                    employeeView.employeeView(scanner);
                    break;
                case "3":
                    RoomView roomView = new RoomViewImpl();
                    roomView.roomView(scanner);
                    break;
                default:
                    args = null;
                    main(args);
            }
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
