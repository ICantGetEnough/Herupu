package models;

import models.base.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees extends Data {
    private Integer data_id;
    private Integer speciality_id;
    private Integer salary;
    private Integer experience;

    public Employees(String name, String surname, String fin, String gender, Date birth_date) {
        super();
    }

}
