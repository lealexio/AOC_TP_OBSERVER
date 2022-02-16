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


/**
 * Main method
 * @author Alexis Leloup
 * @author Simon Lecordier
 */
public class run {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Scanner scanner = new Scanner(System.in);

        // Add available diffusion to a list
        List<String> diffusions = new ArrayList<>();
        diffusions.add("strategy.EraDiffusion");
        diffusions.add("strategy.AtomicDiffusion");
        diffusions.add("strategy.SequentialDiffusion");

        // Diffusion selector
        int selected = diffusions.size() + 1;
        while (selected < 0 || selected >= diffusions.size()) {
            System.out.println("Please select a diffusion method :");
            for (int i = 0; i < diffusions.size(); i++) {
                System.out.println(i + " : " + diffusions.get(i));
            }
            selected = scanner.nextInt();
        }

        // Period selector
        int period = 0;
        while (period < 2) {
            System.out.println("Please choose a period greater than or equal to 2 :");
            period = scanner.nextInt();
        }

        // Period selector
        int nb_threads = 0;
        while (nb_threads < 5) {
            System.out.println("Please choose a amount of threads greater than or equal to 5 :");
            nb_threads = scanner.nextInt();
        }

        // Get diffusion from name
        AlgoDiffusion diffusion = (AlgoDiffusion) Class.forName(diffusions.get(selected)).getDeclaredConstructor().newInstance();

        // Init thread pool, min 5 required
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(nb_threads);

        // Init displays
        SensorObserver display1 = new Display(1);
        SensorObserver display2 = new Display(2);
        SensorObserver display3 = new Display(3);

        // Init sensor
        Sensor sensor1 = new SensorImpl(1, diffusion);

        // Inits channels and attach sensors and displays
        Channel channel1 = new ChannelImpl(threadPool, sensor1, display1);
        Channel channel2 = new ChannelImpl(threadPool, sensor1, display2);
        Channel channel3 = new ChannelImpl(threadPool, sensor1, display3);
        // Attach channels to sensors
        sensor1.attach(channel1);
        sensor1.attach(channel2);
        sensor1.attach(channel3);

        // Start threadPool
        threadPool.scheduleAtFixedRate(sensor1::tick, 0, period, TimeUnit.SECONDS);

    }
}
