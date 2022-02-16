package middle;

import destination.SensorObserver;

import java.util.concurrent.Future;

/**
 * Interface representing a channel, a channel is the link between the sensor and the receiver
 */
public interface Channel {
    /**
     * Refresh the display
     * @return void
     */
    Future<?> update();

    /**
     * Get value of sensor
     * @return value of sensor
     */
    Future<Integer> getValue();

    /**
     * Get attached display
     * @return attached display
     */
    SensorObserver getDisplay();
}
