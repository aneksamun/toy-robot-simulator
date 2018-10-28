package co.uk.redpixel.simulator;

import co.uk.redpixel.simulator.domain.model.Board;
import co.uk.redpixel.simulator.domain.model.Robot;
import co.uk.redpixel.simulator.infrastructure.cli.Command;
import co.uk.redpixel.simulator.infrastructure.property.SimulatorProperties;
import lombok.val;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

public class Application {

    private static volatile boolean EXIT;

    public static void main(String[] args) {

        System.out.println("Toy Robot Simulator\n");
        System.out.println("Press Ctrl+C to exit...\n");

        try {
            Board board = createBoard();
            Optional<Robot> robot = Optional.empty();

            try (Scanner scanner = new Scanner(System.in)) {
                while (!EXIT) {
                    System.out.print("Command: ");
                    String line = scanner.nextLine();
                    Optional<Robot> maybeRobot = Command.of(line)
                            .map(inspectRobotPlaced(robot))
                            .map(execute(board, robot.orElse(null)));

                    robot = maybeRobot.isPresent() ? maybeRobot : robot;
                }
            }
        } catch (Exception e) {
            System.out.println(format("An error occurred: %s", e.getMessage()));
        }

        getRuntime().addShutdownHook(new Thread(() -> EXIT = true));
    }

    private static Board createBoard() {
        val builder = Board.builder();
        val settings = SimulatorProperties.load();
        settings.getRowsCount().map(builder::rows);
        settings.getColumnCount().map(builder::columns);
        return builder.build();
    }

    private static Function<Command, Command> inspectRobotPlaced(Optional<Robot> robot) {
        return command -> !robot.isPresent() && command.requiresRobot() ? null : command;
    }

    private static Function<Command, Robot> execute(Board board, Robot robot) {
        return command -> command.execute(board, robot);
    }
}
