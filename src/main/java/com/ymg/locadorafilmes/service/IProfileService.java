package com.ymg.locadorafilmes.service;

import com.ymg.locadorafilmes.model.Profile;
import java.util.List;
public interface IProfileService {
    public Profile saveProfile(Profile profile);
	public Profile updateProfile(Profile profile);
	public List<Profile> findAll();
	public void deleteProfile(Profile profile);
}
