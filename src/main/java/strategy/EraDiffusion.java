package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Epochal strategy: the displays can receive different subsets of sensor values, but these subsets must be increasing
 */
public class EraDiffusion implements AlgoDiffusion {

    private SensorImpl sensor;

    @Override
    public void configure(SensorImpl sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        // Update the displays
        for (Channel c : this.sensor.channels) {
            c.update();
        }
    }
}
