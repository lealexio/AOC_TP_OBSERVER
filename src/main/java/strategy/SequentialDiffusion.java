package strategy;

import middle.Channel;
import origin.SensorImpl;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// bloque le tick tant que tout les afficheurs n'ont pas les valueurs
public class SequentialDiffusion implements AlgoDiffusion {

    private SensorImpl capteur;
    private String NAME = "SequentialDiffusion";

    @Override
    public void configure(SensorImpl capteur) {
        this.capteur = capteur;
    }

    @Override
    public void execute() {
        System.out.println("START " + NAME + " execute");

        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Channel c : this.capteur.channels) {
            System.out.println("Call .update()");
            futures.add(c.update());
        }

        System.out.println("END " + NAME + " execute");
    }
}
