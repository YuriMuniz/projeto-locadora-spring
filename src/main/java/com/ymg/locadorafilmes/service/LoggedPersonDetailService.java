package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.LoggedPerson;
import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.exception.SenhaInvalidaException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoggedPersonDetailService implements UserDetailsService {

    @Autowired
    private IUserService service;

   

    public UserDetails authenticate(User user){
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        boolean isEqualPass = new BCryptPasswordEncoder().matches(user.getPassword(), userDetails.getPassword());

        if(isEqualPass){
            return userDetails;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {
            user = service.findByEmail(email);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Usuário " + email + " não encontrado!");
        }
        LoggedPerson logged = new LoggedPerson(user);
        logged.setAuthenticated(true);
        return logged;
    }

}
