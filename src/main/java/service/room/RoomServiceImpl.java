package service.room;

import models.doctor_employee.Doctor;
import models.room.Room;
import repository.room.RoomDao;
import repository.room.RoomDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public RoomServiceImpl() {
        this.roomDao = new RoomDaoImpl();
    }

    @Override
    public List<Room> getAllRoom() throws SQLException {
        return roomDao.getAllRoom();
    }

    @Override
    public List<Room> getRoomByKeyword(String keyword) throws SQLException {
        return roomDao.getRoomByKeyword(keyword);
    }

    @Override
    public List<Room> getRoomById(int id) throws SQLException {
        return roomDao.getRoomById(id);
    }

    @Override
    public void saveRoom(Room room) throws SQLException {
        Doctor doctor = new Doctor();
        Room roomFromDb = roomDao.getDoctorIdByName(room.getDoctor().getName(), room.getDoctor().getSurname());

        if (roomFromDb != null) {

            doctor.setId(roomFromDb.getDoctor().getId());

            room.setDoctor(doctor);

            roomDao.saveRoom(room);
        } else {
            System.out.println(room.getDoctor().getName() + " "
                    + room.getDoctor().getSurname() + " не найден!");
        }

    }

    @Override
    public void updateRoom(Room room) throws SQLException {
        Doctor doctor = new Doctor();
        Room id = roomDao.getDoctorIdByName(room.getDoctor().getName(), room.getDoctor().getSurname());

        if (id != null) {
            doctor.setId(id.getDoctor().getId());
            room.setDoctor(doctor);

            roomDao.updateRoom(room);
        } else {
            System.out.println(room.getDoctor().getName() + " "
                    + room.getDoctor().getSurname() + " не найден!");
        }
    }

    @Override
    public void deleteRoom(Room room) throws SQLException {
        roomDao.deleteRoom(room);
    }
}