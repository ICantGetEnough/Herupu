package view.user;

import java.util.Scanner;

public interface UserView {
    void loginMenu(Scanner sc) throws Exception;

    void signUpMenu(Scanner sc) throws Exception;

    void LoginAdminMenu(Scanner sc) throws Exception;

    void updatePasswordMenu(Scanner sc) throws Exception;
}
