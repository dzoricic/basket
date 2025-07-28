package hr.abysalto.hiring.mid.user.api;

public final class AuthPayloadExamples {

    public static final String REGISTRATION_REQUEST_PAYLOAD = """
            {
              "username": "myCoolUsername",
              "password": "Password123",
              "firstName": "MyName",
              "lastName": "MyLastName",
              "title": "The"
            }
            """;

    public static final String ACCESS_RESPONSE_PAYLOAD = """
            {
              "token": "{...}"
            }
            """;

    public static final String ACTIVE_USER_RESPONSE_PAYLOAD = """
            {
                "username": "myCoolUsername",
                "firstname": "MyName",
                "lastname": "MyLastName",
                "title": "The"
            }
            """;
}
