package km.hw56.taskmanager.model;

public enum UserRole {
    ROLE_GUEST, ROLE_USER, ROLE_ADMIN;

    @Override
    public String toString() {
        return name();
    }
}
