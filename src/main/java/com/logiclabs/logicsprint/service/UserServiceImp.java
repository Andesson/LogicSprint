package com.logiclabs.logicsprint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logiclabs.logicsprint.model.User;
import com.logiclabs.logicsprint.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}


	@Override
	public Boolean loadUserByUserLogin(String userlogin) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(userlogin);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		return new .User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
