package origin;

import middle.Channel;

public interface Sensor {
    void attach(Channel channel);

    void detach(Channel channel);

    int getValue();

    String getName();

    void tick();
}
