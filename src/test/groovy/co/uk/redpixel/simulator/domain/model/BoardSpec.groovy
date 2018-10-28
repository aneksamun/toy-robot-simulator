package co.uk.redpixel.simulator.domain.model

import spock.lang.Specification
import spock.lang.Unroll

class BoardSpec extends Specification {

    @Unroll
    def 'test can place: (#point)'() {
        given: 'a board'
        def board = new Board(5, 5)

        expect: 'a valid coordinates'
        board.containsPoint(point)

        where:
        point           | _
        new Point(0, 0) | _
        new Point(4, 4) | _
        new Point(1, 3) | _
    }

    @Unroll
    def 'test cannot place: (#point)'() {
        given: 'a board'
        def board = new Board(5, 5)

        expect: 'invalid coordinates'
        !board.containsPoint(point)

        where:
        point             | _
        new Point(-1, -1) | _
        new Point(5, 5)   | _
        new Point(9, 0)   | _
    }

    def 'test board building with default size'() {
        given: 'A builder'
        def builder = Board.builder()

        when: 'board is build'
        def board = builder.build()

        then: 'board has default column and row size'
        board.columns == 5 && board.rows == 5
    }

    def 'test board building with custom size'() {
        given: 'a custom size'
        def expectedRows = 7
        def expectedColumns = 5

        when: 'board is build'
        def board = Board.builder()
                .columns(expectedColumns)
                .rows(expectedRows)
                .build()

        then: 'board has specified column and row size'
        board.columns == expectedColumns && board.rows == expectedRows
    }
}
