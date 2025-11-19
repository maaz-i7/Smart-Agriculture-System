package Smart_Agriculture_System.Sensor_Simulation;

public class HumiditySensor extends Sensor {
    // typical humidity to vary around
    private double baseHumidity;
    
    // lowest possible humidity
    private double minHumidity;
    
    // highest possible humidity
    private double maxHumidity;

    // create humidity sensor with default range
    public HumiditySensor(String sensorId){
        super(sensorId, "Humidity", "%");
        this.baseHumidity = 50.0;
        this.minHumidity = 10.0;
        this.maxHumidity = 95.0;
    }

    // create humidity sensor with custom range
    public HumiditySensor(String sensorId, double minHumidity, double maxHumidity){
        super(sensorId, "Humidity", "%");
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.baseHumidity = (minHumidity + maxHumidity)/2;
    }

    // generate random humidity reading
    public double generateReading() {
        double humidity;
        double variation = (random.nextDouble() - 0.5) * 10.0; 
        humidity = baseHumidity + variation;

        if (humidity < minHumidity) humidity = minHumidity;
        if (humidity > maxHumidity) humidity = maxHumidity;

        return humidity;
    }

    // set humidity to minimum value
    public void setHumidityMin() {
        this.baseHumidity = 30.0;
        this.currentValue = 30.0;
    }
}