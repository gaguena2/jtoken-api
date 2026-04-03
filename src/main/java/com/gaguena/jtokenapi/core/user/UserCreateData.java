package com.gaguena.jtokenapi.core.user;

public record UserCreateData(String name,
        String email,
        String password) {
    
    public UserEntity to() {
        var entity = new UserEntity();
        entity.setEmail(this.email);
        entity.setName(this.name);
        entity.setPassword(this.password);
        return entity;
    }
}
