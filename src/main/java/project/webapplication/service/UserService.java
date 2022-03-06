package project.webapplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import project.webapplication.model.User;
import project.webapplication.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}