package com.gaguena.jtokenapi.core.user;

import java.util.Set;

public record UserCreateData(String name,
        String email,
        String password) {
    
    public UserEntity to() {
        var entity = new UserEntity();
        entity.setEmail(this.email);
        entity.setName(this.name);
        entity.setPassword(this.password);
        entity.setRoles(Set.of(Role.USER));
        return entity;
    }
}
