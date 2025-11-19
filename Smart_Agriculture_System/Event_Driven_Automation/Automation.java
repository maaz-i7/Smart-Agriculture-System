package Smart_Agriculture_System.Event_Driven_Automation;

import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;
import java.io.BufferedReader;
import java.io.FileReader;

public class Automation {

    // Default threshold values for triggering different automated actions , can be changed by User
    public static double MOISTURE_THRESHOLD = 45.0;
    public static double TEMPERATURE_THRESHOLD = 45.0;
    public static double HUMIDITY_THRESHOLD = 60.0;
    public static double LIGHT_THRESHOLD = 60000.0;

    public static void main(String[] args) throws Exception {

        // Ensure logs directory exists
        new java.io.File("logs").mkdirs();

        // Clear previous run's log files
        new java.io.File("logs/warning.txt").delete();
        new java.io.File("logs/automaticActions.txt").delete();

        // Create sensor simulation instance
        SensorSimulation simulation = new SensorSimulation();

        // Make simulation accessible from Analysis and Checks
        Analysis.simulation = simulation;

        // Start sensor simulation in a separate thread
        Thread sensorThread = new Thread(() -> {
            simulation.initializeSensors(); 
            simulation.startSimulation();    
        });
        sensorThread.start();

        // Allow sensors time to initialize before reading data
        Thread.sleep(2000);

        int hour = 0;

        // Read sensor data from file and process line-by-line
        try (BufferedReader reader = new BufferedReader(new FileReader("logs/sensor_data.txt"))) {

            reader.readLine();  // Skip header line in the file
            Thread.sleep(5000); // Ensure a 5-second delay before first processing

            while (hour < 1000) {

                String line;

                // If end of file is reached, wait until more data arrives
                while ((line = reader.readLine()) == null) {
                    Thread.sleep(500);
                }

                // Send the line to Analysis to perform threshold checks
                Analysis.process(line);
                hour++;  

                Thread.sleep(10000);  // Wait 10 seconds before reading next line
            }
        }
    }
}
