package co.uk.redpixel.simulator.infrastructure.cli

import spock.lang.Specification
import spock.lang.Unroll

class CommandSpec extends Specification {

    @Unroll
    def 'should succeed: "#pattern"'() {
        when:
        def maybeCmd = Command.of(pattern)

        then:
        maybeCmd.isPresent()

        where:
        pattern               | _
        "PLACE 0,0,NORTH"     | _
        "PLACE -1,-1,N"       | _
        "place 90,1000,North" | _
        "PLACE -1,0,s"        | _
        "place 8,9,SOUTH"     | _
        "Place 0,0,east"      | _
        "place -40,8,E"       | _
        "PlAcE 0,0,W"         | _
        "pLAcE 999,000,WeSt"  | _
        "MOVE"                | _
        "left"                | _
        "Right"               | _
        "rEpOrT"              | _
    }

    @Unroll
    def 'should fail: "#pattern"'() {
        when:
        def maybeCmd = Command.of(pattern)

        then:
        !maybeCmd.isPresent()

        where:
        pattern                   | _
        "PLACED 0,0,NORTH"        | _
        "PLACE -1A,P,N"           | _
        "place 90,1000,NorthWest" | _
        "PLACE A,0,s"             | _
        "Aplace 8,9,SOUTH"        | _
        "MOVED"                   | _
        "123"                     | _
        "RIGHTMOVE"               | _
        "LEFT_"                   | _
        "REPORRTED"               | _
    }
}
