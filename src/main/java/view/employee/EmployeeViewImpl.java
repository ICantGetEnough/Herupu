package view.employee;

import models.doctor_employee.Employee;
import repository.employee.EmployeeDao;
import repository.employee.EmployeeDaoImpl;
import service.employee.EmployeeServiceImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class EmployeeViewImpl implements EmployeeView {

    @Override
    public void employeeView(Scanner sc) throws Exception {

        System.out.println("-- Employee Admin Menu --");
        System.out.println("1.Get\n2.Insert\n3.Update\n4.Delete\n5.Exit");
        String query = sc.nextLine();

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeDao);

        switch (query.trim().toLowerCase()) {
            //GET
            case "1":
                System.out.println("1.Get All Info\n2.Get by Id\n3.Get by keyword\n4.Get count");
                String id = sc.nextLine();

                switch (id.toLowerCase().trim()) {
                    //Get all info
                    case "1":
                        employeeService.getEmployee().forEach(System.out::println);
                        break;
                    //Get by ID
                    case "2":
                        System.out.print("Enter Id for searching employee data: ");
                        int idSearch = sc.nextInt();

                        System.out.println(employeeService.getEmployeeById(idSearch));
                        break;
                    //Get by keyword
                    case "3":
                        System.out.print("Enter keyword for searching employee data: ");
                        String keyword = sc.nextLine();

                        System.out.println(employeeService.getEmployeeByKeyword(keyword));
                        break;
                    //Get count
                    case "4":
                        System.out.println("Employee count is " + employeeService.getEmployeeCount());
                        break;
                }
                break;
            //Insert
            case "2":
                Employee employee = new Employee();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                System.out.println("Enter employee name: ");
                employee.setName(sc.nextLine());
                System.out.println("Enter employee surname: ");
                employee.setSurname(sc.nextLine());
                System.out.println("Enter employee fin: ");
                employee.setFin(sc.nextLine());
                System.out.println("Enter employee gender: ");
                employee.setGender(sc.nextLine());
                System.out.println("Enter employee birth_date: ");
                String birthDate = sc.nextLine();
                java.util.Date utilDate = sdf.parse(birthDate);
                Date sqlDate = new Date(utilDate.getTime());
                employee.setBirth_date(sqlDate);
                System.out.println("Enter employee specialization: ");
                employee.setSpeciality_name(sc.nextLine());
                System.out.println("Enter employee salary: ");
                employee.setSalary(sc.nextInt());
                System.out.println("Enter employee experience: ");
                employee.setExperience(sc.nextInt());

                employeeService.insertEmployee(employee);
                break;
            //Update
            case "3":
                Employee employee1 = new Employee();

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                System.out.println("Enter Id for updating employee data: ");
                employee1.setData_id(sc.nextInt());
                sc.nextLine();

                System.out.println("Enter employee name: ");
                employee1.setName(sc.nextLine());
                System.out.println("Enter employee surname: ");
                employee1.setSurname(sc.nextLine());
                System.out.println("Enter employee fin: ");
                employee1.setFin(sc.nextLine());
                System.out.println("Enter employee gender: ");
                employee1.setGender(sc.nextLine());
                System.out.println("Enter employee birth_date: ");
                String birthDate1 = sc.nextLine();
                java.util.Date utilDate1 = sdf1.parse(birthDate1);
                Date sqlDate1 = new Date(utilDate1.getTime());
                employee1.setBirth_date(sqlDate1);
                System.out.println("Enter employee specialization: ");
                employee1.setSpeciality_name(sc.nextLine());
                System.out.println("Enter employee salary: ");
                employee1.setSalary(sc.nextInt());
                System.out.println("Enter employee experience: ");
                employee1.setExperience(sc.nextInt());

                employeeService.updateEmployee(employee1);
                break;
            //Delete
            case "4":
                System.out.println("Enter Id for deleting employee data: ");
                int idDelete = sc.nextInt();

                Employee employee2 = new Employee();

                employee2.setData_id(idDelete);

                employeeService.deleteEmployee(employee2);
                break;
            //Exit
            case "5":
                System.exit(0);
                break;
        }
    }
}
