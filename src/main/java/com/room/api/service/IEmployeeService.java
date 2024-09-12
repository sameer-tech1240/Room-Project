package com.room.api.service;

import java.util.List;

import com.room.api.exception.RMException;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;

public interface IEmployeeService {

    String createEmployee(Employee employee);

    EmployeeDTO getEmpById(int id) throws RMException;

    List<EmployeeDTO> getAllEmp();

    String deleteById(int id) throws RMException;

    EmployeeDTO updateEmployeeById(int id, Employee employee) throws RMException;

}
