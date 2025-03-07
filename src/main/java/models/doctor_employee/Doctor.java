package models.doctor_employee;


import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class Doctor extends Speciality {
    private Integer data_id;
    private Integer speciality_id;
    private Integer salary;
    private Integer experience;

    public Doctor() {

    }

    @Override
    public String toString() {
        return super.toString() +
                ", salary=" + salary +
                ", experience=" + experience ;
    }
}
