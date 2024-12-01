package mapper;

import java.util.List;

import org.mapstruct.Mapper;

import dto.AppUserDTO;
import entities.UserEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  AppUserDTO userEntityToAppUserDTO(UserEntity userEntity);

  List<AppUserDTO> userEntityListToDTOList(Iterable<UserEntity> userEntityList);
}
