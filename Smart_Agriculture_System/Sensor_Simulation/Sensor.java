package Smart_Agriculture_System.Sensor_Simulation;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class Sensor implements Monitorable, Controllable{
    // unique identifier for this sensor
    protected String sensorId;
    
    // type of sensor
    protected String sensorType;
    
    // measurement unit
    protected String unit;
    
    // latest reading value
    protected double currentValue;
    
    // random number generator for readings
    protected Random random;
    
    // whether sensor is turned on
    protected boolean isActive;

    // create new sensor with id type and unit
    public Sensor(String sensorId, String sensorType, String unit){
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.unit = unit;
        this.random = new Random();
        this.isActive = true;
        this.currentValue = 0.0;
    }

    // subclasses must implement reading logic
    public abstract double generateReading();

    // get current reading from sensor
    public SensorData getCurrentReading(){
        if(isActive){
            currentValue = generateReading();
            return new SensorData(sensorType, currentValue, unit, LocalDateTime.now(), sensorId);
        }
        return null;
    }

    // get sensor id
    public String getSensorId(){
        return sensorId;
    }

    // get measurement unit
    public String getUnit(){
        return unit;
    }

    // get last reading value
    public double getCurrentValue(){
        return currentValue;
    }

    // check if sensor is on
    public boolean isActive(){
        return isActive;
    }

    // turn sensor on or off
    public void setActive(boolean active){
        this.isActive = active;
    }

    // get sensor type
    public String getSensorType(){
        return sensorType;
    }
}