import destination.Display;
import destination.SensorObserver;
import middle.Channel;
import middle.ChannelImpl;
import origin.Sensor;
import origin.SensorImpl;
import strategy.AlgoDiffusion;
import strategy.AtomicDiffusion;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class run {
    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(8);

        SensorObserver display1 = new Display("1");
        SensorObserver display2 = new Display("2");
        SensorObserver display3 = new Display("3");
        SensorObserver display4 = new Display("4");

        Sensor sensor1 = new SensorImpl("Capteur 1", new AtomicDiffusion());
        Channel channel1 = new ChannelImpl(threadPool, sensor1, display1);
        Channel channel2 = new ChannelImpl(threadPool, sensor1, display2);
        Channel channel3 = new ChannelImpl(threadPool, sensor1, display3);
        Channel channel4 = new ChannelImpl(threadPool, sensor1, display4);
        sensor1.attach(channel1);
        sensor1.attach(channel2);
        sensor1.attach(channel3);
        sensor1.attach(channel4);

        threadPool.scheduleAtFixedRate(sensor1::tick, 0, 2, TimeUnit.SECONDS);
        //threadPool.scheduleAtFixedRate(sensor2::tick, 1, 1, TimeUnit.SECONDS);

    //ticker.cancel(true);
    //scdheduler.awaitTermiantion
    }
}
