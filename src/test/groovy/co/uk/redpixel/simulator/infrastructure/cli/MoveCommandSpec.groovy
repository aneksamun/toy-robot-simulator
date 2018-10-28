package co.uk.redpixel.simulator.infrastructure.cli

import co.uk.redpixel.simulator.domain.model.Board
import co.uk.redpixel.simulator.domain.model.Point
import co.uk.redpixel.simulator.domain.model.Robot
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static co.uk.redpixel.simulator.domain.model.CardinalDirection.EAST
import static co.uk.redpixel.simulator.domain.model.CardinalDirection.NORTH
import static co.uk.redpixel.simulator.domain.model.CardinalDirection.SOUTH
import static co.uk.redpixel.simulator.domain.model.CardinalDirection.WEST

class MoveCommandSpec extends Specification {

    @Shared
    def cmd = new MoveCommand()

    @Unroll
    def 'test moves: #robot.facing'() {
        given: 'a board'
        def board = new Board(3, 3)

        when: 'command is executed'
        def moved = cmd.execute(board, robot)

        then: 'robot is moved correctly'
        moved.location == expected

        where:
        robot                      | expected
        Robot.of(SOUTH, new Point(1, 1)) | new Point(1, 0)
        Robot.of(WEST, new Point(1, 1)) | new Point(0, 1)
        Robot.of(EAST, new Point(1, 1)) | new Point(2, 1)
        Robot.of(NORTH, new Point(1, 1)) | new Point(1, 2)
    }

    def 'should prevent robot from falling to destruction'() {
        given: 'a board'
        def board = new Board(3, 3)

        and: 'robot in the south west most corner'
        def robot = new Robot(SOUTH, new Point(0, 0))

        when: 'command is executed'
        def actual = cmd.execute(board, robot)

        then: 'no move is performed'
        actual.location == robot.location
    }
}
