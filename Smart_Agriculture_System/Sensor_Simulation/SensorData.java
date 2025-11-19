package Smart_Agriculture_System.Sensor_Simulation;

import java.time.LocalDateTime;

public class SensorData{
    // type of sensor
    public String sensorType;
    
    // measured value from sensor
    public double value;
    
    // unit of measurement
    public String unit;
    
    // when reading was taken
    public LocalDateTime timestamp;
    
    // unique sensor identifier
    public String sensorId;

    // create sensor data with all fields
    public SensorData(String sensorType, double value, String unit, LocalDateTime timestamp, String sensorId){
        this.sensorType = sensorType;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
        this.sensorId = sensorId;
    }

    // create empty sensor data
    public SensorData(){

    }

    // print sensor data to console
    public void displayData(){
        System.out.println(sensorType + ": " +value+ " "+ unit + " | Time: " + timestamp+ " | ID: "+sensorId );
    }

    // convert sensor data to string
    @Override //IDE was throwing error so had to add this
    public String toString(){
        return sensorType + ": " + value + " " + unit + 
           " (ID: " + sensorId + ") at " + timestamp;
    }
}