package service;

import admin.dao.DoctorDao;
import admin.models.Doctor;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private DoctorDao doctorDao;

    List<Doctor> get

    @Override
    public List<Doctor> findAllDoctors() {
        return List.of();
    }

    @Override
    public Doctor findDoctorById(int id) {
        return null;
    }

    @Override
    public List<Doctor> findDoctorByKeyword(String keyword) {
        return List.of();
    }
}
