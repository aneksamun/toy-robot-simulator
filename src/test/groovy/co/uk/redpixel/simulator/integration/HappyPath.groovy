package co.uk.redpixel.simulator.integration

import co.uk.redpixel.simulator.domain.model.Board
import co.uk.redpixel.simulator.domain.model.CardinalDirection
import co.uk.redpixel.simulator.domain.model.Point
import co.uk.redpixel.simulator.domain.model.Robot
import co.uk.redpixel.simulator.infrastructure.cli.Command
import spock.lang.Specification

class HappyPath extends Specification {

    def 'happy path'() {
        given: 'a prerequisites'
        Board board = new Board(5, 5)
        Optional<Robot> maybeRobot = Optional.empty()

        when: 'I run commands'
        maybeRobot = executeCommand("PLACE 0,0,NORTH", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)
        maybeRobot = executeCommand("PLACE 0,0,NORTH", board, maybeRobot)
        maybeRobot = executeCommand("LEFT", board, maybeRobot)
        maybeRobot = executeCommand("PLACE 1,2,EAST", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)
        maybeRobot = executeCommand("LEFT", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)

        then: 'I get correct result'
        def robot = maybeRobot.get()

        robot.location == new Point(3,3)
        robot.facing == CardinalDirection.NORTH
    }

    def executeCommand(pattern, board, maybeRobot) {
        return Command.of(pattern).map { it.execute(board, maybeRobot.orElse(null)) }
    }
}
