package Smart_Agriculture_System.Event_Driven_Automation;
import Smart_Agriculture_System.Sensor_Simulation.SensorSimulation;
public class Irrigation {
    static boolean isIrrigationOn = false;
    static int MAX_IRRIGATION_TIME = 4; // import from User
    private SensorSimulation simulation;



     public Irrigation(SensorSimulation simulation) {
        this.simulation = simulation;
    }


    public void startIrrigation() {
        // Code to start irrigation
        simulation.setSoilMoistureMax(); // can even setSoilMoisture(10) or something for overloading?
        isIrrigationOn = true;
    }
    public void startIrrigation(int time)
    {
        // how will time increase here be shown during Analysis? like how will User know Irrigation is still on?
        if(time > MAX_IRRIGATION_TIME)
            time = MAX_IRRIGATION_TIME;
        while(time >0)
        {
        time--;
        simulation.setSoilMoistureMax();
        isIrrigationOn = true;
        }
    }
    public void stopIrrigation() {
        isIrrigationOn = false;
        // Code to stop irrigation
    }
}
