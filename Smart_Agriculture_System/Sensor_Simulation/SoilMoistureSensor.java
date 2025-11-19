package Smart_Agriculture_System.Sensor_Simulation;

public class SoilMoistureSensor extends Sensor {
    // ideal moisture level
    private double optimalMoisture;
    
    // current soil moisture level
    private double currentMoisture;
    
    // how fast moisture decreases
    private double moistureLossRate;

    // create soil sensor with default values
    public SoilMoistureSensor(String sensorId){
        super(sensorId, "Soil Moisture", "%");
        this.optimalMoisture = 60.0;
        this.currentMoisture = 45.0;
        this.moistureLossRate = 0.5;
    }

    // create soil sensor with custom values
    public SoilMoistureSensor(String sensorId, double initialMoisture, double optimalLevel, double moistureLossRate){
        super(sensorId, "Soil Moisture", "%");
        this.optimalMoisture = optimalLevel;
        this.currentMoisture = initialMoisture;
        this.moistureLossRate = moistureLossRate;
    }
    
    // generate moisture reading with loss
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

    // set soil moisture to maximum
    public void setSoilMoistureMax() {
        this.currentMoisture = 100.0;
        this.currentValue = 100.0;
    }
}