package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Solution {
    private int id;
    private int userId;
    private int exerciseId;
    private Timestamp created;
    private Timestamp updated;
    private String description;


    public Solution(int userId, int exerciseId, Timestamp created, Timestamp updated, String description) {
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }


    public Timestamp getCreated() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                '}';
    }
}
