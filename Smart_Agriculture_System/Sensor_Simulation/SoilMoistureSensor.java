package Smart_Agriculture_System.Sensor_Simulation;

public class SoilMoistureSensor extends Sensor {
    private double optimalMoisture;
    private double currentMoisture;
    private double moistureLossRate;
    

    public SoilMoistureSensor(String sensorId){
        super(sensorId, "Soil Moisture", "%");
        this.optimalMoisture = 60.0;
        this.currentMoisture = 45.0;
        this.moistureLossRate = 0.5;
    }


    public SoilMoistureSensor(String sensorId, double initialMoisture, double optimalLevel, double moistureLossRate){
        super(sensorId, "Soil Moisture", "%");
        this.optimalMoisture = optimalLevel;
        this.currentMoisture = initialMoisture;
        this.moistureLossRate = moistureLossRate;

    }
    public double generateReading(){

        currentMoisture -= moistureLossRate;
       double variation = (random.nextDouble() - 0.5) * 6.0;
        double moisture = currentMoisture + variation;

        if(moisture<0)
        moisture = 0;
        if(moisture>100)
        moisture = 100;

        currentMoisture = moisture;

        return moisture;
    }
}