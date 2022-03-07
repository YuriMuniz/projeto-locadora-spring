package com.ymg.locadorafilmes.controller;


import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.model.UserSituation;
import com.ymg.locadorafilmes.service.IProfileService;
import com.ymg.locadorafilmes.service.IUserService;
import com.ymg.locadorafilmes.service.LoggedPersonDetailService;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import com.ymg.locadorafilmes.controller.dto.CredentialsDTO;
import com.ymg.locadorafilmes.security.jwt.JwtService;
import com.ymg.locadorafilmes.controller.dto.TokenDTO;
import com.ymg.locadorafilmes.exception.SenhaInvalidaException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    public JwtService jwtService;

    @Autowired
    public IUserService userService;

    @Autowired
    public IProfileService profileService;

    @Autowired
    private LoggedPersonDetailService loggedPersonService;

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public @ResponseBody User createUser(@Valid @RequestBody User user) {      

        User userSaved = userService.saveUser(user);
        return userSaved;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> findAll() {
        return userService.findAllBySituation(UserSituation.ACTIVE);

    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody User findById(@PathVariable String id) {
        return userService.findById(Long.parseLong(id));

    }

    @PutMapping(value = "/users/update", consumes = "application/json", produces = "application/json")
    public User updateMovie(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PutMapping(value = "/users/delete", consumes = "application/json", produces = "application/json")
    public @ResponseBody String deteleMovie(@RequestBody User user) {

        try {
            userService.deleteUser(user);
        } catch (Error err) {
            return err.getMessage();
        }
        return "Movie has been deleted";
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody CredentialsDTO credentials) {
        try {
            User user = User.builder()
                    .email(credentials.getLogin())
                    .password(credentials.getPassword()).build();

            loggedPersonService.authenticate(user);
            String token = jwtService.tokenGenerator(user);
            return new TokenDTO(user.getEmail(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
