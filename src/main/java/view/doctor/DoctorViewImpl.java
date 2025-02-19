package view.doctor;

import models.doctor_employee.Doctor;
import repository.doctor.DoctorDao;
import repository.doctor.DoctorDaoImpl;
import service.doctor.DoctorServiceImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DoctorViewImpl implements DoctorView {
    @Override
    public void doctorView(Scanner sc) throws Exception {

        System.out.println("-- Doctor Admin Menu --");
        System.out.println("1.Get\n2.Insert\n3.Update\n4.Delete\n5.Exit");
        String query = sc.nextLine();

        DoctorServiceImpl doctorService = new DoctorServiceImpl();

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
                Doctor doctor = new Doctor();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                System.out.println("Enter doctor name: ");
                doctor.setName(sc.nextLine());
                System.out.println("Enter doctor surname: ");
                doctor.setSurname(sc.nextLine());
                System.out.println("Enter doctor fin: ");
                doctor.setFin(sc.nextLine());
                System.out.println("Enter doctor gender: ");
                doctor.setGender(sc.nextLine());
                System.out.println("Enter doctor birth_date: ");
                String birthDate = sc.nextLine();
                java.util.Date utilDate = sdf.parse(birthDate);
                Date sqlDate = new Date(utilDate.getTime());
                doctor.setBirth_date(sqlDate);
                System.out.println("Enter doctor specialization: ");
                doctor.setSpeciality_name(sc.nextLine());
                System.out.println("Enter doctor salary: ");
                doctor.setSalary(sc.nextInt());
                System.out.println("Enter doctor experience: ");
                doctor.setExperience(sc.nextInt());

                doctorService.insertDoctor(doctor);
                break;
            //Update
            case "3":
                Doctor doctor1 = new Doctor();

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                System.out.println("Enter Id for updating doctor data: ");
                doctor1.setData_id(sc.nextInt());
                sc.nextLine();

                System.out.println("Enter doctor name: ");
                doctor1.setName(sc.nextLine());
                System.out.println("Enter doctor surname: ");
                doctor1.setSurname(sc.nextLine());
                System.out.println("Enter doctor fin: ");
                doctor1.setFin(sc.nextLine());
                System.out.println("Enter doctor gender: ");
                doctor1.setGender(sc.nextLine());
                System.out.println("Enter doctor birth_date: ");
                String birthDate1 = sc.nextLine();
                java.util.Date utilDate1 = sdf1.parse(birthDate1);
                Date sqlDate1 = new Date(utilDate1.getTime());
                doctor1.setBirth_date(sqlDate1);
                System.out.println("Enter doctor specialization: ");
                doctor1.setSpeciality_name(sc.nextLine());
                System.out.println("Enter doctor salary: ");
                doctor1.setSalary(sc.nextInt());
                System.out.println("Enter doctor experience: ");
                doctor1.setExperience(sc.nextInt());

                doctorService.updateDoctor(doctor1);
                break;
            //Delete
            case "4":
                System.out.println("Enter Id for deleting doctor data: ");
                int idDelete = sc.nextInt();
                doctorService.deleteDoctor(idDelete);
                break;
            //Exit
            case "5":
                System.exit(0);
                break;
        }
    }
}