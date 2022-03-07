package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.Profile;
import com.ymg.locadorafilmes.model.Role;
import com.ymg.locadorafilmes.model.User;
import com.ymg.locadorafilmes.model.UserSituation;
import com.ymg.locadorafilmes.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserService implements IUserService {
	@Autowired
	public IUserRepository userRepository;

	@Autowired
	public IProfileService profileService;

	@Override
	public User saveUser(User user) {
		Period period = Period.between(user.getBirthDate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate(), LocalDate.now());

		if (period.getYears() < 18) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have to be of legal age - 18 years.");
		}
		user.setSituation(UserSituation.ACTIVE);

		if (user.getPassword() != null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}

		User userSaved = userRepository.saveAndFlush(user);
		Profile profile = new Profile();
		profile.setRole(Role.USER);
		profile.setUser(userSaved);
		profileService.saveProfile(profile);
		return userSaved;

	}

	@Override
	public User updateUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findById(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public List<User> findAllBySituation(UserSituation situation) {
		return userRepository.findBySituation(situation);
	}

}
