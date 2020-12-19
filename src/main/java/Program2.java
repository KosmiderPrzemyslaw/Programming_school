import dao.ExerciseDao;
import models.Exercise;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        while (true) {
            printAllExercise();
            String choice = chooseOperation();

            switch (choice.toLowerCase()) {
                case "add": {
                    addNewExercise();
                    break;
                }

                case "edit":{
                    ExerciseDao exerciseDao = new ExerciseDao();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Type id exercise to update: ");
                    while (!scanner.hasNextInt()){
                        System.out.println("Incorrect value type integer");
                        scanner.next();
                    }
                    int exerciseIdToEdit = scanner.nextInt();
                    exerciseDao.

                }

                case "quit" :{
                    System.out.println("Bye!");
                    return;
                }
            }

        }

    }

    private static void addNewExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type exercise title: ");
        String title = scanner.nextLine();

        Scanner scanner1= new Scanner(System.in);
        System.out.println("Type exercise description: ");
        String description = scanner1.nextLine();

        Exercise exercise = new Exercise(title, description);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);
    }

    private static void printAllExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        List<Exercise> exerciseList = exerciseDao.findAll();

        for (Exercise exercise : exerciseList
        ) {
            System.out.println(exercise);
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
