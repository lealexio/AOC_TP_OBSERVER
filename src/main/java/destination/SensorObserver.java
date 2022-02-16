package destination;

import middle.Channel;

/**
 * Interface that represents a sensor observer
 */
public interface SensorObserver {
    /**
     * Update current display by getting current value fo sensor
     * @param c the channel receiving the update request for the sensor value update
     */
    void update(Channel c);

    /**
     * Get current value attribute
     * @return value as integer
     */
    Integer getValue();

    /**
     * Set current value attribute
     * @param value to set as new
     */
    void setValue(int value);
}

