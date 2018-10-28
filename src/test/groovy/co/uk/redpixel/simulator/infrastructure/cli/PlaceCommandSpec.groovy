package co.uk.redpixel.simulator.infrastructure.cli

import co.uk.redpixel.simulator.domain.model.Board
import co.uk.redpixel.simulator.domain.model.Point
import co.uk.redpixel.simulator.domain.model.Robot
import spock.lang.Specification

import static co.uk.redpixel.simulator.domain.model.CardinalDirection.NORTH
import static co.uk.redpixel.simulator.domain.model.CardinalDirection.SOUTH

class PlaceCommandSpec extends Specification {

    def 'should update robot on successful placement'() {
        given: 'a board'
        def board = new Board(5, 5)

        and: 'robot'
        def robot = new Robot(NORTH, new Point(0, 0))

        when: 'command is executed'
        def command = new PlaceCommand(3, 3, SOUTH)
        def updated = command.execute(board, robot)

        then: 'robot is updated'
        updated.facing == command.direction
        updated.location.x == command.x
        updated.location.y == command.y
    }

    def 'should not update robot on placement failure'() {
        given: 'a board'
        def board = new Board(2, 2)

        and: 'robot'
        def robot = new Robot(NORTH, new Point(0, 0))

        when: 'command is failed'
        def command = new PlaceCommand(-1, 1, SOUTH)
        def actual = command.execute(board, robot)

        then: 'robot is not updated'
        actual == robot
    }
}
