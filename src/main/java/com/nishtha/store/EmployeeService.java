package com.nishtha.store;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private AuditLogService auditLogService;

    public EmployeeService(EmployeeRepository employeeRepository, AuditLogService auditLogService) {
        this.employeeRepository = employeeRepository;
        this.auditLogService = auditLogService;
    }

    public Employee addEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        auditLogService.log("Employee", saved.getId(), "CREATE");
        return saved;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Page<Employee> getAllEmployees(int page, int size, String sortBy) {
        return employeeRepository.findAll(
                PageRequest.of(page, size, Sort.by(sortBy))
        );
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        Employee saved = employeeRepository.save(updatedEmployee);
        auditLogService.log("Employee", id, "UPDATE");
        return saved;
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        auditLogService.log("Employee", id, "DELETE");
    }
}