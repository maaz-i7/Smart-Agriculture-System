package Smart_Agriculture_System.Event_Driven_Automation;

import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

public class ShadeSystem {

    // Tracks whether the shade is currently deployed
    static boolean isShadeOn = false;

    // Reference to the SensorSimulation object
    private SensorSimulation simulation;

    // Constructor: Receives the simulation object used for control adjustments
    public ShadeSystem(SensorSimulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Activates the shade system.
     * This reduces temperature and light intensity through the simulation.
     */
    public void shadeOn() {
        simulation.setTemperatureMin();     // Lower the temperature
        simulation.setLightIntensityMin();  // Lower the light level
        isShadeOn = true;                   // Mark shade as active
    }

    /**
     * Deactivates the shade system.
     */
    public void shadeOff() {
        isShadeOn = false;
    }
}

