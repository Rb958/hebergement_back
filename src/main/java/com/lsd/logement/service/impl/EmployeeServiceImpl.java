package com.lsd.logement.service.impl;

import com.lsd.logement.dao.EmployeeRepository;
import com.lsd.logement.entity.personnel.Employee;
import com.lsd.logement.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee entity) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        entity.setCreatedAt(currentTime);
        entity.setLastUpdatedAt(currentTime);
        return repository.save(entity);
    }

    @Override
    public List<Employee> save(List<Employee> entities) {
        return (List<Employee>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) repository.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        Page<Employee> entityPage = repository.findAll(pageable);
        List<Employee> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Employee update(Employee entity, Integer id) {
        Optional<Employee> optional = findById(id);
        if (optional.isPresent()) {
            entity.setId(optional.get().getId());
            entity.setLastUpdatedAt(ZonedDateTime.now());
            return save(entity);
        }
        return null;
    }
}