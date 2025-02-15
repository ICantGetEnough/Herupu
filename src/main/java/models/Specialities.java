package models;

import lombok.Data;

@Data
public class Specialities extends models.base.Data {
    private String speciality_name;
    private String speciality_role_name;
    private Integer speciality_role_id;

    @Override
    public String toString() {
        return super.toString() + ", speciality_name: " + speciality_name +
                ", speciality_role: " + speciality_role_name;
    }
}
