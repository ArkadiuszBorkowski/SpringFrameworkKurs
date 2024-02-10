package pl.training.shop.time;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimeStamp() {
        return Instant.now();
    }
}
