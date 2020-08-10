package com.example.springUsers.Services;

import java.util.List;

import com.example.springUsers.Dto.UserDto;

public interface UserServices {

	List<UserDto> getAllUsers(int page, int limit);

	UserDto getUserByUserId(String userId);

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto);

	void deleteUser(String userId);

	UserDto getUserByEmail(String email);

}