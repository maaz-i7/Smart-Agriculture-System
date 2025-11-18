package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.LightIntensitySensor;
import Smart_Agriculture_System.Sensor_Simulation.TemperatureSensor;
public class ShadeSystem{
    static boolean isShadeOn = false;
    TemperatureSensor tempSensor = new TemperatureSensor("TemperatureSensor1");
     LightIntensitySensor lightSensor = new  LightIntensitySensor("LightIntensitySensor1");
    public void shadeOn() {
        // Code to start shade system
        tempSensor.setTemperatureMin();
        lightSensor.setLightIntensityMin();
    }
    public void shadeOff() {
        // Code to stop shade system
        isShadeOn = false;
    }
}
