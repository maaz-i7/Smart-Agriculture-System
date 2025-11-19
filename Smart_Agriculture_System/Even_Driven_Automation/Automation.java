package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

import java.io.BufferedReader;
import java.io.FileReader;

public class Automation {
    public static double MOISTURE_THRESHOLD = 60.0;
    public static double TEMPERATURE_THRESHOLD = 55.0;
    public static double HUMIDITY_THRESHOLD = 10.0;
    public static double LIGHT_THRESHOLD = 60000.0;

    public static void main(String[] args) throws Exception {

        // Start sensor simulation in parallel
        SensorSimulation simulation = new SensorSimulation();
        Analysis.simulation = simulation; // Make it available to Analysis/Checks
        Thread sensorThread = new Thread(() -> {
            simulation.initializeSensors();
            simulation.startSimulation();
        });
        sensorThread.start();

        Thread.sleep(2000); // Give sensors time to start

        int hour = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "Smart_Agriculture_System\\sensor_data.txt"))) {
            String line;
            reader.readLine(); // Skip header line
            Thread.sleep(5000); // does line 1 get printed at 0s? if no then need to change value to 15000 to
                                // ensure 5s offset
            while (hour < 24) {
                while ((line = reader.readLine()) == null) {
                    Thread.sleep(500);
                } // can we directly do line=reader.readLine() here?
                Analysis.process(line);
                hour++;
                Thread.sleep(10000);
            }
            reader.close();
        }
    }

    public static void start() throws Exception {

        int hour = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\maazk\\Downloads\\Smart-Agriculture-System-main\\Smart-Agriculture-System-main\\Smart_Agriculture_System\\sensor_data.txt"))) {
            String line;
            reader.readLine(); // Skip header line
            Thread.sleep(2000); // does line 1 get printed at 0s? if no then need to change value to 15000 to
                                // ensure 5s offset
            while (hour < 24) {
                while ((line = reader.readLine()) == null) {
                    Thread.sleep(500);
                } // can we directly do line=reader.readLine() here?
                Analysis.process(line);
                hour++;
                Thread.sleep(5000);
            }
            reader.close();
        }
    }
}
