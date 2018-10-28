package co.uk.redpixel.simulator.infrastructure.cli;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.CardinalDirection;
import co.uk.redpixel.simulator.domain.model.Point;
import co.uk.redpixel.simulator.domain.model.Robot;

public final class MoveCommand implements Command {

    @Override
    public Robot execute(Board board, Robot robot) {
        Point current = robot.getLocation();
        Point latest = move(current, robot.getFacing());
        return board.containsPoint(latest) ?
                Robot.builder()
                        .location(latest)
                        .direction(robot.getFacing())
                        .build() :
                robot;
    }

    private static Point move(Point point, CardinalDirection towards) {
        switch (towards) {
            case SOUTH:
                return point.update((x, y) -> new Point(x, --y));
            case WEST:
                return point.update((x, y) -> new Point(--x, y));
            case EAST:
                return point.update((x, y) -> new Point(++x, y));
            case NORTH:
                return point.update((x, y) -> new Point(x, ++y));
            default:
                return point;
        }
    }
}
