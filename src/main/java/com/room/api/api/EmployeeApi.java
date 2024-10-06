/**
 *
 */
package com.room.api.api;

import com.room.api.exception.RMException;
import com.room.api.model.Employee;
import com.room.api.model.EmployeeDTO;
import com.room.api.service.IEmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this is API
 *
 * @author SAMEER
 * @see @Controller
 * @see @ResponseBody
 * @see IEmployeeService
 */
@Controller
@ResponseBody
@RequestMapping("/employee")
@Log4j2
public class EmployeeApi {

    private final IEmployeeService service;

    public EmployeeApi(IEmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/createEmp", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
    public String createEmployee(@RequestBody Employee employee) {
        String createEmployee = service.createEmployee(employee);
        return createEmployee + "Success";

    }

    @GetMapping("/getEmpById/{id}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable("id") int empId) throws RMException {
        log.info("Request for this API accessing:{}", empId);
        EmployeeDTO empById = service.getEmpById(empId);
        log.info("db details for this request:{}", empById);
        return ResponseEntity.ok(empById);

    }

    /**
     * Using request param
     *
     * @param empId
     * @return
     */

    @GetMapping("/getEmpById")
    public ResponseEntity<EmployeeDTO> getEmpByIdByRequestParam(@RequestParam int empId) throws RMException {
        EmployeeDTO empById = service.getEmpById(empId);

        if (empById != null) {
            return ResponseEntity.ok(empById);
        }
        return ResponseEntity.notFound().build();

    }

    /**
     * Used to get All data
     *
     * @return ResponseEntity
     * @see EmployeeDTO
     */

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> allEmp = service.getAllEmp();
        if (allEmp != null) {
            return new ResponseEntity<>(allEmp, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(allEmp, HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable(required = true) int id) throws RMException {
        return service.deleteById(id);

    }

    @PutMapping("/updateById/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmpById(@PathVariable("empId") int id, @RequestBody Employee employee) throws RMException {
        EmployeeDTO updateEmployeeById = service.updateEmployeeById(id, employee);
        return new ResponseEntity<>(updateEmployeeById, HttpStatus.OK);

    }

    @GetMapping("/generate_pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable int id) {
        try {
            byte[] pdfContent = service.generatePDF(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "generated.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
