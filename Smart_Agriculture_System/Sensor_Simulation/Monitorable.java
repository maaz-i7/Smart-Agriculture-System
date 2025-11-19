package Smart_Agriculture_System.Sensor_Simulation;

public interface Monitorable {
    // get type of sensor
    String getSensorType();
    
    // get current sensor value
    double getCurrentValue();
    
    // check if sensor is on
    boolean isActive();
}
