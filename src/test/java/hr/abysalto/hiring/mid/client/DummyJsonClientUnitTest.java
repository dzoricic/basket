package hr.abysalto.hiring.mid.client;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
public class DummyJsonClientUnitTest {

    @Mock
    private RestClient restClient;

    @InjectMocks
    private DummyJsonClient dummyJsonClient;

}