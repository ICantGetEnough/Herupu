package models;


import lombok.AllArgsConstructor;

import java.sql.Date;

@lombok.Data
@AllArgsConstructor
public class Doctor extends Specialities {
    private Integer data_id;
    private Integer speciality_id;
    private Integer salary;
    private Integer experience;

    public Doctor(String name, String surname, String fin, String gender, Date birth_date) {
        super();
    }

    public Doctor() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary +
                ", experience=" + experience;
    }
}
