package co.uk.redpixel.simulator.infrastructure.cli;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.CardinalDirection;
import co.uk.redpixel.simulator.domain.model.Point;
import co.uk.redpixel.simulator.domain.model.Robot;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PlaceCommand implements Command {

    private final int x, y;
    private final CardinalDirection direction;

    @Override
    public Robot execute(Board board, Robot robot) {
        Point point = new Point(x, y);
        return board.containsPoint(point) ?
                Robot.builder()
                        .location(point)
                        .direction(direction)
                        .build() :
                robot;
    }

    @Override
    public boolean requiresRobot() {
        return false;
    }
}
