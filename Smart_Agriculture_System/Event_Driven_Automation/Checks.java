package Smart_Agriculture_System.Event_Driven_Automation;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Checks {

    /**
     * Checks soil moisture level.
     * If below threshold → logs warning, logs automatic action,
     * and starts irrigation system.
     */
    public static void checkSoil(double soilMoisture) throws Exception {
        if (soilMoisture < Automation.MOISTURE_THRESHOLD) {

            // Log the warning message to logs/warning.txt. also pleae check if this works on Windows cuase the file path is meant for macos
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/warning.txt", true))) {
                writer.write("\nWARNING: Soil moisture is less than " + Automation.MOISTURE_THRESHOLD);
            }

            // Log the automatic action taken
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/automaticActions.txt", true))) {
                writer.write("\nACTION: Irrigation started automatically");
            }

            // Trigger irrigation mechanism
            Irrigation irrigation = new Irrigation(Analysis.simulation);
            irrigation.startIrrigation();
        }
    }

    /**
     * Checks if either temperature or light exceeds their respective thresholds.
     * If yes → logs the warning, logs shade deployment, and activates shade system.
     */
    public static void checkShade(double temperature, double light) throws Exception {
        if (temperature > Automation.TEMPERATURE_THRESHOLD || light > Automation.LIGHT_THRESHOLD) {

            // Log appropriate warning
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/warning.txt", true))) {
                if (temperature > Automation.TEMPERATURE_THRESHOLD)
                    writer.write("\nWARNING: Temperature is more than " + Automation.TEMPERATURE_THRESHOLD);
                else
                    writer.write("\nWARNING: Light intensity is more than " + Automation.LIGHT_THRESHOLD);
            }

            // Log automatic shade action
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/automaticActions.txt", true))) {
                writer.write("\nACTION: Shade deployed automatically");
            }

            // Activate the shade system
            ShadeSystem shade = new ShadeSystem(Analysis.simulation);
            shade.shadeOn();
        }
    }
    /**
     * Checks humidity level.
     * If above threshold → logs warning and returns true.
     * No automatic action is taken; ventilator is triggered manually by the user.
     */
    public static boolean checkHumidity(double humidity) throws Exception {
        if (humidity > Automation.HUMIDITY_THRESHOLD) {

            // Log humidity warning
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("logs/warning.txt", true))) {
                writer.write("\nWARNING: Humidity is more than " + Automation.HUMIDITY_THRESHOLD);
            }

            return true;  // User handles ventilator manually
        }

        return false;
    }
}
