package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Employee name cannot be empty.")
    @Size(min = 5, max = 250)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Employee name cannot be empty.")
    @Size(min = 5, max = 250)
    private String lastName;

    @Column(name = "skills")
    @NotEmpty(message = "Employee skills cannot be empty.")
    @Size(min = 5, max = 250)
    private String skills;

    @Column(name = "email")
    @NotEmpty(message = "Employee email cannot be empty.")
    private String email;

    @Column(name = "doj")
    @NotNull(message = "Employee doj cannot be null.")
    private String doj;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String skills, String email, String doj) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.email = email;
        this.doj = doj;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

}
