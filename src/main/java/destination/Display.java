package destination;

import middle.Channel;

import java.util.concurrent.ExecutionException;

public class Display implements SensorObserver {
    private String name;

    public Display(String name) {
        this.name = name;
    }

    public void update(Channel c) {
        try {
            System.out.println("Display " + this.name + " show value : " + c.getValue().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
