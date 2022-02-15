package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// Stratégie atomique : les afficheurs reçoivent toutes les valeurs du capteur
public class AtomicDiffusion implements AlgoDiffusion {

    private SensorImpl capteur;

    @Override
    public void configure(SensorImpl capteur) {
        this.capteur = capteur;
    }

    @Override
    public void execute() {

        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Channel c : this.capteur.channels) {
            futures.add(c.update());
        }

        futures.forEach(i -> {
            try {
                i.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
