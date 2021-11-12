package strategy;

import origin.SensorImpl;

public interface AlgoDiffusion {
    //Settings

    void configure(SensorImpl capteur);

    void execute();
}
