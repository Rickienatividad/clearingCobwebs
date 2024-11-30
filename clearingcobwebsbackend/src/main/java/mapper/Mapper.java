package mapper;

import dto.AppUserDTO;
import entities.UserEntity;

public class Mapper {
  private AppUserDTO userEntityToAppUserDTO(UserEntity userEntity) {
    AppUserDTO appUserDTO = new AppUserDTO();
    appUserDTO.setEmail(userEntity.getEmail());
    appUserDTO.setFirstName(userEntity.getFirstName());
    appUserDTO.setLastName(userEntity.getLastName());

    return appUserDTO;
  }
}
