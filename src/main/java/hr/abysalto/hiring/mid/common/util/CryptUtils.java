package hr.abysalto.hiring.mid.common.util;

import org.hashids.Hashids;

import java.util.Objects;

public final class CryptUtils {

    private static final String DEFAULT_SALT = "sugar";
    private static final Integer DEFAULT_HASH_LENGTH = 16;

    private static final Hashids hashids = new Hashids(DEFAULT_SALT, DEFAULT_HASH_LENGTH);

    public static String encrypt(int id) {
        return hashids.encode(id);
    }

    public static int decrypt(String encoded) {
        var result = hashids.decode(encoded);

        if (Objects.isNull(result) || result.length == 0 || result[0] < 0 || result[0] > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return (int) result[0];
    }
}
