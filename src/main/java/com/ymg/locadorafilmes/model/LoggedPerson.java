package com.ymg.locadorafilmes.model;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class LoggedPerson extends User implements Authentication {

    private static final long serialVersionUID = 1L;
    private boolean isAuthenticated;
    private com.ymg.locadorafilmes.model.User user;

    public LoggedPerson(com.ymg.locadorafilmes.model.User user) {

        super(user.getEmail(),
               user.getPassword(),
                true, true, true, true,
                user.getUserProfiles());

        this.user = user;
    }

    // public List<Profile> getProfiles() {
    // return usuario.getUsuarioProfiles();
    // }

   
    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        setIsAuth(true);
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        setIsAuth(isAuthenticated);
    }

    public boolean getIsAuth() {
        return isAuthenticated;
    }

    public void setIsAuth(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}
