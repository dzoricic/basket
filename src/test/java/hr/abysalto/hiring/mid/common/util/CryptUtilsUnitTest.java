package hr.abysalto.hiring.mid.common.util;

import hr.abysalto.hiring.mid.product.exception.InvalidProductIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static hr.abysalto.hiring.mid.product.exception.InvalidProductIdException.INVALID_PRODUCT_ID_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class CryptUtilsUnitTest {

    @Test
    void shouldDecryptIdGivenHashValue() {
        // given
        var givenProductId = 123;
        var givenEncryptedProductId = CryptUtils.encrypt(givenProductId);

        // when
        var actual = CryptUtils.decrypt(givenEncryptedProductId);

        // then
        then(actual).isEqualTo(givenProductId);
    }

    @ParameterizedTest
    @MethodSource("provideUnsupportedProductIds")
    void shouldThrowGivenInvalidId(String givenInvalidProductId) {
        // when
        var actual = catchThrowable(() -> CryptUtils.decrypt(givenInvalidProductId));

        // then
        then(actual).isExactlyInstanceOf(InvalidProductIdException.class);
        then(actual.getMessage()).isEqualTo(INVALID_PRODUCT_ID_ERROR_MESSAGE);
    }

    private static Stream<Arguments> provideUnsupportedProductIds() {
        return Stream.of(
                Arguments.of("invalidProductIdString"),
                Arguments.of("12.3"),
                Arguments.of("-1"),
                Arguments.of("0"),
                Arguments.of("99999999999")
        );
    }
}
