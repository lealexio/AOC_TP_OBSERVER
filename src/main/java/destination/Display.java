package destination;

import middle.Channel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Display implements SensorObserver {
    private final int id;
    private Object value;

    public Display(int id) {
        this.id = id;
    }

    public void update(Channel c) {
        try {
            this.value = c.getValue().get();
            System.out.println("Display " + this.id + " show value : " + this.value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
