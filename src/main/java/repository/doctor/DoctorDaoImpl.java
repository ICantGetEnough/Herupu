package repository.doctor;

import connection.DBConnection;
import models.Doctor;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public List<Doctor> getDoctors() throws SQLException {
        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.doctors d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id";
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
        List<Doctor> doctors = new ArrayList<>();

        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.doctors d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id " +
                "where lower(name) like lower(?) or lower(surname) like lower(?) or lower(fin) like lower(?)" +
                " or lower(speciality_name) like lower(?) or lower(speciality_role_name) like lower(?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Doctor doctor = new Doctor();
                getDoctorData(rs, doctor);
                doctors.add(doctor);
            }
        }

        return doctors;
    }

    @Override
    public Doctor getSpecialityName(String specialityName) throws SQLException {
        String sql = "select speciality_name from hospital.specialities where speciality_name = ?";
        Doctor doctor = new Doctor();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, specialityName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor.setSpeciality_name(rs.getString("speciality_name"));
                    return doctor;
                }
            }
        }

        return doctor;
    }

    @Override
    public Integer getIdBySpecialityName(String specialityName) throws SQLException {
        String sql = "select speciality_role_id from speciality_roles where speciality_role_name = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, specialityName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("speciality_role_id");
                }
            }
        }

        return null;
    }

    @Override
    public Integer getDoctorCount() throws SQLException {
        String sql = "select count(*) from hospital.doctors";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return null;
    }

    @Override
    public Doctor getDoctorById(int id) throws SQLException {
        Doctor doctor = new Doctor();

        String sql = "select datas.data_id, name, surname, gender, fin, speciality_name," +
                "speciality_role_name, salary, experience, birth_date from hospital.doctors d " +
                "inner join hospital.datas on d.data_id = datas.data_id " +
                "inner join hospital.specialities s on s.speciality_id = d.speciality_id " +
                "inner join hospital.speciality_roles sr on s.speciality_role_id = sr.speciality_role_id " +
                "where datas.data_id = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor.setData_id(rs.getInt("datas.data_id"));
                    return doctor;
                }
            }

        }

        return doctor;
    }

    @Override
    public void insertDoctor(Doctor doctor) throws SQLException {
        String sql = "insert into datas (name, surname, fin, gender, birth_date) " +
                "values (?, ?, ?, ?, ?) " +
                "insert into doctors (data_id, speciality_id, salary, experience) " +
                "values (select data_id from hospital.datas " +
                "order by data_id desc limit 1, ?, ?, ?) ";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSurname());
            ps.setString(3, doctor.getFin());
            ps.setString(4, doctor.getGender());
            ps.setString(5, doctor.getBirth_date().toString());
            ps.setInt(6, doctor.getSpeciality_id());
            ps.setInt(7, doctor.getSalary());
            ps.setInt(8, doctor.getExperience());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sql = "update ";
    }

    @Override
    public void deleteDoctor(int id) throws SQLException {

    }

    public static void getDoctorData(ResultSet rs, Doctor doctor) throws SQLException {
        doctor.setId(rs.getLong("data_id"));
        doctor.setName(rs.getString("name"));
        doctor.setSurname(rs.getString("surname"));
        doctor.setGender(rs.getString("gender"));
        doctor.setFin(rs.getString("fin"));
        doctor.setSpeciality_name(rs.getString("speciality_name"));
        doctor.setSpeciality_role_name(rs.getString("speciality_role_name"));
        doctor.setSalary(rs.getInt("salary"));
        doctor.setExperience(rs.getInt("experience"));
        doctor.setBirth_date(rs.getDate("birth_date"));
    }
}
