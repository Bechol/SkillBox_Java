package ToDoList.config.security;

public enum ApplicationUserPermission {
    TODO_READ("todo:read"),
    TODO_WRITE("todo:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
