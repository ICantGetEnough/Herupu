package utility;

import login.model.User;

import java.sql.ResultSet;

public class LoginUtility {
    public static void userGetLoginData(ResultSet rs, User user) throws Exception {
        user.setId(rs.getLong("login_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));

        String email = rs.getString("email");

        if (email == null) {
            email = "";
        }

        user.setEmail(email);

        String phoneNumber = rs.getString("phone_number");

        if (phoneNumber == null) {
            phoneNumber = "";
        }

        user.setPhone_number(phoneNumber);
        user.setStatus(rs.getString("status"));
    }
}
