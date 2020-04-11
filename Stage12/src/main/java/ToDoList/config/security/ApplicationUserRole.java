package ToDoList.config.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static ToDoList.config.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    USER(Sets.newHashSet(TODO_READ, TODO_WRITE)),
    ADMIN(Sets.newHashSet(TODO_READ, TODO_WRITE, USER_READ, USER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
