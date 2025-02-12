package admin.dao;

import admin.models.Doctor;

import java.util.List;

public interface DoctorDao {
    List<Doctor> getDoctors() throws Exception;

    List<Doctor> getDoctorByKeyword(String keyword) throws Exception;

    Doctor getDoctorById(int id) throws Exception;

    void insertDoctor(Doctor doctor) throws Exception;

    void updateDoctor(Doctor doctor) throws Exception;

    void deleteDoctor(int id) throws Exception;
}
