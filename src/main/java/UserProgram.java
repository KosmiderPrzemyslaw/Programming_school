import dao.ExerciseDao;
import dao.UserDao;
import models.Exercise;
import models.User;

import java.util.List;
import java.util.Scanner;

public class UserProgram {
    public static void main(String[] args) {
        System.out.println("Type user id: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            System.out.println("incorrect value!");
            scanner.next();
        }
        int userId = scanner.nextInt();

        System.out.println("Choose add or view");
        Scanner scanner1 = new Scanner(System.in);
        String next = scanner1.next();

        if(next.equals("add")){
            UserDao userDao = new UserDao();
            User user = userDao.findUserById(userId);

            ExerciseDao exerciseDao = new ExerciseDao();
            List<Exercise> exerciseList = exerciseDao.findAllNotResolved(userId);

            for (Exercise e :exerciseList
            ) {
                System.out.println(e);
            }




        }
    }
}
