package co.uk.redpixel.simulator.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
public final class Board {

    @Builder.Default @Getter
    private final int rows = 5;

    @Builder.Default @Getter
    private final int columns = 5;

    public boolean containsPoint(Point location) {
        return location.getX() >= 0 && location.getX() < columns &&
               location.getY() >= 0 && location.getY() < rows;
    }
}
