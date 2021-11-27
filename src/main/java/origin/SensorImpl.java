package origin;

import middle.Channel;
import strategy.AlgoDiffusion;
import strategy.AtomicDiffusion;
import strategy.SequentialDiffusion;

import java.util.ArrayList;
import java.util.List;

public class SensorImpl implements Sensor {
    public List<Channel> channels;
    public AlgoDiffusion strategy;
    private final int id;
    private int value;

    public SensorImpl(int id, AlgoDiffusion strategy) {
        this.channels = new ArrayList<>();
        this.id = id;
        this.value = 0;
        this.strategy = strategy;
        this.strategy.configure(this);
    }

    public void attach(Channel channel) {
        this.channels.add(channel);
    }

    public void detach(Channel channel) {
        this.channels.remove(channel);
    }

    public int getValue() {
        return this.value;
    }

    public int getId() {
        return id;
    }

    public void tick() {
        System.out.println("\nSensor " + this.id + " tick");
        this.value++;
        strategy.execute();
    }


}
