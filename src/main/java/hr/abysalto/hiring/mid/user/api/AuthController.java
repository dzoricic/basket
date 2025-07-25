package hr.abysalto.hiring.mid.user.api;

import hr.abysalto.hiring.mid.user.model.RegistrationRequest;
import hr.abysalto.hiring.mid.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Auth", description = "Service used for new user registration.")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "Register new user",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegistrationRequest request) {
        userService.registerUser(request);
        return ResponseEntity.created(URI.create("/api/register")).build();
    }
}
