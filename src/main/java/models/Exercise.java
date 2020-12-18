package models;

public class Exercise {
    private int id;
    private String titile;
    private String description;

    public Exercise(String titile, String description) {
        this.titile = titile;
        this.description = description;
    }

    public Exercise() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", titile='" + titile + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
