package com.gaguena.jtokenapi.core.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserData create(UserCreateData data) {
        var userEntity = userRepository.save(data.to());
        return UserData.UserEntityTo(userEntity);
    }

    public Optional<UserData> findById(String id) {
        return userRepository.findById(id).map(UserData::UserEntityTo);
    }
}
