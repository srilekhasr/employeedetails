package project.webapplication.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

	@Pattern(regexp = "[a-zA-z]+")
	@NotEmpty(message = "Employee name cannot be empty.")
	@Size(min = 3, max = 50)
	private String firstName;

	@Pattern(regexp = "[a-zA-z]+")
	@NotEmpty(message = "Employee name cannot be empty.")
	@Size(min = 3, max = 50)
	private String lastName;

	@NotBlank
	@Email(message = "Please enter a valid e-mail address")
	private String email;

	@NotEmpty(message = "Employee name cannot be empty.")
	@Size(min = 3, max = 50)
	private String password;

	public UserRegistrationDto() {

	}

	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CharSequence getResetPasswordToken() {
		return null;
	}
}
