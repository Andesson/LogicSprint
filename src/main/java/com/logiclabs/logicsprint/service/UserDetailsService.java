package com.logiclabs.logicsprint.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.logiclabs.logicsprint.model.User;

public interface UserDetailsService {
	public User loadUserByUsername(String user) throws UsernameNotFoundException;
}
