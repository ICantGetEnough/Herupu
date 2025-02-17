package repository;

public interface DataDao {

    void setId(Long id);

    void setName(String name);

    void setSurname(String surname);

    void setGender(String gender);

    void setFin(String fin);

    void setSpeciality_name(String specialityName);

    void setSpeciality_role_name(String specialityRoleName);

    void setSalary(Integer salary);

    void setExperience(Integer experience);

    void setBirth_date(java.sql.Date birthDate);


}
