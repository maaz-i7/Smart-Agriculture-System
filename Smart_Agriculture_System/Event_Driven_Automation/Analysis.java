package Smart_Agriculture_System.Event_Driven_Automation;

import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

public class Analysis {

    // Reference to the sensor simulation object
    public static SensorSimulation simulation;

    /**
     * Processes a single line of sensor data in the format:
     * soil | humidity | temperature | light
     *
     * Splits the string, extracts values, prints them,
     * and passes them to the appropriate checking methods.
     */
    public static void process(String line) throws Exception {

        // Split incoming sensor line using "|"
        String[] parts = line.split("\\|");

        // Parse individual sensor readings
        double currSoil = Double.parseDouble(parts[1].trim());
        double currHumidity = Double.parseDouble(parts[2].trim());
        double currTemp = Double.parseDouble(parts[3].trim());
        double currLight = Double.parseDouble(parts[4].trim());

        // Print what is being read (for monitoring/debugging)
        System.out.println("Read " + currSoil + " " + currHumidity + " "
                           + currTemp + " " + currLight);

        // Soil moisture handling (may trigger automatic irrigation)
        Checks.checkSoil(currSoil);

        // Shade handling (may trigger automatic shade deployment)
        Checks.checkShade(currTemp, currLight);

        // Humidity handling
        // Returns true if humidity exceeds threshold — NO auto action taken.
        Checks.checkHumidity(currHumidity);
    }
}

