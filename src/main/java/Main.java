import dao.UserDao;
import models.User;

public class Main {
    public static void main(String[] args) {
    //    User user = new User("Kamil", "Kamil@email.pl",
    //            "password");
    //    UserDao userDao = new UserDao();
    //    userDao.create(user);

        UserDao userDao = new UserDao();

        User user = userDao.read(2);

        user.setUserName("Marcin");
        user.setPassword("marcin");
        user.setEmail("email@email.pl");
        user.setId(2);

        userDao.update(user);

        userDao.delete(2);
    }
}
