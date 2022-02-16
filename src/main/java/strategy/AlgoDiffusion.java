package strategy;

import origin.SensorImpl;

/**
 * Interface that represent strategy diffusions
 */
public interface AlgoDiffusion {

    /**
     * Configure diffusion by set sensor
     * @param sensor to set for current diffusion strategy
     */
    void configure(SensorImpl sensor);

    /**
     * Execute diffusion
     */
    void execute();
}
