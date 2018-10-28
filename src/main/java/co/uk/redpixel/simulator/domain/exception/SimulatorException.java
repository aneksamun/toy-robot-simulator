package co.uk.redpixel.simulator.domain.exception;

import co.uk.redpixel.simulator.domain.common.Error;

import static java.lang.String.format;

public class SimulatorException extends RuntimeException {

    public SimulatorException(Error error, Object... args) {
        super(format(error.getErrorMessage(), args));
    }

    public SimulatorException(Error error) {
        super(error.getErrorMessage());
    }
}
