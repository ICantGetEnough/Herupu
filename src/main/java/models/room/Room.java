package models.room;

import lombok.Data;
import models.base.Hospital;
import models.doctor_employee.Doctor;

@Data
public class Room extends Hospital {
    Integer room_number;
    String room_name;
    Doctor doctor;

    public void setDoctor(String name, String surname){
        this.doctor.getName() = name;
    }

    @Override
    public String toString() {
        return "room_number=" + room_number +
                ", room_name='" + room_name +
                "name and surname=" + doctor.getName() + " " + doctor.getSurname();
    }
}
