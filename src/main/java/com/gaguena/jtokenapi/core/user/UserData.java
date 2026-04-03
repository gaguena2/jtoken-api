package com.gaguena.jtokenapi.core.user;

public record UserData(String id, String Name, String email) {

    public static UserData UserEntityTo(UserEntity userEntity) {
        return new UserData(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

}
