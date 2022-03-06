package project.webapplication.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	@Pattern(regexp = "[a-zA-z]+")
	@NotEmpty(message = "Employee name cannot be empty.")
	@Size(min = 3, max = 50)
	private String firstName;

	@Column(name = "last_name")
	@Pattern(regexp = "[a-zA-z]+")
	@NotEmpty(message = "Employee name cannot be empty.")
	@Size(min = 5, max = 250)
	private String lastName;

	@Column(name = "email")
	@NotBlank
	@Email(message = "Please enter a valid e-mail address")
	private String email;

	@Column(name = "skills")
	@Size(min = 5, max = 250)
	@NotEmpty(message = "Employee skills cannot be empty.")
	private String skills;

	@Column(name = "doj")
	@NotNull(message = "Employee doj cannot be null.")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String doj;

	public Employee() {
	}

	public Employee(long id, String firstName, String lastName, String email, String skills, String doj) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.skills = skills;
		this.doj = doj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

}
