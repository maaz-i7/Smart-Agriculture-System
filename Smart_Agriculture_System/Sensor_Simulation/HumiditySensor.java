package Smart_Agriculture_System.Sensor_Simulation;

public class HumiditySensor extends Sensor {
    private double baseHumidity;
    private double minHumidity;
    private double maxHumidity;



    public HumiditySensor(String sensorId){

        super(sensorId, "Humidity", "%");

        this.baseHumidity = 50.0;
        this.minHumidity = 10.0;
        this.maxHumidity = 95.0;
    }


     public HumiditySensor(String sensorId, double minHumidity, double maxHumidity){

        super(sensorId, "Humidity", "%");

        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.baseHumidity = (minHumidity + maxHumidity)/2;

        
    }

     public double generateReading() {
        double humidity;

        double variation = (random.nextDouble() - 0.5) * 10.0; 
        humidity = baseHumidity + variation;

        if (humidity < minHumidity) humidity = minHumidity;
        if (humidity > maxHumidity) humidity = maxHumidity;

        return humidity;
    
    }

    /**
     * Sets the humidity to a minimum value (10%).
     * This represents very low humidity conditions.
     */
    public void setHumidityMin() {
        this.baseHumidity = 30.0;
        this.currentValue = 30.0;
    }

}