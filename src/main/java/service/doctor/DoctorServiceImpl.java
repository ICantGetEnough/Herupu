package service.doctor;

import repository.doctor.DoctorDao;
import models.doctor_employee.Doctor;
import repository.doctor.DoctorDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = new DoctorDaoImpl();
    }

    @Override
    public List<Doctor> findAllDoctors() throws SQLException {
        return doctorDao.getDoctors();
    }

    @Override
    public List<Doctor> findDoctorByKeyword(String keyword) throws SQLException {
        return doctorDao.getDoctorByKeyword(keyword);
    }

    @Override
    public Doctor findDoctorById(int id) throws SQLException {
        return doctorDao.getDoctorById(id);
    }

    @Override
    public void insertDoctor(Doctor doctor) throws SQLException {
        Doctor specialityDoctor = doctorDao.getIdBySpecialityName(doctor.getSpeciality_name());

        if (specialityDoctor != null) {
            doctor.setSpeciality_id(specialityDoctor.getSpeciality_id());
            doctorDao.insertDoctor(doctor);
        } else {
            throw new SQLException("Not found " + doctor.getSpeciality_name());
        }
    }


    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        Doctor specialityDoctor = doctorDao.getIdBySpecialityName(doctor.getSpeciality_name());

        if (specialityDoctor != null) {
            doctor.setSpeciality_id(specialityDoctor.getSpeciality_id());
            doctorDao.updateDoctor(doctor);
        } else {
            throw new SQLException("Not found " + doctor.getSpeciality_name());
        }
    }

    @Override
    public void deleteDoctor(int id) throws SQLException {
        doctorDao.deleteDoctor(id);
    }

    @Override
    public Integer getDoctorCount() throws SQLException {
        return doctorDao.getDoctorCount();
    }
}
