package Smart_Agriculture_System.Event_Driven_Automation;
import java.io.BufferedReader;
import java.io.FileReader;
public class Automation {
     public static double MOISTURE_THRESHOLD = 30.0;
     public static double TEMPERATURE_THRESHOLD = 35.0; 
     public static double HUMIDITY_THRESHOLD = 75.0;
     public static double LIGHT_THRESHOLD = 10000.0; 
     public static void main(String[] args) throws Exception {
        // can this be done to run the sensor simulation in parallel?
  /*    Thread sensorThread = new Thread(() -> {
        try {
            Smart_Agriculture_System.Sensor_Simulation.SensorSimulation.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    sensorThread.start();

    Thread.sleep(2000); */
      int hour=0;
     try(BufferedReader reader = new BufferedReader(new FileReader("C:\\C Programming\\Java\\OOPS Project\\Smart-Agriculture-System\\sensor_data.txt"))) {
      String line;
      reader.readLine(); // Skip header line
      Thread.sleep(5000); // does line 1 get printed at 0s? if no then need to change value to 15000 to ensure 5s offset
       while(hour<24)
       {
          while ((line = reader.readLine()) == null) {
                Thread.sleep(500); 
            }   // can we directly do line=reader.readLine() here?
       Analysis.process(line);
        hour++;
        Thread.sleep(10000);
       }
reader.close();
    }
     }
    }

