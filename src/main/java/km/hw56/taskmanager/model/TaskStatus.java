package km.hw56.taskmanager.model;

public enum TaskStatus {
    NEW, PROCESS, COMPLETED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
