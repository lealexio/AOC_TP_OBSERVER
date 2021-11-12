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
    private String name;
    private int value;

    public SensorImpl(String name, AlgoDiffusion strategy) {
        this.channels = new ArrayList<>();
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void tick() {
        System.out.println("\nSensor " + this.name + " tick");
        this.value++;
        strategy.execute();
    }


}
