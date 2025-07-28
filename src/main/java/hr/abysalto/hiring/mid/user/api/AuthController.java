package hr.abysalto.hiring.mid.user.api;

import hr.abysalto.hiring.mid.common.util.JwtUtils;
import hr.abysalto.hiring.mid.user.mapper.UserMapper;
import hr.abysalto.hiring.mid.user.model.AccessResponse;
import hr.abysalto.hiring.mid.user.model.RegistrationRequest;
import hr.abysalto.hiring.mid.user.model.UserApiModel;
import hr.abysalto.hiring.mid.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Service used for user registration and check.")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Operation(
            summary = "Register new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Registration request payload",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegistrationRequest.class),
                            examples = @ExampleObject(AuthPayloadExamples.REGISTRATION_REQUEST_PAYLOAD)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AccessResponse.class),
                                    examples = @ExampleObject(AuthPayloadExamples.ACCESS_RESPONSE_PAYLOAD)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AccessResponse> register(@Valid @RequestBody RegistrationRequest request) {
        var userDetails = userService.registerUser(request);
        var token = jwtUtils.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new AccessResponse(token));
    }

    @Operation(
            summary = "Register new user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserApiModel.class),
                                    examples = @ExampleObject(AuthPayloadExamples.ACTIVE_USER_RESPONSE_PAYLOAD)
                            )
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserApiModel> getCurrentUser(Authentication authentication) {
        var userDto = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(UserMapper.toApiModel(userDto));
    }
}
