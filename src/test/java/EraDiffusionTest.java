import destination.Display;
import destination.SensorObserver;
import middle.Channel;
import middle.ChannelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import origin.Sensor;
import origin.SensorImpl;
import strategy.AtomicDiffusion;
import strategy.EraDiffusion;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EraDiffusionTest {

    SensorObserver display1;
    SensorObserver display2;
    SensorObserver display3;
    Sensor sensor1;
    Channel channel1;
    Channel channel2;
    Channel channel3;
    EraDiffusion eraDiffusion;
    ScheduledExecutorService threadPool;
    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeEach
    void setUp() {
        threadPool = Executors.newScheduledThreadPool(8);
        eraDiffusion = new EraDiffusion();

        display1 = new Display(1);
        display2 = new Display(2);
        display3 = new Display(3);

        sensor1 = new SensorImpl(1, eraDiffusion);
        channel1 = new ChannelImpl(threadPool, sensor1, display1);
        channel2 = new ChannelImpl(threadPool, sensor1, display2);
        channel3 = new ChannelImpl(threadPool, sensor1, display3);
        sensor1.attach(channel1);
        sensor1.attach(channel2);
        sensor1.attach(channel3);
    }

    @Test
    @DisplayName("Unique tick")
    void testUniqueTick() throws InterruptedException {
        sensor1.tick();
        lock.await(5000, TimeUnit.MILLISECONDS);
        assertEquals(sensor1.getValue(), 1);
        assertEquals(display1.getValue(), 1);
        assertEquals(display2.getValue(), 1);
        assertEquals(display3.getValue(), 1);
    }

    @Test
    @DisplayName("Triple tick")
    void testTripleTick() {
        sensor1.tick();
        assertEquals(sensor1.getValue(), 1);
        assertEquals(display1.getValue(), 1);
        assertEquals(display2.getValue(), 1);
        assertEquals(display3.getValue(), 1);

        sensor1.tick();
        assertEquals(sensor1.getValue(), 2);
        assertEquals(display1.getValue(), 2);
        assertEquals(display2.getValue(), 2);
        assertEquals(display3.getValue(), 2);

        sensor1.tick();
        assertEquals(sensor1.getValue(), 3);
        assertEquals(display1.getValue(), 3);
        assertEquals(display2.getValue(), 3);
        assertEquals(display3.getValue(), 3);
    }

}
