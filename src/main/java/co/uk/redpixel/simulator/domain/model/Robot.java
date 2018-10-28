package co.uk.redpixel.simulator.domain.model;

import lombok.*;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class Robot {

    @Getter
    private final CardinalDirection facing;

    @Getter
    private final Point location;

    public Robot rotate(RotationDirection rotation) {
        val rotatedDegree = facing.getDegree() + rotation.getAngle();
        val direction = CardinalDirection.of(rotatedDegree, rotation.getAngle());
        return Robot.builder()
                .direction(direction)
                .location(location)
                .build();
    }

    @Override
    public String toString() {
        return format("%s,%s", location, facing);
    }

    @Builder
    public static Robot of(@NonNull CardinalDirection direction,
                           @NonNull Point location) {
        return new Robot(direction, location);
    }
}
