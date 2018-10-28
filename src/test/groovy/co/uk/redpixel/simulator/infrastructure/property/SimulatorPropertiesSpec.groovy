package co.uk.redpixel.simulator.infrastructure.property

import spock.lang.Specification

class SimulatorPropertiesSpec extends Specification {

    def 'should read rows count'() {
        when: 'properties are read'
        def properties = SimulatorProperties.load()

        then: 'rows count present'
        properties.getRowsCount().isPresent()
    }

    def 'should read columns count'() {
        when: 'properties are read'
        def properties = SimulatorProperties.load()

        then: 'columns count present'
        properties.getColumnCount().isPresent()
    }
}
