package hr.abysalto.hiring.mid.user.mapper;

import hr.abysalto.hiring.mid.user.model.RegistrationRequest;
import hr.abysalto.hiring.mid.user.model.UserAuthDetails;
import hr.abysalto.hiring.mid.user.model.UserDto;
import hr.abysalto.hiring.mid.user.model.UserEntity;

public final class UserMapper {

    public static UserDto fromEntity(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUsername(),
                entity.getTitle()
        );
    }

    public static UserAuthDetails authDetailsFromEntity(UserEntity entity) {
        return new UserAuthDetails(entity.getUsername(), entity.getPassword());
    }

    public static UserEntity toEntity(RegistrationRequest request) {
        var entity = new UserEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setTitle(request.getTitle());
        entity.setUsername(request.getUsername());
        return entity;
    }
}
