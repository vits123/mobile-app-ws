package com.appsdeveloperblog.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.UserRepository;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.shared.utilities.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	
	@Override
	public UserDto createUser(UserDto user) {
		
		
	if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists");
		
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		
		String publicUserId=utils.generateUserId(30);
		
		
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId(publicUserId);
		
		UserDto returnValue=new UserDto();
		
		UserEntity storedUserDetails= userRepository.save(userEntity);
		
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		
		return returnValue;
	}

}
