package hr.abysalto.hiring.mid.user.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessRequest {
    @NotEmpty
    String username;
    @NotEmpty
    String password;
}
