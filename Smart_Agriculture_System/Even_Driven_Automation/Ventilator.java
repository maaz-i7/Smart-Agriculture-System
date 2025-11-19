package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.HumiditySensor;
public class Ventilator {
    static boolean isVentilatorOn = false;
    HumiditySensor humiditySensor = new HumiditySensor("HumiditySensor1");
    public void ventilatorOn() {
        // Code to start ventilator
      //  humiditySensor.setHumidityMin();function not present
        isVentilatorOn = true;
    }
    public void ventilatorOff() {
        // Code to stop ventilator
        isVentilatorOn = false;
    }
}
