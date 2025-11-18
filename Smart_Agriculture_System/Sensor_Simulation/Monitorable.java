package Smart_Agriculture_System.Sensor_Simulation;

public interface Monitorable {
    String getSensorType();
    double getCurrentValue();
    boolean isActive();
}
