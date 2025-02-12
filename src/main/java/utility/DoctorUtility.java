package utility;

import admin.models.Doctor;
import login.model.User;

import java.sql.ResultSet;

public class DoctorUtility {
    public static void getDoctorData(ResultSet rs, Doctor doctor) throws Exception {
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
