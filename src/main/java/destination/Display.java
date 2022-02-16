package destination;

import middle.Channel;

import java.util.concurrent.ExecutionException;

public class Display implements SensorObserver {
    private final int id;
    private int value;

    /**
     * Initialization of the display
     * @param id is the id of the sensor
     */
    public Display(int id) {
        this.id = id;
    }

    public void update(Channel c) {
        try {
            int tmp_value = c.getValue().get();
            if (tmp_value > this.value) {
                this.value = tmp_value;
            }
            System.out.println("Display " + this.id + " show value : " + this.value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
