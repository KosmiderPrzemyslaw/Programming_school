import dao.SolutionDao;
import models.Solution;

import java.sql.Timestamp;
import java.util.Date;

public class AddToDb {
    public static void main(String[] args) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Solution solution = new Solution();
        solution.setCreated(timestamp);
        solution.setUpdated(timestamp);
        solution.setDescription("desciption solution");
        solution.setExerciseId(10);
        solution.setUserId(36);

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
    }
}
