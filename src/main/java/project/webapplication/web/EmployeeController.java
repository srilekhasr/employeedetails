package project.webapplication.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.webapplication.model.Employee;
import project.webapplication.service.EmployeeService;

@Controller
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	// display list of employees
	@GetMapping("/employees")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		logger.info("Creating a employee");
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {
		// save employee to database
		redirectAttribute.addFlashAttribute("message", "Employee saved successfully");
		logger.info("Saving employee");
		if (bindingResult.hasErrors()) {
			logger.error(" employee");
			return "new_employee";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employees";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model,
			RedirectAttributes redirectAttribute) {

		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		redirectAttribute.addFlashAttribute("message", "Employee Id " + id + " successfully edited");
		logger.info("edit employee");
		return "update_employee";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,
			RedirectAttributes redirectAttribute) {
		// save employee to database
		redirectAttribute.addFlashAttribute("message", "Employee edited successfully");
		logger.info("Saving employee");
		if (bindingResult.hasErrors()) {
			logger.error(" employee");
			return "new_employee";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employees";

	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id, RedirectAttributes redirectAttribute) {

		// call delete employee method
		this.employeeService.deleteEmployeeById(id);
		redirectAttribute.addFlashAttribute("message", "Employee Id " + id + " successfully deleted");
		logger.info("employee deleted");
		return "redirect:/employees";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;

		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
