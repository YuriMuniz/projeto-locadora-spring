package com.ymg.locadorafilmes.service;

import java.util.List;

import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.model.UserSituation;

public interface IUserService {
    public User saveUser(User user);
	public User updateUser(User user);
	public List<User> findAll();
	public void deleteUser(User user);
    public User findByName(String name);
	public User findByEmail(String email);
	public User findById(Long id);
	public List<User> findAllBySituation(UserSituation situation);
	
}
