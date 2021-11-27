import destination.Display;
import destination.SensorObserver;
import middle.Channel;
import middle.ChannelImpl;
import origin.Sensor;
import origin.SensorImpl;
import strategy.AlgoDiffusion;
import strategy.AtomicDiffusion;
import strategy.EraDiffusion;
import strategy.SequentialDiffusion;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class run {
    public static void main(String[] args) {



        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(8);

        SensorObserver display1 = new Display(1);
        SensorObserver display2 = new Display(2);
        SensorObserver display3 = new Display(3);

        Sensor sensor1 = new SensorImpl(1, new EraDiffusion());
        Channel channel1 = new ChannelImpl(threadPool, sensor1, display1);
        Channel channel2 = new ChannelImpl(threadPool, sensor1, display2);
        Channel channel3 = new ChannelImpl(threadPool, sensor1, display3);
        sensor1.attach(channel1);
        sensor1.attach(channel2);
        sensor1.attach(channel3);

        threadPool.scheduleAtFixedRate(sensor1::tick, 0, 2, TimeUnit.SECONDS);

    //ticker.cancel(true);
    //scdheduler.awaitTermiantion
    }
}
