package hr.abysalto.hiring.mid.user.model;

import lombok.Getter;

@Getter
public class RegistrationRequest extends AccessRequest {
    private final String firstName;
    private final String lastName;
    private final String title;

    public RegistrationRequest(String firstName, String lastName, String title, String username, String password) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }
}
