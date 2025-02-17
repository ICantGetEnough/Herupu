package repository.user;

import connection.DBConnection;
import models.user.User;
import util.UserUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //GET ALL LOGIN DATA
    @Override
    public List<User> getLoginData() throws Exception {
        List<User> users = new ArrayList<>();

        String sql = "select login_id, username, password, email, phone_number, status from hospital.login";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();

                UserUtility.userGetLoginData(rs, user);
                users.add(user);
            }
        }

        return users;
    }

    //GET LOGIN DATA BY KEYWORD
    @Override
    public List<User> getLoginDataByKeyword(String keyword) throws Exception {
        List<User> users = new ArrayList<>();

        String sql = "select * from hospital.login where lower(username) like lower(?) or " +
                "lower(email) like lower(?) or " +
                "lower(phone_number) like lower(?) or " +
                "lower(password) like lower(?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                UserUtility.userGetLoginData(rs, user);
                users.add(user);
            }
        }

        return users;
    }

    //GET LOGIN DATA BY ID
    @Override
    public User getLoginDataById(Integer id) throws Exception {
        User user = new User();

        String sql = "select * from hospital.login where id = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserUtility.userGetLoginData(rs, user);
            }
        }

        return user;
    }

    //CREATE LOGIN DATA ROW
    @Override
    public void insertLogin(User user) throws Exception {
        String sql = "insert into hospital.login(username, password, email, phone_number) values(?,?,?,?)";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone_number());
            ps.executeUpdate();
        }
    }

    //UPDATE LOGIN DATA BY ID
    @Override
    public void updateLogin(User user) throws Exception {
        String sql = "update hospital.login set username=? , password=? , email=? , phone_number=?" +
                " where login_id=?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone_number());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
        }
    }

    //DELETE LOGIN DATA BY ID
    @Override
    public void deleteLogin(Integer id) throws Exception {
        String sql = "delete from hospital.login where login_id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    //UPDATE LOGIN PASSWORD BY USERNAME
    @Override
    public void updatePassword(String username, String newPassword) throws Exception {
        String sql = "update hospital.login set password=? where username=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    //GET COUNT ALL LOGIN DATA
    @Override
    public int countLoginData() throws Exception {
        String sql = "select count(*) from hospital.login";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }

    //GET LOGIN DATA STATUS BY USERNAME
    @Override
    public User getLoginDataStatus(String username, String password) throws Exception {
        String sql = "SELECT status FROM hospital.login WHERE active = 1 AND username = ? and password = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                return new User(username, status);
            } else {
                return null;
            }
        }
    }

    @Override
    public User getByUsername(String username) throws Exception {
        User user = new User();
        String sql = "select username from hospital.login WHERE username = ?";

        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
            }
        }

        return user;
    }
}
