package view;

import java.util.Scanner;

public interface Menu {
    void loginMenu(Scanner sc) throws Exception;
    void signUpMenu(Scanner sc) throws Exception;
    void AdminMenu(Scanner sc) throws Exception;
    void updatePasswordMenu(Scanner sc) throws Exception;
}
