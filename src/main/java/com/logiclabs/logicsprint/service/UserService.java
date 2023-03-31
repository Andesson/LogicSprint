package com.logiclabs.logicsprint.service;

import com.logiclabs.logicsprint.model.User;

public interface UserService {
	public User saveUser(User user);
	public Boolean login(String user, String pass);
	public String resetPass(String user, String pass);
}
