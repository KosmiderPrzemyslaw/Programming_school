import dao.GroupDao;
import models.Group;

import java.util.List;
import java.util.Scanner;

public class Program3 {
    public static void main(String[] args) {
        while (true) {
            printAllGroup();
            String choice = chooseOperation();

            switch (choice.toLowerCase()) {
                case "add": {
                    addNewUserGroup();
                    break;
                }
                case "edit": {
                    editGroup();
                    break;
                }

                case "delete": {
                    deleteGroup();
                }
                case "quit": {
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }

    private static void deleteGroup() {
        System.out.println("Type group id to delete");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            System.out.println("incorrect value");
            scanner.next();
        }
        int groupIdToDelete = scanner.nextInt();
        GroupDao groupDao = new GroupDao();
        groupDao.delete(groupIdToDelete);
    }

    private static void editGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type group id to edit: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect value!");
            scanner.next();
        }
        int groupIdToRemove = scanner.nextInt();

        GroupDao groupDao = new GroupDao();
        Group findByGroupId = groupDao.findByGroupId(groupIdToRemove);

        if (findByGroupId != null) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Type new group id:");
            while (!scanner1.hasNextInt()) {
                System.out.println("Incorrect value!");
                scanner1.next();
                                        }
            int idToUpdate = scanner1.nextInt();

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Type new group name");

            String nameToUpdate = scanner2.next();

            findByGroupId.setId(idToUpdate);
            findByGroupId.setName(nameToUpdate);
            groupDao.update(findByGroupId);
        } else {
            try {
                System.out.println("There is no such id in the database");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addNewUserGroup() {
        GroupDao groupDao = new GroupDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type name of user group: ");
        String userGroupName = scanner.next();
        Group group = new Group(userGroupName);
        groupDao.create(group);
    }

    private static void printAllGroup() {
        GroupDao groupDao = new GroupDao();
        List<Group> groupList = groupDao.findAll();

        for (Group gr : groupList
        ) {
            System.out.println(gr);
        }
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
}
