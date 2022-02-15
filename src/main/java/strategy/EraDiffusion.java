package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.ArrayList;
import java.util.concurrent.Future;

// Stratégie époque : les afficheurs peuvent recevoir des sous ensembles de valeurs du capteur différents, mais il faut que ces sous-ensembles soient croissant
public class EraDiffusion implements AlgoDiffusion {

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

    }
}
