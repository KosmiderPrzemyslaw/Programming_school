import dao.ExerciseDao;
import dao.SolutionDao;
import dao.UserDao;
import models.Exercise;
import models.Solution;
import models.User;

import java.util.List;
import java.util.Scanner;

public class Program4 {
    public static void main(String[] args) {
        while (true) {

            String choice = chooseOperation();
            switch (choice.toLowerCase()){
                case "add":{
                    printAllUsers();
                    int userId = getUserId();

                    printAllExercise();

                    int exerciseId = getExerciseId();

                    Solution solution = new Solution();
                    solution.setUserId(userId);
                    solution.setExerciseId(exerciseId);

                    SolutionDao solutionDao = new SolutionDao();
                    solutionDao.create(solution);
                    break;
                }
                case "view":{

                    System.out.println("Type user id to check solutions");
                    Scanner scanner = new Scanner(System.in);
                    while (!scanner.hasNextInt()){
                        System.out.println("Incorrect value! Type integer");
                        scanner.next();
                    }
                    int userId = scanner.nextInt();

                    SolutionDao solutionDao = new SolutionDao();
                    List<Solution> allSolutionsByUserId = solutionDao.findAllSolutionsByUserId(userId);

                    for (Solution s: allSolutionsByUserId
                         ) {
                        System.out.println(s);
                    }

                    break;
                }
                case "quit":{
                    System.out.println("Bye!");
                    return;
                }

            }

        }


    }

    private static int getExerciseId() {
        System.out.println("Type exercise id");
        Scanner scanner1 = new Scanner(System.in);
        while (!scanner1.hasNextInt()){
            System.out.println("Incorrect value! Type once again integer!");
            scanner1.next();
        }
        return scanner1.nextInt();
    }

    private static int getUserId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type user id to whom the exercise should be assigned: ");
        while (!scanner.hasNextInt()){
            System.out.println("Incorrect value! Type once again integer!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void printAllExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exerciseList = exerciseDao.findAll();
        for (Exercise e:exerciseList
             ) {
            System.out.println(e);
        }
    }

    private static void printAllUsers() {
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();
        for (User u : userList
        ) {
            System.out.println(u);
        }
    }

    private static String chooseOperation() {
        System.out.println("SELECT OPERATION FROM MENU: ");
        System.out.println("add");
        System.out.println("view");
        System.out.println("quit");

        String choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.next();
        return choice;
    }
}
