package Tests;

import java.time.Instant;

public class Global {
    private static String username;

    public static String getUsername() {
        if (username == null || username.length() <= 0) {
            username = String.valueOf(Instant.now().getEpochSecond());
        }
        return username;
    }

}
