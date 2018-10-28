package co.uk.redpixel.simulator.infrastructure.cli;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.Robot;

public final class ReportCommand implements Command {

    @Override
    public Robot execute(Board board, Robot robot) {
        System.out.println(robot);
        return robot;
    }
}
