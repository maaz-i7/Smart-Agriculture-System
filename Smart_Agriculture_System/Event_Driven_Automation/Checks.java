package Smart_Agriculture_System.Event_Driven_Automation;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Checks {
    public static void checkSoil(double soilMoisture) throws Exception {
            if (soilMoisture < Automation.MOISTURE_THRESHOLD) {
               try( BufferedWriter writer = new BufferedWriter(new FileWriter("warning.txt", true))) {
            writer.write("\nWARNING:Soil moisture is less than " + Automation.MOISTURE_THRESHOLD);
            writer.close();
               }  
                try( BufferedWriter writer = new BufferedWriter(new FileWriter("automaticActions.txt", true))) {
            writer.write("\nACTION: Irrigation started automatically");
            writer.close();
               }    
                Irrigation irrigation = new Irrigation(Analysis.simulation);
                irrigation.startIrrigation();
            } 
        }

        public static void checkShade(double temperature, double light) throws Exception {
            if (temperature > Automation.TEMPERATURE_THRESHOLD || light > Automation.LIGHT_THRESHOLD) {
               try( BufferedWriter writer = new BufferedWriter(new FileWriter("warning.txt", true))) {
                if(temperature > Automation.TEMPERATURE_THRESHOLD)
            writer.write("\nWARNING:Temperature is more than " + Automation.TEMPERATURE_THRESHOLD);
            else
            writer.write("\nWARNING:Light intensity is more than " + Automation.LIGHT_THRESHOLD);
               }  
                try( BufferedWriter writer = new BufferedWriter(new FileWriter("automaticActions.txt", true))) {
            writer.write("\nACTION: Shade deployed automatically");
            writer.close();
               }    
                ShadeSystem shade = new ShadeSystem(Analysis.simulation);
                shade.shadeOn();
            } 
        }

        public static boolean checkHumidity(double humidity) throws Exception {
            if (humidity > Automation.HUMIDITY_THRESHOLD) {
               try( BufferedWriter writer = new BufferedWriter(new FileWriter("warning.txt", true))) {
            writer.write("\nWARNING:Humidity is more than " + Automation.HUMIDITY_THRESHOLD);
            writer.close();
               }  
               return true;
              // Farmer calls ventilator manually
            } 
            return false;
        }
    }
