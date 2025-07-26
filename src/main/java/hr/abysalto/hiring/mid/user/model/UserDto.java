package hr.abysalto.hiring.mid.user.model;

public record UserDto(
        int id,
        String firstName,
        String lastName,
        String username,
        String title
) {
}
