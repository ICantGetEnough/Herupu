package service.room;

import models.room.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    List<Room> getAllRoom() throws SQLException;

    List<Room> getRoomByKeyword(String keyword) throws SQLException;

    List<Room> getRoomById(int id) throws SQLException;

    void saveRoom(Room room) throws SQLException;

    void updateRoom(Room room) throws SQLException;

    void deleteRoom(Room room) throws SQLException;
}
