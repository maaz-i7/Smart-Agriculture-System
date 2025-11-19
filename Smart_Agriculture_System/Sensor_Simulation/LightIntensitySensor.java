package Smart_Agriculture_System.Sensor_Simulation;

import java.time.LocalTime;

public class LightIntensitySensor extends Sensor {
    // maximum light brightness
    private double maxLightIntensity;
    
    // whether to simulate day night cycle
    private boolean dayNightCycle;
    
    // current light level
    private double currentIntensity;

    // create light sensor with default values
    public LightIntensitySensor(String sensorId){
        super(sensorId, "Light Intensity", "lx");
        this.maxLightIntensity = 100000.0;
        this.dayNightCycle = true;
        this.currentIntensity = 20000.0;
    }

    // create light sensor with custom values
    public LightIntensitySensor(String sensorId, double maxIntensity, boolean dayNightCycle){
        super(sensorId, "LightIntensity", "lx");
        this.maxLightIntensity = maxIntensity;
        this.dayNightCycle = dayNightCycle;
        this.currentIntensity = maxIntensity*0.2;
    }
    
    // generate light intensity reading
    public double generateReading() {
        double lightLevel;
        if(dayNightCycle){
            lightLevel = calculateDayNightIntensity();
        }
        else{
            double variation = (random.nextDouble()-0.5)*0.3;
            lightLevel = currentIntensity *(1+variation);
        }

        if (lightLevel < 0) lightLevel = 0;
        if (lightLevel > maxLightIntensity) lightLevel = maxLightIntensity;

        currentIntensity = lightLevel;

        return Math.round(lightLevel);
    }

    // calculate light based on time
    private double calculateDayNightIntensity() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        
        double sunFactor;
        if (hour >= 6 && hour <= 18) {
            double timeFromNoon = Math.abs(12 - hour);
            sunFactor = 1.0 - (timeFromNoon / 6.0 * 0.7);
        } else {
            sunFactor = 0.01;
        }
        
        double cloudFactor = 0.7 + (random.nextDouble() * 0.3);
        
        return maxLightIntensity * sunFactor * cloudFactor;
    }

    // set light to minimum value
    public void setLightIntensityMin() {
        this.dayNightCycle = false;  
        this.currentIntensity = 5000.0;
        this.currentValue = 5000.0;
    }
}
