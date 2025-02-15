package main;

import connection.DBConnection;
import repository.doctor.DoctorDao;
import service.doctor.DoctorServiceImpl;
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

            DoctorDao doctorDao = new DoctorDaoImpl();

            DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);

            doctorService.findAllDoctors().forEach(System.out::println);

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
