package middle;

import destination.SensorObserver;

import java.util.concurrent.Future;

public interface Channel {
    Future<?> update();

    Future<Integer> getValue();

    SensorObserver getDisplay();
}
