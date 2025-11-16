import java.time.LocalDateTime;

public class SensorData{
    public String sensorType;
    public double value;
    public String unit;
    public LocalDateTime timestamp;
    public String sensorId;


    public SensorData(String sensorType, double value, String unit, LocalDateTime timestamp, String sensorId){
        this.sensorId=sensorId;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
        this.sensorId = sensorId;
    }

    public SensorData(){

    }

    public void displayData(){
        System.out.println(sensorType + ": " +value+ " "+ unit + " | Time: " + timestamp+ " | ID: "+sensorId );
    }
}