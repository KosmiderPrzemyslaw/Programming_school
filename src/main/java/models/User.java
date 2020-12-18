package models;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int groupId;

    public User(int id, String userName, String email, String password, int gruopId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.groupId = gruopId;
    }

    public int getGruopId() {
        return groupId;
    }

    public void setGruopId(int gruopId) {
        this.groupId = gruopId;
    }

    private void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
