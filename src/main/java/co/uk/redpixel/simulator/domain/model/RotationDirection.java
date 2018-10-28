package co.uk.redpixel.simulator.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RotationDirection {
    LEFT(-90),
    RIGHT(90);

    @Getter
    private final int angle;
}
