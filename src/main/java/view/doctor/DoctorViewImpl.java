package view.doctor;

import repository.doctor.DoctorDao;
import repository.doctor.DoctorDaoImpl;
import service.doctor.DoctorServiceImpl;

import java.util.Scanner;

public class DoctorViewImpl implements DoctorView {
    @Override
    public void doctorView(Scanner sc) throws Exception {

        System.out.println("-- Doctor Admin Menu --");
        System.out.println("1.Get\n2.Insert\n3.Update\n4.Delete\n5.Exit");
        String query = sc.nextLine();

        DoctorDao doctorDao = new DoctorDaoImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);

        switch (query.trim().toLowerCase()) {
            //GET
            case "1":
                System.out.println("1.Get All Info\n2.Get by Id\n3.Get by keyword\n4.Get count");
                String id = sc.nextLine();

                switch (id.toLowerCase().trim()) {
                    //Get all info
                    case "1":
                        doctorService.findAllDoctors().forEach(System.out::println);
                        break;
                    //Get by ID
                    case "2":
                        System.out.print("Enter Id for searching doctor data: ");
                        int idSearch = sc.nextInt();

                        System.out.println(doctorService.findDoctorById(idSearch));
                        break;
                    //Get by keyword
                    case "3":
                        System.out.print("Enter keyword for searching doctor data: ");
                        String keyword = sc.nextLine();

                        System.out.println(doctorService.findDoctorByKeyword(keyword));
                        break;
                    //Get count
                    case "4":
                        System.out.println(doctorService.getDoctorCount());
                        break;
                }
                break;
            //Insert
            case "2":
                break;
            //Update
            case "3":
                break;
            //Delete
            case "4":
                break;
            //Exit
            case "5":
                System.exit(0);
                break;
        }

    }
}
