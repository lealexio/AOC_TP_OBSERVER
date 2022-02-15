package middle;

import destination.SensorObserver;
import origin.Sensor;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChannelImpl implements Channel {

    public ScheduledExecutorService scheduledExecutorService;
    public Sensor sensor;
    public SensorObserver display;
    final Random random;

    public ChannelImpl(ScheduledExecutorService scheduledExecutorService, Sensor sensor, SensorObserver display) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.sensor = sensor;
        this.display = display;
        this.random = new Random();
    }

    public Future<?> update() {
        return scheduledExecutorService.schedule(() -> this.display.update(this), this.random.nextInt(1000) + 500, TimeUnit.MILLISECONDS);
    }

    public Future<Integer> getValue() {
        return scheduledExecutorService.schedule(() -> this.sensor.getValue(), this.random.nextInt(1000) + 500, TimeUnit.MILLISECONDS);
    }

    public SensorObserver getDisplay() {
        return display;
    }
}
