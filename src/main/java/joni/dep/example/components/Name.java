package joni.dep.example.components;

public class Name {
    private final int userId;
    private final String name;

    public Name(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
