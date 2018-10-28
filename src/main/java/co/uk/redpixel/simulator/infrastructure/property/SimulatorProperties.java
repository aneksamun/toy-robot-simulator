package co.uk.redpixel.simulator.infrastructure.property;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import static co.uk.redpixel.simulator.domain.common.Error.CONFIG_LOAD_ERROR;

public final class SimulatorProperties extends Properties {

    private static final String PROPERTIES_FILE = "application.properties";
    private static final String BOARD_ROWS_PROPERTY = "board.rows";
    private static final String BOARD_COLUMNS_PROPERTY = "board.columns";

    private SimulatorProperties() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            try (InputStream stream = classLoader.getResourceAsStream(PROPERTIES_FILE)) {
                this.load(stream);
            }
        } catch (Exception e) {
            throw new ConfigurationException(CONFIG_LOAD_ERROR, e.getMessage());
        }
    }

    public Optional<Integer> getRowsCount() {
        return Optional.ofNullable(getProperty(BOARD_ROWS_PROPERTY))
                       .map(Integer::parseInt);
    }

    public Optional<Integer> getColumnCount() {
        return Optional.ofNullable(getProperty(BOARD_COLUMNS_PROPERTY))
                       .map(Integer::parseInt);
    }

    public static SimulatorProperties load() {
        return new SimulatorProperties();
    }
}
