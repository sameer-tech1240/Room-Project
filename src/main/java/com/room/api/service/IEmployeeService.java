package com.room.api.service;

import java.util.List;

import com.room.api.exception.ResourceNotFound;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;

public interface IEmployeeService {

	String createEmployee(Employee employee);

	EmployeeDTO getEmpById(int id);

	List<EmployeeDTO> getAllEmp();
	
	String deleteById(int id) throws ResourceNotFound;

	EmployeeDTO updateEmployeeById(int id, Employee employee);

}
