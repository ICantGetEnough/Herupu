package repository.doctor;

import models.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao {
    List<Doctor> getDoctors() throws SQLException;

    List<Doctor> getDoctorByKeyword(String keyword) throws SQLException;

    Doctor getSpecialityName(String specialityName) throws SQLException;

    Integer getIdBySpecialityName(String specialityName) throws SQLException;

    Integer getDoctorCount() throws SQLException;

    Doctor getDoctorById(int id) throws SQLException;

    void insertDoctor(Doctor doctor) throws SQLException;

    void updateDoctor(Doctor doctor) throws SQLException;

    void deleteDoctor(int id) throws SQLException;


}
