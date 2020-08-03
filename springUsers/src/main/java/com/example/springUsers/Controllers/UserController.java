package com.example.springUsers.Controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springUsers.Dto.UserDto;
import com.example.springUsers.Exceptions.CustomAppException;
import com.example.springUsers.Model.Request.UserRequest;
import com.example.springUsers.Model.Response.UserResponse;
import com.example.springUsers.Services.UserServices;

//Main controller that has the routes default route is users
@RestController
@RequestMapping("users")
public class UserController {

	private UserServices userService; 
	
	public UserController(UserServices userService) {
		this.userService = userService;
	}
	//post
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		 
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto createdUser = userService.createUser(userDto); 
		UserResponse returnUser = new UserResponse(); 
		BeanUtils.copyProperties(createdUser, returnUser);
		
		return returnUser; 
		 
	}
	//get
	@GetMapping
	public List<UserResponse> getAllUsers(
			@RequestParam(value="page", defaultValue = "1") int page, 
			@RequestParam(value="limit", defaultValue="5") int limit) throws CustomAppException{
		try {
		List<UserDto> userDtoList = userService.getAllUsers(page, limit);
		List<UserResponse> userResponseList  = new ArrayList<UserResponse>(); 
		
		for ( int i = 0; i<userDtoList.size(); i++ ) {
			UserResponse userResponse = new UserResponse(); 
			BeanUtils.copyProperties(userDtoList.get(i), userResponse);
			userResponseList.add(userResponse); 
		}
	
		return userResponseList; }
		 catch(NoSuchElementException exc) {
	            throw new CustomAppException("sorry cant get current list of users");
	        }
	}
	
	//get
	@GetMapping(path="/{userId}")
	public UserResponse getUserByUserId(@PathVariable String userId) throws CustomAppException{
		try {
		UserDto singleUserDto = userService.getUserByUserId(userId); 
		UserResponse returnValue = new UserResponse(); 
		BeanUtils.copyProperties(singleUserDto, returnValue);
		return returnValue;  }
        catch(NoSuchElementException exc) {
            throw new CustomAppException("That username  doesnt exist");
        }
	}
	
	//update
	@PutMapping(path="/{userId}")
	public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);
		userDto.setUserId(userId);
		UserDto updatedUserDto = userService.updateUser(userDto); 
		
		UserResponse returnValue = new UserResponse(); 
		BeanUtils.copyProperties(updatedUserDto, returnValue);
		
		return returnValue; 
	}
	//delete
	
	@DeleteMapping(path="/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId); 
	}
	//get
	@GetMapping(path="/email/{email}")
	public UserResponse getUserByEmail(@PathVariable String email) throws CustomAppException{
		try {
		UserDto userDto = userService.getUserByEmail(email);
		UserResponse returnValue = new UserResponse(); 
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue; }
		 catch(NoSuchElementException exc) {
	            throw new CustomAppException("That email adress doesnt exist");
	        }
	}
	
}



