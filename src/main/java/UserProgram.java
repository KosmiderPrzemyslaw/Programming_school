import dao.ExerciseDao;
import dao.UserDao;
import models.Exercise;

import java.util.List;
import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) {
        System.out.println("Type user id: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("incorrect value!");
            scanner.next();
        }
        int userId = scanner.nextInt();

        System.out.println("Choose add or view");
        Scanner scanner1 = new Scanner(System.in);
        String next = scanner1.next();

        if (next.equals("add")) {

            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> resolvedExerciseList = exerciseDao.findAllResolved(userId);
            List<Exercise> allExercise = exerciseDao.findAll();

            for (Exercise e : resolvedExerciseList
            ) {
                for (Exercise ex : allExercise
                ) {
                    if (e == ex) {
                        allExercise.remove(ex);
                    }
                }
            }

        }
    }
}
