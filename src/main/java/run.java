import destination.Display;
import destination.SensorObserver;
import middle.Channel;
import middle.ChannelImpl;
import origin.Sensor;
import origin.SensorImpl;
import strategy.AlgoDiffusion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class run {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Scanner scanner = new Scanner(System.in);

        // Add available diffusion to a list
        List<String> diffusions = new ArrayList<>();
        diffusions.add("strategy.EraDiffusion");
        diffusions.add("strategy.AtomicDiffusion");
        diffusions.add("strategy.SequentialDiffusion");


        System.out.println("Please select a diffusion method :");
        int selected = diffusions.size() + 1;

        while (selected < 0 || selected >= diffusions.size()) {
            for (int i = 0; i < diffusions.size(); i++) {
                System.out.println(i + " : " + diffusions.get(i));
            }
            selected = scanner.nextInt();
        }
        AlgoDiffusion diffusion = (AlgoDiffusion) Class.forName(diffusions.get(selected)).getDeclaredConstructor().newInstance();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(8);

        SensorObserver display1 = new Display(1);
        SensorObserver display2 = new Display(2);
        SensorObserver display3 = new Display(3);

        Sensor sensor1 = new SensorImpl(1, diffusion);
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
