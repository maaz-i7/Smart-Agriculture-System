package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;

public class ShadeSystem{
    static boolean isShadeOn = false;
    private SensorSimulation simulation;

      public ShadeSystem(SensorSimulation simulation) {
        this.simulation = simulation;
    }
    public void shadeOn() {
        // Code to start shade system
        simulation.setTemperatureMin();
        simulation.setLightIntensityMin();
    }
    public void shadeOff() {
        // Code to stop shade system
        isShadeOn = false;
    }
}
