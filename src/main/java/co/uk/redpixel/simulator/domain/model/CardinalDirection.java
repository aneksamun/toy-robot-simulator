package co.uk.redpixel.simulator.domain.model;

import co.uk.redpixel.simulator.domain.common.Error;
import co.uk.redpixel.simulator.domain.exception.SimulatorException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.function.Predicate;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
public enum CardinalDirection {
    NORTH("N", 360),
    EAST("E", 90),
    SOUTH("S", 180),
    WEST("W", 270);

    @Getter
    private final String letter;

    @Getter
    private final int degree;

    public static CardinalDirection of(int degree, int angle) {
        val directions = values();
        val index = (int) Math.round((((double) degree % 360) / Math.abs(angle))) % directions.length;
        return directions[index];
    }

    public static CardinalDirection get(String word) {
        Predicate<CardinalDirection> matchNameOrLetter = direction ->
                direction.name().equalsIgnoreCase(word) || direction.letter.equalsIgnoreCase(word);

        return stream(values())
                .filter(matchNameOrLetter)
                .findFirst()
                .orElseThrow(() -> new SimulatorException(Error.INVALID_CARDINAL_DIRECTION, word));
    }
}
