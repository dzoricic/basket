package hr.abysalto.hiring.mid.user.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class RegistrationRequest extends AccessRequest {
    @NotEmpty
    private final String firstName;
    @NotEmpty
    private final String lastName;
    @NotEmpty
    private final String title;

    public RegistrationRequest(String firstName, String lastName, String title, String username, String password) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }
}
