package project.webapplication.web;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.webapplication.service.UserService;
import project.webapplication.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.error("valdation error");
			return "registration";
		}
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
