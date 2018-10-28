package co.uk.redpixel.simulator.integration

import co.uk.redpixel.simulator.domain.model.Board
import co.uk.redpixel.simulator.domain.model.Point
import co.uk.redpixel.simulator.domain.model.Robot
import co.uk.redpixel.simulator.infrastructure.cli.Command
import spock.lang.Specification

class PreventingFall extends Specification {

    def 'saving robot'() {
        given: 'a prerequisites'
        Board board = new Board(5, 5)
        Optional<Robot> maybeRobot = Optional.empty()

        when: 'I run commands'
        maybeRobot = executeCommand("PLACE 0,0,SOUTH", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)
        maybeRobot = executeCommand("RIGHT", board, maybeRobot)
        maybeRobot = executeCommand("MOVE", board, maybeRobot)

        then: 'robot must stay on board'
        def robot = maybeRobot.get()
        robot.location == new Point(0,0)
    }

    def executeCommand(pattern, board, maybeRobot) {
        return Command.of(pattern).map { it.execute(board, maybeRobot.orElse(null)) }
    }
}
