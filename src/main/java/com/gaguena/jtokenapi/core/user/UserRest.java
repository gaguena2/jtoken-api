package com.gaguena.jtokenapi.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;


    /**
     * Criar usuário
     */
    @PostMapping(path = "/")
    public ResponseEntity<UserData> create(@RequestBody UserCreateData data) {
        return ResponseEntity.ok(userService.create(data));
    }

    /**
     * Buscar usuário por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserData> findById(@PathVariable String id) {

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}