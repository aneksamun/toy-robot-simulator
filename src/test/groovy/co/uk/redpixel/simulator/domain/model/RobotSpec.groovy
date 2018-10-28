package co.uk.redpixel.simulator.domain.model

import spock.lang.Specification
import spock.lang.Unroll

import static CardinalDirection.*
import static RotationDirection.*

class RobotSpec extends Specification {

    def 'test robot toString()'() {
        given: 'a robot'
        def robot = Robot.builder()
                .direction(EAST)
                .location(new Point(1, 2))
                .build()

        expect: 'x, y and direction to be included in the string'
        robot.toString() == "${robot.location.x},${robot.location.y},${robot.facing}"
    }

    @Unroll
    def 'test robot rotation to the left: #initial -> #expected'() {
        given: 'a robot'
        def origin = Robot.of(initial, new Point(0, 0))

        when: 'I rotate the robot'
        def rotated = origin.rotate(LEFT)

        then: 'robot is facing correct direction'
        rotated.facing == expected

        and: 'location remains the same'
        origin.location == rotated.location

        where:
        initial | expected
        NORTH   | WEST
        WEST    | SOUTH
        SOUTH   | EAST
        EAST    | NORTH
    }

    @Unroll
    def 'test robot rotation to the right: #initial -> #expected'() {
        given: 'a robot'
        def origin = Robot.of(initial, new Point(3, 3))

        when: 'I rotate the robot'
        def rotated = origin.rotate(RIGHT)

        then: 'robot is facing correct direction'
        rotated.facing == expected

        and: 'location remains the same'
        origin.location == rotated.location

        where:
        initial | expected
        NORTH   | EAST
        EAST    | SOUTH
        SOUTH   | WEST
        WEST    | NORTH
    }
}
