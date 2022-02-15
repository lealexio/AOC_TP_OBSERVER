package strategy;

import middle.Channel;
import origin.SensorImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

// Stratégie diffusion : les afficheurs reçoivent le même sous-ensemble de valeurs du capteur
public class SequentialDiffusion implements AlgoDiffusion {

    private SensorImpl capteur;

    @Override
    public void configure(SensorImpl capteur) {
        this.capteur = capteur;
    }

    @Override
    public void execute() {
        Map<Future<?>, Channel> futures = new HashMap<>();

        for (Channel c : this.capteur.channels) {
            futures.put(c.update(), c);
        }

        // Nb received futures
        int received = 1;
        // Loop to get futures
        while (received < futures.size()) {
            for (Map.Entry<Future<?>, Channel> entry : futures.entrySet()) {
                Future<?> f = entry.getKey();
                Channel c = entry.getValue();
                // When a future is done
                if (f.isDone()) {
                    // Get value of display on done future
                    int best_value = c.getDisplay().getValue();
                    // For every displays, if current best value is > than current
                    // Set new display value as current best_value
                    for (Channel tmp_c : futures.values()) {
                        if (best_value > tmp_c.getDisplay().getValue()) {
                            tmp_c.getDisplay().setValue(best_value);
                        }
                    }
                    received++;
                }
            }
        }


    }
}
