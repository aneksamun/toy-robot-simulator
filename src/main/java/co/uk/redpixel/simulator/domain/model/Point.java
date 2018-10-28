package co.uk.redpixel.simulator.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@EqualsAndHashCode
@RequiredArgsConstructor
public final class Point {

    @Getter
    private final int x;

    @Getter
    private final int y;

    public Point update(BiFunction<Integer, Integer, Point> action) {
        return action.apply(x, y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
