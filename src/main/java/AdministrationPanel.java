import dao.GroupDao;
import dao.UserDao;
import models.Group;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministrationPanel {
    public static void main(String[] args) {
        printMenu();
    }

    private static void printMenu() {
        while (true) {
            printAllUsers();
            String choice = chooseOperation();

            switch (choice.toLowerCase()) {
                case "add": {
                    addUserToDb();
                    break;
                }
                case "edit": {
                    editUser();
                    break;
                }
                case "delete": {
                    deleteUserById();
                    break;
                }
                case "quit": {
                    System.out.println("bye!");
                    return;
                }

            }
        }
    }

    private static void deleteUserById() {
        UserDao userDao = new UserDao();

        int idToRemove = getUserId();
        userDao.delete(idToRemove);
    }


    private static int getUserId() {
        System.out.println("Type user ID: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect value");
            System.out.println("Type once again integer");
            scanner.next();
        }
        int userId = scanner.nextInt();

        userId = checkUserIdInDb(userId);
        return userId;
    }

    private static int checkUserIdInDb(int userId) {
        try {
            UserDao userDao = new UserDao();
            User userById = userDao.findUserById(userId);
            int idToCheck = userById.getId();
            while (idToCheck == 0) {
                System.out.println("There is no such user in the database");
                idToCheck = getUserId();
            }

        } catch (NullPointerException e) {
            System.out.println("there is no such user");
            userId = getUserId();
        }
        return userId;
    }

    private static void editUser() {
        UserDao userDao = new UserDao();


        int userIdToEdit = getUserId();
        User findUserById = userDao.findUserById(userIdToEdit);

        String updatedEmail = newUserEmail();
        //findUserById.setEmail(updatedEmail);

        System.out.println("Type new password: ");
        Scanner scanner2 = new Scanner(System.in);
        String updatedPassword = scanner2.next();
        //findUserById.setPassword(updatedPassword);


        System.out.println("Type new username: ");
        Scanner scanner3 = new Scanner(System.in);
        String updatedUsername = scanner3.next();
        //findUserById.setUserName(updatedUsername);

        System.out.println("Type new user group id: ");
        Scanner scanner4 = new Scanner(System.in);
        int updatedGroupId = checkIdGroupInputFromKeyboard(scanner4);
        //findUserById.setGruopId(updatedGroupId);


        User userToUpdate = new User(updatedUsername, updatedEmail, updatedPassword, updatedGroupId);


        userDao.update(findUserById);
    }

    private static String newUserEmail() {

        System.out.println("Type new user email: ");
        Scanner scanner1 = new Scanner(System.in);
        String mail = scanner1.next();
        String checkedEmail = checkEmailInDb(mail);

        return checkedEmail;
    }

    private static String chooseOperation() {
        System.out.println("SELECT OPERATION FROM MENU: ");
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
        UserDao userDao = new UserDao();

        System.out.println("Type email: ");
        Scanner scanner = new Scanner(System.in);
        String mail = scanner.next();
        String checkedEmail = checkEmailInDb(mail);


        System.out.println("Type user name: ");
        Scanner scanner2 = new Scanner(System.in);
        String userName = scanner2.next();


        System.out.println("Type password: ");
        Scanner scanner1 = new Scanner(System.in);
        String password = scanner1.next();


        System.out.println("Type group ID: ");
        Scanner userGroup = new Scanner(System.in);
        int checkedGroupId = checkIdGroupInputFromKeyboard(userGroup);

        User user = new User(userName, checkedEmail, password, checkedGroupId);
        userDao.create(user);
    }

    private static int checkIdGroupInputFromKeyboard(Scanner userGroup) {

        GroupDao groupDao = new GroupDao();
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
        return idFromKeyboard;
    }

    private static String checkEmailInDb(String mail) {
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();
        String mailToReturn = null;
        for (User u : userList
        ) {
            if (u.getEmail().equals(mail)) {
                System.out.println("This email address is already taken! Program will close in few sec");
                System.exit(1);
            } else
                mailToReturn = mail;
        }
        return mailToReturn;
    }

    private static void printAllUsers() {
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();
        System.out.println("--------------------ALL USERS FROM DATABASE-----------------");
        for (User user : userList
        ) {
            System.out.println(user);
        }
    }
}
