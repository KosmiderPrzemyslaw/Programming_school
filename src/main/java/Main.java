import dao.ExerciseDao;
import dao.GroupDao;
import dao.UserDao;
import models.Exercise;
import models.Group;
import models.User;

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
    }
}
