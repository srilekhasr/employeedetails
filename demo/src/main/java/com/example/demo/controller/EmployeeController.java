package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // display list of employees
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        logger.info("get all employees");
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        logger.info("Creating a employee");
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttribute) {
        // save employee to database
        employeeService.saveEmployee(employee);
        redirectAttribute.addFlashAttribute("message", "Employee saved successfully");
        logger.info("Saving employee");
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable long id, Model model, RedirectAttributes redirectAttribute) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        redirectAttribute.addFlashAttribute("message", "Employee Id " + id + " successfully edited");
        logger.info("edit employee");
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
            @ModelAttribute("employee") Employee employee,
            Model model, RedirectAttributes redirectAttribute) {

        // get student from database by id
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDoj(employee.getDoj());

        // save updated student object
        employeeService.updateEmployee(existingEmployee, id);
        redirectAttribute.addFlashAttribute("message", "Employee Id " + id + " successfully edited");
        logger.info("edited employee");
        return "redirect:/employees";
    }

    // handler method to handle delete student request

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttribute) {
        employeeService.deleteEmployeeById(id);
        redirectAttribute.addFlashAttribute("message", "Employee Id " + id + " successfully deleted");
        logger.info("employee deleted");
        return "redirect:/employees";
    }

}
