package co.uk.redpixel.simulator.infrastructure.cli

import co.uk.redpixel.simulator.domain.model.Board
import co.uk.redpixel.simulator.domain.model.Point
import co.uk.redpixel.simulator.domain.model.Robot
import spock.lang.Specification

import static co.uk.redpixel.simulator.domain.model.CardinalDirection.EAST
import static co.uk.redpixel.simulator.domain.model.CardinalDirection.SOUTH

class RotateRightCommandSpec extends Specification {

    def 'test command'() {
        given: 'a board'
        def board = new Board(7, 9)

        and: 'robot'
        def robot = new Robot(EAST, new Point(0, 0))

        when: 'robot is rotated'
        def rotated = new RotateRightCommand().execute(board, robot)

        then: 'direction is changed'
        rotated.facing == SOUTH
    }
}
