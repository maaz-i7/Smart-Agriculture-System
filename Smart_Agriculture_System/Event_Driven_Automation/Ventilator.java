package Smart_Agriculture_System.Event_Driven_Automation;

import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

public class Ventilator {

    // Tracks whether the ventilator is currently on
    static boolean isVentilatorOn = false;

    // Maximum ventilator runtime
    static int MAX_VENTILATOR_TIME = 4;

    // Reference to the sensor simulation object
    private SensorSimulation simulation;

    // Constructor: receives SensorSimulation instance
    public Ventilator(SensorSimulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Turns the ventilator ON.
     * This lowers humidity through the simulation object.
     */
    public void ventilatorOn() {
        simulation.setHumidityMin();  // Reduce humidity
        isVentilatorOn = true;        // Mark ventilator as active
    }

    /**
     * Starts ventilator for a specific duration.
     */
    public void ventilatorOn(int time) {

        // Cap runtime to maximum allowed limit
        if (time > MAX_VENTILATOR_TIME)
            time = MAX_VENTILATOR_TIME;

        // Run ventilator for the given duration
        while (time > 0) {
            time--;

           simulation.setHumidityMin(); // Reduce humidity

            isVentilatorOn = true;  // Ventilator remains ON
        }
        isVentilatorOn = false;  // Mark ventilator as OFF after completion
    }

    /**
     * Turns the ventilator OFF.
     */
    public void ventilatorOff() {
        isVentilatorOn = false;
    }
}

