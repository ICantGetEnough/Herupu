package repository.doctor;

import connection.DBConnection;
import models.doctor_employee.Doctor;

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

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                getDoctorData(rs, doctor);
                doctors.add(doctor);
            }
        }

        return doctors;
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
                    getDoctorData(rs, doctor);
                    return doctor;
                }
            }

        }

        return doctor;
    }

    @Override
    public Doctor getIdBySpecialityName(String name) throws SQLException {
        String sql = "select speciality_id from hospital.specialities where speciality_name = ?";
        Doctor doctor = new Doctor();

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor.setSpeciality_id(rs.getInt("speciality_id"));

                    return doctor;
                }
            }
        }

        return doctor;
    }

    @Override
    public void insertDoctor(Doctor doctor) throws SQLException {
        String sqlData = "INSERT INTO hospital.datas (name, surname, fin, gender, birth_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        String sqlDoctor = "INSERT INTO hospital.doctors (data_id, speciality_id, salary, experience) " +
                "VALUES ((SELECT MAX(data_id) FROM hospital.datas), ?, ?, ?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement psData = c.prepareStatement(sqlData);
             PreparedStatement psDoctor = c.prepareStatement(sqlDoctor)) {

            // Вставляем данные в hospital.datas
            psData.setString(1, doctor.getName());
            psData.setString(2, doctor.getSurname());
            psData.setString(3, doctor.getFin());
            psData.setString(4, doctor.getGender());
            psData.setString(5, doctor.getBirth_date().toString());
            psData.executeUpdate();

            // Вставляем данные в hospital.doctors
            psDoctor.setInt(1, doctor.getSpeciality_id());
            psDoctor.setInt(2, doctor.getSalary());
            psDoctor.setInt(3, doctor.getExperience());
            psDoctor.executeUpdate();
        }
    }


    @Override
    public void updateDoctor(Doctor doctor) throws SQLException {
        String sqlData = "update hospital.datas " +
                "set name = ?, surname = ?, fin = ?, gender = ?, birth_date = ? " +
                "where data_id = ? ;";

        String sqlDoctor =
                "update hospital.doctors " +
                        "set data_id = ?, speciality_id = ?, salary = ?, experience = ? " +
                        "where doctor_id = ?";

        try (Connection c = DBConnection.getConnection();) {
            PreparedStatement ps = c.prepareStatement(sqlData);
            PreparedStatement ps1 = c.prepareStatement(sqlDoctor);

            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getSurname());
            ps.setString(3, doctor.getFin());
            ps.setString(4, doctor.getGender());
            ps.setString(5, doctor.getBirth_date().toString());
            ps.setInt(6, doctor.getData_id());

            ps1.setInt(1, doctor.getData_id());
            ps1.setInt(2, doctor.getSpeciality_id());
            ps1.setInt(3, doctor.getSalary());
            ps1.setInt(4, doctor.getExperience());
            doctor.setId(Long.valueOf(doctor.getData_id()));
            ps1.setLong(5, doctor.getId());

            ps.executeUpdate();
            ps1.executeUpdate();

        }
    }

    @Override
    public void deleteDoctor(int id) throws SQLException {
        String sqlData = "delete from hospital.datas where data_id = ?";
        String sqlDoctor = "delete from hospital.doctors where doctor_id = ?";

        try (Connection c = DBConnection.getConnection();) {
            PreparedStatement  psdDoctor = c.prepareStatement(sqlDoctor);
            psdDoctor.setInt(1, id);
            psdDoctor.executeUpdate();

            PreparedStatement psData = c.prepareStatement(sqlData);
            psData.setInt(1, id);
            psData.executeUpdate();

        }
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
