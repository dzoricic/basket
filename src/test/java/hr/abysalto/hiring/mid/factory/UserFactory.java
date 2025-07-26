package hr.abysalto.hiring.mid.factory;

import hr.abysalto.hiring.mid.user.model.UserApiModel;
import hr.abysalto.hiring.mid.user.model.UserAuthDetails;
import hr.abysalto.hiring.mid.user.model.UserDto;
import hr.abysalto.hiring.mid.user.model.UserEntity;

import static hr.abysalto.hiring.mid.constants.UserConstants.*;

public class UserFactory {

    public static UserEntity givenUserEntity(int id) {
        var entity = new UserEntity();
        entity.setId(id);
        entity.setUsername(USERNAME);
        entity.setFirstName(FIRSTNAME);
        entity.setLastName(LASTNAME);
        entity.setTitle(TITLE);
        entity.setPassword(ENCRYPTED_PASSWORD);
        entity.setCreatedAt(CREATED_AT);
        entity.setUpdatedAt(UPDATED_AT);
        return entity;
    }


    public static UserDto givenUserDto(int id) {
        return new UserDto(
                id,
                FIRSTNAME,
                LASTNAME,
                USERNAME,
                TITLE
        );
    }

    public static UserApiModel givenUserApiModel() {
        return new UserApiModel(
                FIRSTNAME,
                LASTNAME,
                USERNAME,
                TITLE
        );
    }

    public static UserAuthDetails givenUserAuthDetails() {
        return new UserAuthDetails(
                USERNAME,
                ENCRYPTED_PASSWORD
        );
    }
}
