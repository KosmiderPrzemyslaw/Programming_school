import dao.GroupDao;
import dao.UserDao;
import models.Group;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        printAllUsers();
        String choice = chooseOperation();

        switch (choice.toLowerCase()) {
            case "add": {
                addUserToDb();
            }

            case "edit": {
                editUser();

            }

            case "delete": {
                deleteUserById();
            }

            case "quit": {
                System.out.println("bye!");
            }
            return;
        }
    }

    private static void deleteUserById() {
        UserDao userDao = new UserDao();
        Scanner scanner = getUserIdToRemove("Type user id to remove: ");
        int idToRemove = scanner.nextInt();
        User userById = userDao.findUserById(idToRemove);
        while (userById == null) {
            System.out.println("Incorrect user id");
            getUserIdToRemove("Type user id once again: ");

        }
        userDao.delete(idToRemove);
    }

    private static Scanner getUserIdToRemove(String s) {
        System.out.println(s);
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect value");
            scanner.next();
        }
        return scanner;
    }

    private static void editUser() {
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();

        Scanner scanner = getUserIdToRemove("Type user id: ");

        int idFromKeyboard = scanner.nextInt();
        User userById = userDao.findUserById(idFromKeyboard);

        System.out.println("Type new user email: ");
        Scanner scanner1 = new Scanner(System.in);
        String mail = scanner1.next();
        checkEmailInDb(userById, userDao, scanner1, mail);
        userDao.update(userById);

        System.out.println("Type new password: ");
        Scanner scanner2 = new Scanner(System.in);
        userById.setPassword(scanner2.next());
        userDao.update(userById);

        System.out.println("Type new username: ");
        Scanner scanner3 = new Scanner(System.in);
        userById.setUserName(scanner3.next());
        userDao.update(userById);

        System.out.println("Type new user group id: ");
        Scanner scanner4 = new Scanner(System.in);
        checkIdGroupInputFromKeyboard(userById, groupDao, scanner4);
        userDao.update(userById);
    }

    private static String chooseOperation() {
        System.out.println("Choose one of the option: ");
        System.out.println("add");
        System.out.println("edit");
        System.out.println("delete");
        System.out.println("quit");

        String choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.next();
        return choice;
    }

    private static void addUserToDb() {
        User user = new User();
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();

        System.out.println("Type email: ");
        Scanner scanner = new Scanner(System.in);
        String mail = scanner.next();

        checkEmailInDb(user, userDao, scanner, mail);

        System.out.println("Type user name: ");
        Scanner userName = new Scanner(System.in);
        user.setUserName(userName.next());

        System.out.println("Type password: ");
        Scanner password = new Scanner(System.in);
        user.setPassword(password.next());

        System.out.println("Type group ID: ");
        Scanner userGroup = new Scanner(System.in);
        checkIdGroupInputFromKeyboard(user, groupDao, userGroup);


        userDao.create(user);
    }

    private static void checkIdGroupInputFromKeyboard(User user, GroupDao groupDao, Scanner userGroup) {
        while (!userGroup.hasNextInt()) {
            System.out.println("Incorrect value!");
            userGroup.next();
        }
        int idFromKeyboard = userGroup.nextInt();

        List<Group> groupList = groupDao.findAll();
        List<Integer> integerList = new ArrayList<>();
        for (Group g : groupList
        ) {
            integerList.add(g.getId());
        }
        while (!integerList.contains(idFromKeyboard)) {
            System.out.println("There is no user group with id " + idFromKeyboard);
            System.out.println("Type once again correct value: ");
            idFromKeyboard = userGroup.nextInt();
        }
        user.setGruopId(idFromKeyboard);
    }

    private static void checkEmailInDb(User user, UserDao userDao, Scanner scanner, String mail) {
        List<User> userList = userDao.findAll();
        for (User u : userList
        ) {
            while (u.getEmail().equals(mail)) {
                System.out.println("Unfortunately this email address is already taken");
                System.out.println("Type new email address!");
                mail = scanner.next();
            }
            user.setEmail(mail);
        }
    }

    private static void printAllUsers() {
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();

        for (User user : userList
        ) {
            System.out.println(user);
        }
    }
}
