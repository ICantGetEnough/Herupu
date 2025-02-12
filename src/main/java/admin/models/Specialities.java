package admin.models;

import lombok.Data;

@Data
public class Specialities extends admin.models.abs.Data {
    private String speciality_name;
    private String speciality_role;

    @Override
    public String toString() {
        return super.toString() + ", speciality_name: " + speciality_name +
                ", speciality_role: " + speciality_role;
    }
}
