package main;

import admin.connection.DBConnection;
import UI.MenuImpl;
import admin.dao.DoctorDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuImpl menu = new MenuImpl();

        Scanner scanner = new Scanner(System.in);
        Connection c = null;

        try {
            c = DBConnection.getConnection();

//            menu.loginMenu(scanner);

            DoctorDaoImpl doctorDao = new DoctorDaoImpl();

            doctorDao.getDoctors().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
