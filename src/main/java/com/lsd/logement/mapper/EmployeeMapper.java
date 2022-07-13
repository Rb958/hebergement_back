package com.lsd.logement.mapper;

import com.lsd.logement.dto.EmployeeDTO;
import com.lsd.logement.entity.personnel.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface EmployeeMapper extends GenericMapper<Employee, EmployeeDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Employee asEntity(EmployeeDTO dto);
}