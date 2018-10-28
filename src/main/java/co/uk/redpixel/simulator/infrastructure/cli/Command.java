package co.uk.redpixel.simulator.infrastructure.cli;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.CardinalDirection;
import co.uk.redpixel.simulator.domain.model.Robot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public interface Command {

    Robot execute(Board board, Robot robot);

    default boolean requiresRobot() {
        return true;
    }

    @RequiredArgsConstructor
    enum CommandPattern {
        PLACE("(?i)PLACE (-?\\d+),(-?\\d+),(WEST|W|NORTH|N|SOUTH|S|EAST|E)$", s -> {
            val chunks = s.split("[, ]");
            val x = Integer.parseInt(chunks[1]);
            val y = Integer.parseInt(chunks[2]);
            val direction = CardinalDirection.get(chunks[3]);
            return new PlaceCommand(x, y, direction);
        }),
        MOVE("(?i)MOVE$", s -> new MoveCommand()),
        LEFT("(?i)LEFT$", s -> new RotateLeftCommand()),
        RIGHT("(?i)RIGHT$", s -> new RotateRightCommand()),
        REPORT("(?i)REPORT$", s -> new ReportCommand());

        @Getter
        private final String regex;

        @Getter
        private final Function<String, Command> create;
    }

    static Optional<Command> of(String line) {
        return stream(CommandPattern.values())
                .filter(command -> Pattern.compile(command.regex)
                        .matcher(line)
                        .matches())
                .findFirst()
                .map(command -> command.create.apply(line));
    }
}
