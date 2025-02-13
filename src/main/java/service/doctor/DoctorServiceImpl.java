package service.doctor;

import repository.doctor.DoctorDao;
import models.Doctor;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> findAllDoctors() throws SQLException {
        return doctorDao.getDoctors();
    }

    @Override
    public List<Doctor> findDoctorByKeyword(String keyword) throws SQLException {
        return List.of();
    }

    @Override
    public Doctor findDoctorById(int id) throws SQLException {
        return null;
    }

    @Override
    public void insertDoctor(Doctor doctor) throws SQLException {

    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {

    }

    @Override
    public void deleteDoctor(int id) throws SQLException {

    }

    @Override
    public Integer getDoctorCount() throws SQLException {
        return 0;
    }
}
