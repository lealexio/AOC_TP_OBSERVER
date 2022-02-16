package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Atomic strategy: the displays receive all the values from the sensor
 */
public class AtomicDiffusion implements AlgoDiffusion {

    private SensorImpl sensor;

    @Override
    public void configure(SensorImpl sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        // Update the displays
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Channel c : this.sensor.channels) {
            futures.add(c.update());
        }

        // Wait end of each future
        futures.forEach(i -> {
            try {
                i.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
