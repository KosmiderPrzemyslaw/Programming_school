import dao.UserDao;
import models.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("Kamil", "Kamil@email.pl",
                "password");
        UserDao userDao = new UserDao();
        userDao.create(user);
    }
}
