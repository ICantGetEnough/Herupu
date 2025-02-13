package repository.doctor;

import connection.DBConnection;
import models.Doctor;
import util.DoctorUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public List<Doctor> getDoctors() throws SQLException {
        String sql = "select name, surname, gender, fin, speciality_name," +
                "speciality_role, salary, experience, birth_date from hospital.doctors d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id";
        List<Doctor> doctors = new ArrayList<>();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                getDoctorData(rs, doctor);
                doctors.add(doctor);
            }

        }
        return doctors;
    }

    @Override
    public List<Doctor> getDoctorByKeyword(String keyword) throws SQLException {
        return List.of();
    }

    @Override
    public Doctor getDoctorById(int id) throws SQLException {
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

    public static void getDoctorData(ResultSet rs, Doctor doctor) throws SQLException {
        doctor.setName(rs.getString("name"));
        doctor.setSurname(rs.getString("surname"));
        doctor.setGender(rs.getString("gender"));
        doctor.setFin(rs.getString("fin"));
        doctor.setSpeciality_name(rs.getString("speciality_name"));
        doctor.setSpeciality_role(rs.getString("speciality_role"));
        doctor.setSalary(rs.getInt("salary"));
        doctor.setExperience(rs.getInt("experience"));
        doctor.setBirth_date(rs.getDate("birth_date"));
    }
}
