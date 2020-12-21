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

                case "edit": {
                    editExercise();
                    break;
                }

                case "delete": {
                    deleteExerciseFromDb();
                    break;
                }
                case "quit": {
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }

    private static void deleteExerciseFromDb() {
        System.out.println("Type exercise id to remove: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect value. Type integer!");
            scanner.next();
        }
        int idToRemove = scanner.nextInt();

        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exerciseByIdInDb = exerciseDao.findById(idToRemove);
        if (exerciseByIdInDb != null) {
            exerciseDao.delete(idToRemove);
        } else {
            try {
                System.out.println("There is no such id in the database");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void editExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();

        int exerciseIdToEdit = getExerciseIdToEdit();

        Exercise exerciseDaoById = exerciseDao.findById(exerciseIdToEdit);


        if (exerciseDaoById != null) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Type new exercise title: ");
            String titleToUpdate = scanner1.next();
            exerciseDaoById.setTitle(titleToUpdate);

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Type new description: ");
            String descriptionToUpdate = scanner2.next();
            exerciseDaoById.setDescription(descriptionToUpdate);
            exerciseDao.update(exerciseDaoById);

        } else {
            try {
                System.out.println("There is no such id in the database");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getExerciseIdToEdit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id exercise to update: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect value type integer");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addNewExercise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type exercise title: ");
        String title = scanner.nextLine();

        Scanner scanner1 = new Scanner(System.in);
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
