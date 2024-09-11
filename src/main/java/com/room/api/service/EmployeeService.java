package com.room.api.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.room.api.constant.RoomApiConstant;
import com.room.api.dao.IEmpRepository;
import com.room.api.exception.ExceptionHandler;
import com.room.api.exception.ResourceNotFound;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmpRepository empRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String createEmployee(Employee employee) {
		Employee save = empRepository.save(employee);
		return "saved";
	}

	@SuppressWarnings("deprecation")
	@Override
	public EmployeeDTO getEmpById(int id) {
		try {
			Employee byId = empRepository.getById(id);
			EmployeeDTO dto = new EmployeeDTO();
			if (byId != null) {
				dto.setId(byId.getId());
				dto.setName(byId.getName());
				dto.setAddress(byId.getAddress());
				dto.setCity(byId.getCity());
				dto.setZipCode(byId.getZipCode());
			} else {
				dto.setId(byId.getId());
			}
			return dto;
		}catch (Exception e) {
		ExceptionHandler build = ExceptionHandler.builder()
			.failMessage("unable to fetch data")
			.failCode("ROOM404")
			.build();
		return build;
		}
	}

	@Override
	public List<EmployeeDTO> getAllEmp() {
		List<Employee> findAll = empRepository.findAll();
		if (!CollectionUtils.isEmpty(findAll)) {
			List<EmployeeDTO> convertEntityToDTO = this.convertEntityToDTO(findAll);

			return convertEntityToDTO;
		}
		return null;

	}

	private List<EmployeeDTO> convertEntityToDTO(List<Employee> findAll) {
		ArrayList<EmployeeDTO> dtos = new ArrayList<>();
		for (Employee employee : findAll) {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setId(employee.getId());
			dto.setName(employee.getName());
			dto.setAddress(employee.getAddress());
			dto.setZipCode(employee.getZipCode());
			dto.setCity(employee.getCity());
			dtos.add(dto);
		}

		return dtos;

	}

	@Override
	public String deleteById(int id) throws ResourceNotFound {
		Employee employee = empRepository.findById(id).
				orElseThrow(() -> new ResourceNotFound("id is not found","R004", "delete not happened", "dlt"));

		if (employee != null) {
			empRepository.deleteById(employee.getId());
			return RoomApiConstant.MESSAGE_FOR_SUCCESS_DLT + id;
		} else {
			return RoomApiConstant.MESSAGE_FOR_ID_NOT_FOUND + id;
		}

	}

	@Override
	public EmployeeDTO updateEmployeeById(int id, Employee emp) {
		Employee employee = empRepository.findById(id).orElse(null);
		if(employee!= null) {
			employee.setName(emp.getName());
			employee.setCity(emp.getCity());
			Employee updatEmployee = empRepository.save(employee);
			EmployeeDTO emplDto = modelMapper.map(updatEmployee, EmployeeDTO.class);
			return emplDto;
		}
		return null;
		
	}

}
