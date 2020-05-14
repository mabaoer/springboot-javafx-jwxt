package example.entity;

        import javafx.beans.property.SimpleStringProperty;

public class FXLogin {
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty role = new SimpleStringProperty();

    public FXLogin(String name, String role) {
        setName(name);
        setRole(role);
    }

    public String getRole() {
        return role.get();
    }
    public void setRole(String role) {
        this.role.set(role);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
}
