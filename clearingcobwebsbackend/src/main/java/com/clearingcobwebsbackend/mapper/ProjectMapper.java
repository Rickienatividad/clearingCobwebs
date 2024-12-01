package com.clearingcobwebsbackend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.clearingcobwebsbackend.dto.AppUserDTO;
import com.clearingcobwebsbackend.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  AppUserDTO userEntityToAppUserDTO(UserEntity userEntity);

  List<AppUserDTO> userEntityListToDTOList(Iterable<UserEntity> userEntityList);
}
