import destination.Display;
import destination.SensorObserver;
import middle.Channel;
import middle.ChannelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import origin.Sensor;
import origin.SensorImpl;
import strategy.SequentialDiffusion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequentialDiffusionTest {

    List<SensorObserver> displays;
    List<Channel> channels;
    Sensor sensor;
    SequentialDiffusion sequentialDiffusion;
    ScheduledExecutorService threadPool;

    // Nb loop for test
    Integer N = 2;

    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeEach
    void setUp() {
        threadPool = Executors.newScheduledThreadPool(8);
        sequentialDiffusion = new SequentialDiffusion();
        sensor = new SensorImpl(1, sequentialDiffusion);
        displays = new ArrayList<>();
        channels = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            displays.add(new Display(i));
            channels.add(new ChannelImpl(threadPool, sensor, displays.get(i)));
            sensor.attach(channels.get(i));
        }
    }

    @Test
    @DisplayName("SequentialDiffusionTick")
    void testSequentialDiffusion() {
        for (int i = 1; i <= N; i++) {
            sensor.tick();
            assertEquals(sensor.getValue(), i);
            for (SensorObserver display : displays) {
                assertEquals(display.getValue(), sensor.getValue());
            }
        }
    }

}
