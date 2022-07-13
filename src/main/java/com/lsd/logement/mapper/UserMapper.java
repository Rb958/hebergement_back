package com.lsd.logement.mapper;

import com.lsd.logement.dto.UserDTO;
import com.lsd.logement.entity.personnel.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface UserMapper extends GenericMapper<User, UserDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    User asEntity(UserDTO dto);
}