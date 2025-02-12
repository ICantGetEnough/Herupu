package service;

import admin.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctors();

    List<Doctor> findDoctorByKeyword(String keyword);

    Doctor findDoctorById(int id);
}
