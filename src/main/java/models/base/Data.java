package models.base;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public abstract class Data extends Hospital {
    private String name;
    private String surname;
    private String fin;
    private Gender gender;
    private Date birth_date;

    public void setFin(String fin) throws SQLException {
        if (fin.length() == 6){
            this.fin = fin;
        }else{
            throw new SQLException("FIN length only 6");
        }
    }

    public void setGender(Gender gender) throws SQLException {
        String[] genders = new String[]{"male", "female", "other"};
        boolean isTrue = false;

        for (String g : genders){
            if (g.equalsIgnoreCase(gender.toString())){
                isTrue = true;
            }
        }

        if (isTrue){
            this.gender = gender;
        }else{
            throw new SQLException("Invalid gender!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", name=" + name +
                ", surname=" + surname +
                ", fin=" + fin +
                ", gender=" + gender +
                ", birth_date=" + birth_date;
    }
}
