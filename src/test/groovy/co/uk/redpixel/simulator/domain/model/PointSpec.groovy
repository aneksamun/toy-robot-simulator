package co.uk.redpixel.simulator.domain.model

import spock.lang.Specification

class PointSpec extends Specification {

    def 'test point toString()'() {
        given: 'a point'
        def point = new Point(1, 2)

        expect: 'x and y to be included in the string'
        point.toString() == "$point.x,$point.y"
    }
}
