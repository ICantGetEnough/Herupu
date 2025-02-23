package view.room;

import main.Main;
import models.doctor_employee.Doctor;
import models.room.Room;
import service.room.RoomServiceImpl;

import java.sql.SQLOutput;
import java.util.Scanner;

public class RoomViewImpl implements RoomView {

    @Override
    public void roomView(Scanner sc) throws Exception {
        System.out.println("-- Room Admin Menu --");
        System.out.println("1.Get\n2.Insert\n3.Update\n4.Delete");
        String choiceNumber = sc.nextLine();

        RoomServiceImpl roomService = new RoomServiceImpl();
        Room room = new Room();
        Doctor doctor = new Doctor();

        switch (choiceNumber.toLowerCase().trim()) {
            case "1":
                System.out.println("1.Get All Info\n2.Get by Id\n3.Get by keyword");
                String id = sc.nextLine();

                switch (id.toLowerCase().trim()) {
                    case "1":
                        roomService.getAllRoom().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.println("Enter Room ID: ");
                        int roomId = sc.nextInt();
                        roomService.getRoomById(roomId).forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Enter Room Keyword: ");
                        String keyword = sc.nextLine();
                        roomService.getRoomByKeyword(keyword).forEach(System.out::println);
                        break;
                }
                break;
            case "2":
                System.out.println("Enter Room number: ");
                room.setRoom_number(sc.nextInt());
                sc.nextLine();
                System.out.println("Enter Room Name: ");
                room.setRoom_name(sc.nextLine());
                System.out.println("Enter Doctor Name: ");
                doctor.setName(sc.nextLine());
                System.out.println("Enter Doctor Surname: ");
                doctor.setSurname(sc.nextLine());
                room.setDoctor(doctor);
                roomService.saveRoom(room);
                break;
            case "3":
                System.out.println("Enter room id for updating: ");
                room.setId(sc.nextLong());
                System.out.println("Enter Room Number: ");
                room.setRoom_number(sc.nextInt());
                sc.nextLine();
                System.out.println("Enter Room Name: ");
                room.setRoom_name(sc.nextLine());
                System.out.println("Enter Doctor Name: ");
                doctor.setName(sc.nextLine());
                System.out.println("Enter Doctor Surname: ");
                doctor.setSurname(sc.nextLine());
                room.setDoctor(doctor);

                roomService.updateRoom(room);
                break;
            case "4":
                System.out.println("Enter the Room Number for delete: ");
                room.setRoom_number(sc.nextInt());
                sc.nextLine();
                System.out.println("Enter Room Name: ");
                room.setRoom_name(sc.nextLine());
                roomService.deleteRoom(room);
                break;
        }
    }
}
