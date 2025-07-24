package hr.abysalto.hiring.mid.common.util;

import hr.abysalto.hiring.mid.common.exceptions.InvalidIdException;
import org.hashids.Hashids;

import java.util.Objects;

public final class CryptUtils {

    private static final String DEFAULT_SALT = "sugar";
    private static final Integer DEFAULT_HASH_LENGTH = 16;

    private static final Hashids hashids = new Hashids(DEFAULT_SALT, DEFAULT_HASH_LENGTH);

    public static String encrypt(int id) {
        return hashids.encode(id);
    }

    public static int decrypt(String value) {
        var result = decodeId(value);

        if (Objects.isNull(result) || result.length == 0 || result[0] < 0 || result[0] > Integer.MAX_VALUE) {
            throw new InvalidIdException();
        }

        return (int) result[0];
    }

    private static long[] decodeId(String value) {
        try {
            return hashids.decode(value);
        } catch (IllegalArgumentException ex) {
            throw new InvalidIdException();
        }
    }
}
