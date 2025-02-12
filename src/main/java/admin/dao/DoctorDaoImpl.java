package admin.dao;

import admin.connection.DBConnection;
import admin.models.Doctor;
import utility.DoctorUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public List<Doctor> getDoctors() throws Exception {
        String sql = "select name, surname, gender, fin, speciality_name," +
                "speciality_role, salary, experience, birth_date from hospital.doctors d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id";
        List<Doctor> doctors = new ArrayList<>();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                DoctorUtility.getDoctorData(rs, doctor);
                doctors.add(doctor);
            }

        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorByKeyword(String keyword) throws Exception {
        return List.of();
    }

    @Override
    public Doctor getDoctorById(int id) throws Exception {
        return null;
    }

    @Override
    public void insertDoctor(Doctor doctor) throws Exception {

    }

    @Override
    public void updateDoctor(Doctor doctor) throws Exception {

    }

    @Override
    public void deleteDoctor(int id) throws Exception {

    }
}
