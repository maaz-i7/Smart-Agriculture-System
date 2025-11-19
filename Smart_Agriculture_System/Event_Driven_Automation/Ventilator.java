package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;
public class Ventilator {
    static boolean isVentilatorOn = false;
    private SensorSimulation simulation;
    
    public Ventilator(SensorSimulation simulation) {
        this.simulation = simulation;
    }
    
    public void ventilatorOn() {
        // Code to start ventilator
        simulation.setHumidityMin();
        isVentilatorOn = true;
    }
    public void ventilatorOff() {
        // Code to stop ventilator
        isVentilatorOn = false;
    }
}
