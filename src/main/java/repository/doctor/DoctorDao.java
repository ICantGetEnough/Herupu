package repository.doctor;

import models.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao {
    List<Doctor> getDoctors() throws SQLException;

    List<Doctor> getDoctorByKeyword(String keyword) throws SQLException;

    Integer getDoctorCount() throws SQLException;

    Doctor getDoctorById(int id) throws SQLException;

    Doctor getIdBySpecialityName(String name) throws SQLException;

    void insertDoctor(Doctor doctor) throws SQLException;

    void updateDoctor(Doctor doctor) throws SQLException;

    void deleteDoctor(int id) throws SQLException;


}
