package co.uk.redpixel.simulator.infrastructure.cli;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.Robot;

import static co.uk.redpixel.simulator.domain.model.RotationDirection.LEFT;

public final class RotateLeftCommand implements Command {

    @Override
    public Robot execute(Board board, Robot robot) {
        return robot.rotate(LEFT);
    }
}
