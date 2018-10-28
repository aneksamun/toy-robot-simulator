package co.uk.redpixel.simulator.domain.model

import co.uk.redpixel.simulator.domain.exception.SimulatorException
import spock.lang.Specification
import spock.lang.Unroll

import static CardinalDirection.NORTH
import static CardinalDirection.EAST
import static CardinalDirection.WEST
import static CardinalDirection.SOUTH
import static RotationDirection.LEFT

class CardinalDirectionSpec extends Specification {

    def 'should throw exception parsing invalid direction'() {
        given: 'invalid direction pattern'
        def invalidPattern = 'ABBA'

        when: 'when converting'
        CardinalDirection.get(invalidPattern)

        then: 'exception is thrown'
        thrown SimulatorException
    }

    @Unroll
    def 'should get right direction: #pattern'() {
        expect: 'success'
        CardinalDirection.get(pattern) == expected

        where:
        pattern | expected
        'W'     | WEST
        'West'  | WEST
        'NORTH' | NORTH
        'N'     | NORTH
        'south' | SOUTH
        's'     | SOUTH
        'EAST'  | EAST
        'e'     | EAST
    }

    @Unroll
    def 'should correctly detect new direction: (#degree, #direction)'() {
        expect: 'success'
        CardinalDirection.of(degree, LEFT.angle) == direction

        where:
        direction | degree
        NORTH     | 0
        NORTH     | 360
        EAST      | 90
        SOUTH     | 180
        WEST      | 270
    }
}
