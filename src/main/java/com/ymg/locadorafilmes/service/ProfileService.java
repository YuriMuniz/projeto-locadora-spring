package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.Profile;
import com.ymg.locadorafilmes.repository.IProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    @Autowired
	public IProfileRepository profileRepository;


    @Override
	public Profile saveProfile(Profile profile) {
		return profileRepository.save(profile);
	}

    @Override
	public Profile updateProfile(Profile profile) {
		return profileRepository.saveAndFlush(profile);
	}

    @Override
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}

    @Override
	public void deleteProfile(Profile profile) {
		profileRepository.delete(profile);
	}

}
