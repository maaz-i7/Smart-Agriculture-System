package Smart_Agriculture_System.Sensor_Simulation;

public class TemperatureSensor extends Sensor{
    // typical temperature to vary around
    private double baseTemperature;
    
    // lowest possible temperature
    private double minTemp;
    
    // highest possible temperature
    private double maxTemp;

    // create temperature sensor with default range
    public TemperatureSensor(String sensorId){
        super(sensorId, "Temperature", "Celsius");
        this.baseTemperature = 22.0;
        this.minTemp = -5;
        this.maxTemp = 45;
    }

    // create temperature sensor with custom range
    public TemperatureSensor(String sensorId, double maxTemp, double minTemp){
        super(sensorId, "Temperature", "C");
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.baseTemperature =(minTemp+maxTemp)/2;
    }

    // generate random temperature reading
    public double generateReading(){
        double variation = (random.nextDouble() - 0.5)*4.0;
        double temperature = baseTemperature + variation;

        if(temperature< minTemp) temperature = minTemp;
        if(temperature>maxTemp) temperature = maxTemp;

        return temperature;
    }

    // set temperature to minimum comfort level
    public void setTemperatureMin() {
        this.baseTemperature = 22.0;
        this.currentValue = 22.0;
    }

    // set temperature to maximum heat level
    public void setTemperatureMax() {
        this.baseTemperature = 50.0;
        this.currentValue = 50.0;
    }
}