package com.room.api.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.room.api.constant.RoomApiConstant;
import com.room.api.dao.IEmpRepository;
import com.room.api.exception.RMException;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmpRepository empRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String createEmployee(Employee employee) {
        log.info("employee request :{}", employee);
        Employee save = empRepository.save(employee);
        return "saved";

    }

    @Override
    public EmployeeDTO getEmpById(int id) throws RMException {
        Optional<Employee> employee = empRepository.findById(id);
        log.info("Employee db response:{}", employee.toString());
        if (employee.isEmpty()) {
            throw new RMException(RoomApiConstant.ERROR_CODE, RoomApiConstant.ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        EmployeeDTO dto = new EmployeeDTO();
        Employee employeeToBeConvertToDTO = employee.get();
        dto.setId(employeeToBeConvertToDTO.getId());
        dto.setName(employeeToBeConvertToDTO.getName());
        dto.setAddress(employeeToBeConvertToDTO.getAddress());
        dto.setCity(employeeToBeConvertToDTO.getCity());
        dto.setZipCode(employeeToBeConvertToDTO.getZipCode());
        return dto;
    }

    @Override
    public List<EmployeeDTO> getAllEmp() {
        List<Employee> findAll = empRepository.findAll();
        if (!CollectionUtils.isEmpty(findAll)) {
            return this.convertEntityToDTO(findAll);
        }
        return null;
    }

    private List<EmployeeDTO> convertEntityToDTO(List<Employee> findAll) {
        List<EmployeeDTO> employeeDtoList = new ArrayList<>();
        for (Employee employee : findAll) {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setAddress(employee.getAddress());
            dto.setZipCode(employee.getZipCode());
            dto.setCity(employee.getCity());
            employeeDtoList.add(dto);
        }
        return employeeDtoList;

    }

    @Override
    public String deleteById(int id) throws RMException {
        Optional<Employee> employee = empRepository.findById(id);
        if (employee.isEmpty()) {
            throw new RMException(RoomApiConstant.ERROR_CODE, RoomApiConstant.ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        empRepository.deleteById(id);
        return RoomApiConstant.MESSAGE_FOR_SUCCESS_DLT + id;
    }

    @Override
    public EmployeeDTO updateEmployeeById(int id, Employee emp) throws RMException {
        Optional<Employee> employee = empRepository.findById(id);
        if (employee.isEmpty()) {
            throw new RMException(RoomApiConstant.ERROR_CODE, RoomApiConstant.ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        emp.setId(employee.get().getId());
        Employee updatEmployee = empRepository.save(emp);
        return modelMapper.map(updatEmployee, EmployeeDTO.class);
    }

    @Override
    public byte[] generatePDF(int id) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Employee byId = empRepository.findById(id).get();
            //  Employee employee = byId.get();
            log.info("employee data to generate the pdf:{}", byId);
            // Add content to the PDF
            document.add(new Paragraph(byId.toString()));


            document.close();
            byte[] pdfBytes = baos.toByteArray();

            // Define the directory and file path
            Path generatedPdfs = Paths.get("generated_pdfs");
            if (!Files.exists(generatedPdfs)) {
                Files.createDirectories(generatedPdfs);  // Create directory if not exists
            }

            // Save the PDF to the specified directory
            File pdfFile = new File(generatedPdfs.toFile(), "employee_" + id + ".pdf");
            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
            }

            return pdfBytes;
            // return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

}
