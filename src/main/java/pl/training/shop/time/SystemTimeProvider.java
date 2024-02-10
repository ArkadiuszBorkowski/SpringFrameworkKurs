package pl.training.shop.time;

import java.time.Instant;

public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimeStamp() {
        return Instant.now();
    }
}
