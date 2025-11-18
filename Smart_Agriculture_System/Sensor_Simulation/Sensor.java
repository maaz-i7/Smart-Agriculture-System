package Smart_Agriculture_System.Sensor_Simulation;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class Sensor implements Monitorable, Controllable{
    protected String sensorId;
    protected String sensorType;
    protected String unit;
    protected double currentValue;
    protected Random random;
    protected boolean isActive;


public Sensor(String sensorId, String sensorType, String unit){
    this.sensorId = sensorId;
    this.sensorType = sensorType;
    this.unit = unit;
    this.random = new Random();
    this.isActive = true;
    this.currentValue = 0.0;
    
}

public abstract double generateReading();

public SensorData  getCurrentReading(){
    if(isActive){
        currentValue = generateReading();
        return new SensorData(sensorType, currentValue, unit, LocalDateTime.now(), sensorId);
       
    }
    return null;
}

public String getSensorId(){
    return sensorId;
}

public String getUnit(){
    return unit;
}

public double getCurrentValue(){
    return currentValue;
}

public boolean isActive(){
    return isActive;
}

public void setActive(boolean active){
    this.isActive = active;
}

public String getSensorType(){
    return sensorType;
}

}