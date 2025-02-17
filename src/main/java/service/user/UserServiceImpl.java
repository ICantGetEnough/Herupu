package service.user;

import repository.user.UserDao;
import repository.user.UserDaoImpl;
import models.user.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> getLoginData() throws Exception {
        return userDao.getLoginData();
    }

    @Override
    public List<User> getLoginDataByKeyword(String keyword) throws Exception {
        return userDao.getLoginDataByKeyword(keyword);
    }

    @Override
    public User getLoginDataById(Integer id) throws Exception {
        return userDao.getLoginDataById(id);
    }

    @Override
    public User getLoginDataStatus(String username, String password) throws Exception {
        return userDao.getLoginDataStatus(username, password);
    }

    @Override
    public User getByUsername(String username) throws Exception {
        return userDao.getByUsername(username);
    }

    @Override
    public void insertLogin(User user) throws Exception {
        userDao.insertLogin(user);
    }

    @Override
    public void updateLogin(User user) throws Exception {
        userDao.updateLogin(user);
    }

    @Override
    public void deleteLogin(Integer id) throws Exception {
        userDao.deleteLogin(id);
    }

    @Override
    public void updatePassword(String username, String newPassword) throws Exception {
        userDao.updatePassword(username, newPassword);
    }

    @Override
    public int countLoginData() throws Exception {
        return userDao.countLoginData();
    }

    public boolean isUsernameUnique(String username) throws Exception {
        User user = userDao.getByUsername(username);

        return username.equals(user.getUsername());
    }

    @Override
    public String checkUserStatus(String username, String password) throws Exception {
        User user = userDao.getLoginDataStatus(username, password);
        if (user != null) {
            return user.getStatus();
        } else {
            return "NOT_FOUND";
        }
    }

}
