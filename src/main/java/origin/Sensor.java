package origin;

import middle.Channel;

/**
 * Representation of a sensor
 */
public interface Sensor {
    /**
     * Attache channel to sensor
     * @param channel to attach
     */
    void attach(Channel channel);

    /**
     * Detach channel to sensor
     * @param channel to detach
     */
    void detach(Channel channel);

    /**
     * Get current value of sensor
     * @return current value of sensor
     */
    int getValue();

    /**
     * Get id of sensor
     * @return if of sensor
     */
    int getId();

    /**
     * The tick method increments the sensor value and calls the execute method of the desired diffusion method
     */
    void tick();
}
