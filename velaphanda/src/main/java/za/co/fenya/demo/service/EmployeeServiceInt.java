package za.co.fenya.demo.service;

import java.util.List;

import za.co.fenya.demo.model.Employee;

public interface EmployeeServiceInt {
	
	
	String saveEmployee(Employee employee);
	Employee getEmployeeByEmpNumber(String empUsername);
	List<Employee> getAllTechnicians();
	List<Employee> getAllTechnicians(String empUsername);
	List<Employee> getAllEmployees(Integer offset, Integer maxResults);
	List<Employee> getAllEmployees(String email);
	List<Employee> getAllEmployees();
	String updateEmployee (Employee employee);
	String changePassword(String email, String password);
	String changePassword(String email);
	String deactivateEmployee(String email);
	List<Employee> getAllManagers();
	List<Employee> reassignTechnicianList(String technicianName);

}
