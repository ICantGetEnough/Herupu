package repository.room;

import connection.DBConnection;
import models.doctor_employee.Doctor;
import models.room.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> getAllRoom() throws SQLException {
        List<Room> rooms = new ArrayList<>();

        String sql = "select room_id, room_number, room_name, name, surname from hospital.rooms r " +
                "join hospital.doctors d on d.doctor_id = r.doctor_id " +
                "join hospital.datas on datas.data_id = d.data_id";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("room_id"));
                room.setRoom_number(rs.getInt("room_number"));
                room.setRoom_name(rs.getString("room_name"));

                // Создаем объект Data
                Doctor data = new Doctor();
                data.setName(rs.getString("name"));
                data.setSurname(rs.getString("surname"));

                // Устанавливаем Data в Room
                room.setDoctor(data);

                // Добавляем Room в список
                rooms.add(room);
            }
        }

        return rooms;
    }

    @Override
    public List<Room> getRoomByKeyword(String keyword) throws SQLException {
        List<Room> roomList = new ArrayList<>();

        String sql = "select room_id, room_number, room_name, name, surname from hospital.rooms r " +
                "join hospital.doctors d on d.doctor_id = r.doctor_id " +
                "join hospital.datas on datas.data_id = d.data_id " +
                "where lower(name) like lower(?) or lower(surname) like ? or lower(fin) like lower(?) ";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("room_id"));
                room.setRoom_number(rs.getInt("room_number"));
                room.setRoom_name(rs.getString("room_name"));
                Doctor data = new Doctor();
                data.setName(rs.getString("name"));
                data.setSurname(rs.getString("surname"));
                room.setDoctor(data);
                roomList.add(room);
            }
        }

        return roomList;
    }

    @Override
    public List<Room> getRoomById(int id) throws SQLException {
        List<Room> roomList = new ArrayList<>();

        String sql = "select room_id, room_number, room_name, name, surname from hospital.rooms r " +
                "join hospital.doctors d on d.doctor_id = r.doctor_id " +
                "join hospital.datas on datas.data_id = d.data_id " +
                "where room_id = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("room_id"));
                room.setRoom_number(rs.getInt("room_number"));
                room.setRoom_name(rs.getString("room_name"));
                Doctor data = new Doctor();
                data.setName(rs.getString("name"));
                data.setSurname(rs.getString("surname"));
                room.setDoctor(data);
                roomList.add(room);
            }
        }

        return roomList;
    }

    @Override
    public Integer getRoomCount() throws SQLException {
        String sql = "select count(*) from hospital.rooms";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        }
        return null;
    }

    @Override
    public void saveRoom(Room room) throws SQLException {
        String sql = "insert into hospital.rooms (room_name, room_number, doctor_id) " +
                "values (?, ?, ?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, room.getRoom_name());
            ps.setInt(2, room.getRoom_number());
            ps.setLong(3, room.getDoctor().getId());
            ps.executeUpdate();
        }
    }

    /*Что бы изменить данные кабинета
    * изменить номер и имя кабинета
    * изменить айди доктора
    * что бы изменить айди доктора - мы должны найти его имя и фамилию
    */
    @Override
    public void
    updateRoom(Room room) throws SQLException {
        String sql = "update hospital.rooms " +
                "set room_number = ? , room_name = ? , doctor_id = ? " +
                "where room_id = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, room.getRoom_number());
            ps.setString(2, room.getRoom_name());
            ps.setLong(3, room.getDoctor().getId());
            ps.setLong(4, room.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public Room getDoctorIdByName(String name, String surname) throws SQLException {
        String sql = "select doctor_id from hospital.doctors d " +
                "join hospital.datas da on d.data_id = da.data_id " +
                "where lower(name) = lower(?) and lower(surname) = lower(?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, surname);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Используем if вместо while, так как ожидаем одного доктора
                Doctor doctor = new Doctor();
                doctor.setId(rs.getLong("doctor_id"));

                Room room = new Room();
                room.setDoctor(doctor);
                return room;

            }
        }

        return null;
    }

    @Override
    public void deleteRoom(Room room) throws SQLException {
        String sql = "delete from hospital.rooms where lower(room_number) = lower(?) " +
                "and lower(room_name) = lower(?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, room.getRoom_number());
            ps.setString(2, room.getRoom_name());
            ps.executeUpdate();
        }
    }
}
