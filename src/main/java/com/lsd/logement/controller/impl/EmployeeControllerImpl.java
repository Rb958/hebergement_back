package com.lsd.logement.controller.impl;

import com.lsd.logement.controller.EmployeeController;
import com.lsd.logement.dto.EmployeeDTO;
import com.lsd.logement.entity.personnel.Employee;
import com.lsd.logement.mapper.EmployeeMapper;
import com.lsd.logement.model.ApiResponse;
import com.lsd.logement.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/employee")
@RestController
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeControllerImpl(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody EmployeeDTO employeeDTO) {
        try {
            Employee employee = employeeMapper.asEntity(employeeDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(employeeMapper.asDTO(employeeService.save(employee)))
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> findById(@PathVariable("id") Integer id) {
        try {
            Employee employee = employeeService.findById(id).orElse(null);
            return ResponseEntity.ok(
                    new ApiResponse<>(employeeMapper.asDTO(employee))
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") Integer id) {
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(HttpStatus.OK.value(), "Employé supprimé avec succes")
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<?>> list() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(employeeMapper.asDTOList(employeeService.findAll()))
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @GetMapping("/page-query")
    public ResponseEntity<ApiResponse<?>> pageQuery(Pageable pageable) {
        try {
            Page<Employee> employeePage = employeeService.findAll(pageable);
            List<EmployeeDTO> dtoList = employeePage
                    .stream()
                    .map(employeeMapper::asDTO).collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ApiResponse<>(new PageImpl<>(dtoList, pageable, employeePage.getTotalElements()))
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Integer id) {
        try {
            Employee employee = employeeMapper.asEntity(employeeDTO);
            return ResponseEntity.ok(
                    new ApiResponse<>(employeeMapper.asDTO(employeeService.update(employee, id)))
            );
        }catch(Exception e){
            return ResponseEntity.ok(ApiResponse.from(e));
        }
    }
}