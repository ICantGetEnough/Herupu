package view.user;

import repository.user.UserDao;
import repository.user.UserDaoImpl;

import models.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import util.ClientUtility;

import java.util.Scanner;

public class UserViewImpl implements UserView {
    private final User user = new User();

    @Override
    public void loginMenu(Scanner sc) throws Exception {
        UserDao userDao = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl(userDao);

        System.out.println("-- Login --");
        System.out.println("Enter your username: ");
        user.setUsername(sc.nextLine());

        System.out.println("Enter your password: ");
        user.setPassword(sc.nextLine());


        String userStatus = userService.checkUserStatus(user.getUsername(), user.getPassword());
        if ("admin".equals(userStatus)) {
            LoginAdminMenu(sc);
        } else if ("user".equals(userStatus)) {
            System.out.println("User");
        } else {
            System.out.println("\nUser not found. Would you like to sign up or change password? (change/signup)");
            String response = sc.nextLine();
            if ("signup".equalsIgnoreCase(response)) {
                signUpMenu(sc);
            } else {
                updatePasswordMenu(sc);
            }
        }
    }

    @Override
    public void signUpMenu(Scanner sc) throws Exception {
        UserDao userDao = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl(userDao);

        System.out.println("\n-- Sign Up --");
        System.out.println("Enter your new username: ");
        user.setUsername(sc.nextLine());

        System.out.println("Enter your new password: ");
        user.setPassword(sc.nextLine());

        System.out.println("Enter your email or skip: ");
        user.setEmail(sc.nextLine());

        System.out.println("Enter your phone number or skip: ");
        user.setPhone_number(sc.nextLine());

        if (userService.isUsernameUnique(user.getUsername())) {
            throw new IllegalArgumentException("Username is already in use!");
        } else {
            userService.insertLogin(user);
            System.out.println("Successfully signed up!");
            loginMenu(sc);
        }
    }

    @Override
    public void LoginAdminMenu(Scanner sc) throws Exception {
        System.out.println("-- Login Admin Menu --");
        System.out.println("1.Get\n2.Insert\n3.Update\n4.Delete\n5.Exit");
        String query = sc.nextLine();

        UserService userService = new UserServiceImpl();

        switch (query.trim().toLowerCase()) {
            case "1":
                System.out.println("1.All Info\n2.Get by keyword\n3.Get by Id\n4.Get by username\n5.Get count of rows");
                String key = sc.nextLine();

                switch (key.toLowerCase().trim()) {
                    case "1":
                        userService.getLoginData().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.print("Enter the keyword: ");
                        userService.getLoginDataByKeyword(sc.nextLine()).forEach(System.out::println);
                        break;
                    case "3":
                        System.out.print("Enter the Id: ");
                        System.out.println(userService.getLoginDataById(sc.nextInt()));
                        break;
                    case "4":
                        System.out.print("Enter the username: ");
                        System.out.println(userService.getByUsername(sc.nextLine()));
                        break;
                    case "5":
                        System.out.println("count is " + userService.countLoginData());
                        break;
                }
                break;
            //Insert
            case "2":
                System.out.println("Enter your new username: ");
                user.setUsername(sc.nextLine());

                System.out.println("Enter your new password: ");
                user.setPassword(sc.nextLine());

                System.out.println("Enter your email or skip: ");
                user.setEmail(sc.nextLine());

                System.out.println("Enter your phone number or skip: ");
                user.setPhone_number(sc.nextLine());

                if (userService.isUsernameUnique(user.getUsername())) {
                    throw new IllegalArgumentException("Username is already in use!");
                } else {
                    userService.insertLogin(user);
                    System.out.println("Successfully created!");
                }

                break;
            //Update
            case "3":
                System.out.print("Enter Id for updating: ");
                user.setId(sc.nextLong());
                System.out.println("Enter your new username: ");
                sc.nextLine();
                user.setUsername(sc.nextLine());
                System.out.println("Enter your new password: ");
                user.setPassword(sc.nextLine());
                System.out.println("Enter your email or skip: ");
                user.setEmail(sc.nextLine());
                System.out.println("Enter your phone number or skip: ");
                user.setPhone_number(sc.nextLine());

                if (userService.isUsernameUnique(user.getUsername())) {
                    throw new IllegalArgumentException("Username is already in use!");
                } else {
                    userService.updateLogin(user);
                    System.out.println("Successfully created!");
                }

                break;
            //Delete
            case "4":
                System.out.println("Enter your Id for deleting: ");
                userService.deleteLogin(sc.nextInt());
                sc.nextLine();
                System.out.println("Successfully deleted!");
                break;
            //Exit
            case "5":
                System.exit(0);
                break;
        }

        LoginAdminMenu(sc);
    }

    @Override
    public void updatePasswordMenu(Scanner sc) throws Exception {
        System.out.print("Enter your username: ");
        user.setUsername(sc.nextLine());
        int code = ClientUtility.getCode();

        System.out.println(code);
        System.out.print("Check email for enter code: ");
        int confirmCode = sc.nextInt();
        sc.nextLine();
        if (code == confirmCode) {
            UserServiceImpl userService = new UserServiceImpl();

            System.out.println("Enter your new password: ");
            user.setPassword(sc.nextLine());
            userService.updatePassword(user.getUsername(), user.getPassword());
            System.out.println("Password successfully changed!");
            loginMenu(sc);
        } else {
            throw new IllegalArgumentException("Invalid code!");
        }
    }


}
