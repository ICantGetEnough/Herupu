package models.user;

import models.base.Hospital;
import lombok.EqualsAndHashCode;

import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@lombok.Getter
public class User extends Hospital {
    private String username;
    private String password;
    private String email;
    private String phone_number;

    public User(String username, String status){
        this.username = username;
        this.status = status;
    }

    public User(){

    }

    public void setStatus(String status) throws SQLException{
        if (status.equalsIgnoreCase("user")) {
            this.status = status;
        }else if (status.equalsIgnoreCase("admin")) {
            this.status = status;
        }else {
            throw new SQLException("U can use only admin or user!");
        }
    }

    private String status;

    public void setUsername(String username) {
        if (username.isEmpty()) {
            throw new IllegalArgumentException("username is empty!");
        } else if (username.length() <= 10) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("username length should be 10");
        }
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("password is empty");
        } else if (password.length() <= 8) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("password length should be 6");
        }
    }

    @Override
    public String toString() {
        return "login_id = '" + getId() + '\'' +
                ", username = '" + username + '\'' +
                ", password = '" + password + '\'' +
                ", email = '" + email + '\'' +
                ", phone_number = '" + phone_number + "'" +
                ", status = '" + status + "'";
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            this.email = null;
        }else{
            String[] domains = new String[]{"@gmail.com", "@outlook.com", "@mail.com", "@hotmail.com", "@icloud.com"};
            boolean isTrue = false;

            for (String domain : domains) {
                if (email.contains(domain) && email.length() <=  20) {
                    isTrue = true;
                }
            }

            if (!isTrue) {
                throw new IllegalArgumentException("email length should be less than 20 and Only domain names");
            }else{
                this.email = email;
            }
        }
    }

    public void setPhone_number(String phone_number) {
        if (phone_number.isEmpty()) {
            this.phone_number = null;
        } else if (phone_number.length() <= 20 && phone_number.matches("[0-9]+")) {
            this.phone_number = phone_number;
        } else {
            throw new IllegalArgumentException("phone number length should be 20 and Only numbers!!");
        }
    }
}
