package com.example.employeeservice.controller;

import java.util.List;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") String id) throws JsonProcessingException {
        String message = "Employee find: id=" + id;
        LOGGER.info(new ObjectMapper().writeValueAsString(new LogMessage(message, "/{id}")));
//        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id).get();
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartmentId(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganizationId(organizationId);
    }

}

class LogMessage {
    private String message;

    private String path;

    public LogMessage(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}