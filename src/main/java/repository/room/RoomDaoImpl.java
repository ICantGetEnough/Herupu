package repository.room;

import connection.DBConnection;
import models.doctor_employee.Doctor;
import models.room.Room;

import models.base.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public List<Room> getAllRoom() throws SQLException {
        List<Room> rooms = new ArrayList<Room>();

        String sql = "select room_number, room_name, name, surname from room r " +
                "join doctors d on d.doctor_id = r.doctor_id " +
                "join datas on datas.data_id = d.data_id";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Room room = new Room();
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

        String sql = "select room_number, room_name, name, surname from room r " +
                "join doctors d on d.doctor_id = r.doctor_id " +
                "join datas on datas.data_id = d.data_id " +
                "where lower(name) like lower(?) or lower(surname) like ? or lower(fin) like lower(?) ";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
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

        String sql = "select room_number, room_name, name, surname from room r " +
                "join doctors d on d.doctor_id = r.doctor_id " +
                "join datas on datas.data_id = d.data_id " +
                "where room_id = ?";

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

    @Override
    public void updateRoom(Room room) throws SQLException {
        String sql = "update";
    }

    @Override
    public Room getDoctorIdByName(String name, String surname) throws SQLException {
        String sql = "select doctor_id from hospital.doctors " +
                "where lower(name) = lower(?) and lower(surname) = lower(?)";
        Room room = new Room();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                room.setId(rs.getLong("doctor_id"));

                return room;
            }
        }

        return room;
    }

    @Override
    public void deleteRoom(Room room) throws SQLException {

    }
}
