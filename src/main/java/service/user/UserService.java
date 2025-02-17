package service.user;

import models.user.User;

import java.util.List;

public interface UserService {
    List<User> getLoginData() throws Exception;

    List<User> getLoginDataByKeyword(String keyword) throws Exception;

    User getLoginDataById(Integer id) throws Exception;

    User getLoginDataStatus(String username, String password) throws Exception;

    User getByUsername(String username) throws Exception;

    void insertLogin(User user) throws Exception;

    void updateLogin(User user) throws Exception;

    void deleteLogin(Integer id) throws Exception;

    void updatePassword(String username, String newPassword) throws Exception;

    int countLoginData() throws Exception;

    boolean isUsernameUnique(String username) throws Exception;

    String checkUserStatus(String username, String password) throws Exception;
}
