package service.doctor;

import models.doctor_employee.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctors() throws SQLException;

    List<Doctor> findDoctorByKeyword(String keyword) throws SQLException;

    Doctor findDoctorById(int id) throws SQLException;

    void insertDoctor(Doctor doctor) throws SQLException;

    void updateDoctor(Doctor doctor) throws SQLException;

    void deleteDoctor(int id) throws SQLException;

    Integer getDoctorCount() throws SQLException;
}
