package hr.abysalto.hiring.mid.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessRequest {
    String username;
    String password;
}
