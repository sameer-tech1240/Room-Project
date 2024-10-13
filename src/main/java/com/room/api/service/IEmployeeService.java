package com.room.api.service;

import com.room.api.exception.RMException;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;
import org.springframework.data.domain.Page;

public interface IEmployeeService {

    String createEmployee(Employee employee);

    EmployeeDTO getEmpById(int id) throws RMException;

    Page<EmployeeDTO> getAllEmp(int page, int pageSize, String orderBy, String order);

    String deleteById(int id) throws RMException;

    EmployeeDTO updateEmployeeById(int id, Employee employee) throws RMException;

    byte[] generatePDF(int id);

}
