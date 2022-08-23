package com.shivan.calsoft.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivan.calsoft.model.UserData;
import com.shivan.calsoft.repository.UserRepository;
import com.shivan.calsoft.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserData> fetchAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserData saveUser(UserData user) {
		return userRepository.save(user);
	}

	@Override
	public UserData getUserByUserId(int userId) {
		return userRepository.getById(userId);
	}

	@Override
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

}
