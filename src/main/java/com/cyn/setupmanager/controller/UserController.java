package com.cyn.setupmanager.controller;

import com.cyn.setupmanager.controller.dto.NewUserDto;
import com.cyn.setupmanager.domain.User;
import com.cyn.setupmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id")Integer id) {
        Optional<User> optU = userRepository.findById(id);
        if (optU.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optU.get();
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false, defaultValue = "10") Integer limit,
                                        @RequestParam(required = false, defaultValue = "desc") String sort) {
        if ( !sort.equalsIgnoreCase("desc") && !sort.equalsIgnoreCase("asc") ) {
            throw new IllegalArgumentException("Invalid sorting param!!!");
        }
        var sortParam = sort.equalsIgnoreCase("asc") ?
                Sort.by(Sort.Direction.ASC, "username") : Sort.by(Sort.Direction.DESC, "username");

        Page<User> users =userRepository.findAll(PageRequest.of(0, limit, sortParam));
        return users.toList();
    }

    @PostMapping(value="/register")
    public User register (@RequestBody NewUserDto newUser) {
        String hashedPw = passwordEncoder.encode(newUser.getPassword());
        Optional<User> optU = userRepository.findByUsername(newUser.getUsername());
        if (optU.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User u = new User();
        u.setName(newUser.getName());
        u.setPassword(hashedPw);
        u.setName(newUser.getName());
        u.setRole("ROLE_USER");
        u.setDescription(newUser.getDescription());
        u.setNationality(newUser.getNationality());
        u.setUsername(newUser.getUsername());

        return userRepository.saveAndFlush(u);
    }
}
