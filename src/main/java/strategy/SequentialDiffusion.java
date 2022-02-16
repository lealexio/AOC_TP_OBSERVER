package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Diffusion strategy: the displays receive the same subset of values from the sensor
 */
public class SequentialDiffusion implements AlgoDiffusion {

    private SensorImpl sensor;

    @Override
    public void configure(SensorImpl sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        Map<Future<?>, Channel> futures = new HashMap<>();

        // List of future from update method
        for (Channel c : this.sensor.channels) {
            futures.put(c.update(), c);
        }

        // Nb ended futures
        int ended = 0;
        // Loop to get futures, when all futures are received loop is break
        while (ended < futures.size()) {
            ended = 0;
            for (Map.Entry<Future<?>, Channel> entry : futures.entrySet()) {
                Future<?> f = entry.getKey();
                Channel c = entry.getValue();
                // When a future is done
                if (f.isDone()) {
                    // Get value of display on done future
                    int best_value = c.getDisplay().getValue();
                    // For every display, if current best value is > than current
                    // Set new display value as current best_value
                    for (Channel tmp_c : futures.values()) {
                        if (best_value > tmp_c.getDisplay().getValue()) {
                            tmp_c.getDisplay().setValue(best_value);
                        }
                    }
                    // Increment ended futures
                    ended++;
                }
            }
        }
    }
}
