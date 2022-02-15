package destination;

import middle.Channel;

public interface SensorObserver {
    void update(Channel c);

    Integer getValue();

    void setValue(int value);
}

