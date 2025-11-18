public class TemperatureSensor extends Sensor{

    private double baseTemperature;
    private double minTemp;
    private double maxTemp;


    public TemperatureSensor(String sensorId){
        super(sensorId, "Temperature", "Celsius");
        this.baseTemperature = 22.0;

        this.minTemp = -5;
        this.maxTemp = 45;
    }

    public TemperatureSensor(String sensorId, double maxTemp, double minTemp){
        super(sensorId, "Temperature", "C");
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.baseTemperature =(minTemp+maxTemp)/2;

    }

    public double generateReading(){

        double variation = (random.nextDouble() - 0.5)*4.0;
        double temperature = baseTemperature + variation;

        if(temperature< minTemp) temperature = minTemp;
        if(temperature>maxTemp) temperature = maxTemp;


        return temperature;
}

}