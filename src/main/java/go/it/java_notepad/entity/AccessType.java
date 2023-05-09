package go.it.java_notepad.entity;

public enum AccessType {
    PUBLIC("public"),
    PRIVATE("private");

    private String type;
    private AccessType(String type) {
        this.type = type;
    }
    public String getAccessName() {
        return type;
    }
}
