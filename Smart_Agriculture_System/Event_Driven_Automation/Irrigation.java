package Smart_Agriculture_System.Event_Driven_Automation;

import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

public class Irrigation {

    // Tracks whether irrigation is currently active
    static boolean isIrrigationOn = false;

    // Maximum allowed irrigation time 
    static int MAX_IRRIGATION_TIME = 3;

    // Reference to the sensor simulation object
    private SensorSimulation simulation;

    // Constructor: Injects the SensorSimulation instance
    public Irrigation(SensorSimulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Starts irrigation immediately with automatic duration.
     * Sets soil moisture to max using the simulation object.
     */
    public void startIrrigation() {
        simulation.setSoilMoistureMax();  // Increase soil moisture
        isIrrigationOn = true;            // Mark irrigation as ON
    }

    /**
     * Starts irrigation for a given time duration.
     * If requested time exceeds MAX_IRRIGATION_TIME, it is capped.
     */
    public void startIrrigation(int time) {

        // Cap time to the maximum allowed limit
        if (time > MAX_IRRIGATION_TIME)
            time = MAX_IRRIGATION_TIME;

        // Run irrigation for the specified duration
        while (time > 0) {
            time--;
            simulation.setSoilMoistureMax();  // Update soil moisture
            isIrrigationOn = true;            // Irrigation active
        }
        isIrrigationOn = false;  // Mark irrigation as OFF after completion
    }

    /**
     * Stops irrigation and marks the system as OFF.
     */
    public void stopIrrigation() {
        isIrrigationOn = false;
        // Additional stop logic can be added here (e.g., motor off)
    }
}

