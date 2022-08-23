package com.shivan.calsoft.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.shivan.calsoft.model.UserData;

@Service
public interface UserService {
	
	List<UserData> fetchAllUsers();
	
	UserData saveUser(UserData user);
	
	UserData getUserByUserId(int userId);

	void deleteUser(int userId);

}
