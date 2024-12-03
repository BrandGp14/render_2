package com.tecsup.financego.security.auth.reference;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum EPermissionReference {
    USER_ROLE(
            Set.of("user:read",
                    "course:read",
                    "module:read")
    ),
    ADMIN_ROLE(
            Set.of("user:read",
                    "user:write",
                    "course:read",
                    "course:write",
                    "module:read",
                    "module:write",
                    "theme:read",
                    "theme:write")
    );

    private final Set<String> permissions;
}
