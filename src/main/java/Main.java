import dao.ExerciseDao;
import dao.GroupDao;
import dao.SolutionDao;
import dao.UserDao;
import models.Exercise;
import models.Group;
import models.Solution;
import models.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //User user = new User("Kamil", "Kamil@email.pl",
        //        "password");
        UserDao userDao = new UserDao();
        //userDao.create(user);

        //`     User user1 = new User("Maciek", "mac", "haslo");
        //     userDao.create(user1);

//        UserDao userDao = new UserDao();
//
//        User user = userDao.read(2);
//
//        user.setUserName("Marcin");
//        user.setPassword("marcin");
//        user.setEmail("email@email.pl");
//        user.setId(2);
//
//        userDao.update(user);
//
//        userDao.delete(2);

        List<User> allUsers = userDao.findAll();
        for (User u : allUsers
        ) {
            System.out.println(u);
        }

        GroupDao groupDao = new GroupDao();
//
//        Group group = new Group("bratki");
//        groupDao.create(group);
//
//        System.out.println(groupDao.read(2));

        Group group = groupDao.read(2);

        group.setName("leonki");
        groupDao.update(group);

        groupDao.delete(6);
        List<Group> groupList = groupDao.findAll();

        for (Group groupFromList: groupList
             ) {
            System.out.println(groupFromList);
        }

        Exercise exercise = new Exercise("some title", "some description");
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);



        System.out.println(exerciseDao.read(2));

        exercise.setTitile("some different titile");
        exercise.setDescription("some different desc");
        exercise.setId(2);
        exerciseDao.update(exercise);

        exerciseDao.delete(2);

        List<Exercise> all = exerciseDao.findAll();

        for (Exercise e: all
             ) {
            System.out.println(e);
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        Solution solution = new Solution();
        solution.setCreated(timestamp);
        solution.setUpdated(timestamp);
        solution.setDescription("desciption solution");
        solution.setExerciseId(3);
        solution.setUserId(3);

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);

        System.out.println(solutionDao.read(4));


        solution.setId(4);
        solution.setUserId(10);
        solution.setExerciseId(16);
        solution.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        solution.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        solution.setDescription("opis jakis tam interia");

        solutionDao.update(solution);
        solutionDao.delete(6);
        List<Solution> solutionList = solutionDao.findAll();

        System.out.println("FIND ALL: ");
        for (Solution solutionFromList: solutionList
             ) {
            System.out.println(solutionFromList);
        }

        List<Solution> allByUserId = solutionDao.findAllByUserId(10);

        System.out.println("FIND ALL BY USER ID: ");
        for (Solution user3solutions: allByUserId
             ) {
            System.out.println(user3solutions);
        }

        List<Solution> allByExerciseId = solutionDao.findAllByExerciseId(3);

        System.out.println("FIND ALL BY EXERCISE ID");
        for (Solution s:allByExerciseId
             ) {
            System.out.println(s);
        }

        List<User> userList = userDao.findAllByGroupId(2);

        for (User user: userList
             ) {
            System.out.println(user);
        }
    }
}
