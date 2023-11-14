package ahlam.java.server;

public class StudentModel {
    private int id; // Changed data type to int
    private String firstName;
    private String lastName;

    // Constructors
    public StudentModel() {}

    // Changed parameter type of id to int
    public StudentModel(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() { // Changed return type to int
        return id;
    }

    public void setId(int id) { // Changed parameter type to int
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
