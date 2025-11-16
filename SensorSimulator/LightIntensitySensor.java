import java.time.LocalTime;

public class LightIntensitySensor extends Sensor {
    private double maxLightIntensity;
    private boolean dayNightCycle;
    private double currentIntensity;

    public LightIntensitySensor(String sensorId){
        super(sensorId, "Light Intensity", "lx");
        this.maxLightIntensity = 100000.0;
        this.dayNightCycle = true;
        this.currentIntensity = 20000.0;
    }

    public LightIntensitySensor(String sensorId, double maxIntensity, boolean dayNightCycle){
        super(sensorId, "LightIntensity", "lx");
        this.maxLightIntensity = maxIntensity;
        this.dayNightCycle = dayNightCycle;
        this.currentIntensity = maxIntensity*0.2;

    }
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

private double calculateDayNightIntensity() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        
        // Simulate sun cycle: lowest at midnight, highest at noon
        double sunFactor;
        if (hour >= 6 && hour <= 18) {
            // Daytime: 6 AM to 6 PM
            double timeFromNoon = Math.abs(12 - hour);
            sunFactor = 1.0 - (timeFromNoon / 6.0 * 0.7); // Peak at noon, 30% at 6 AM/PM
        } else {
            // Nigh// Nighttime: very low light
            sunFactor = 0.01; // 1% of max (moonlight/artificial light)
        }
        
        // Add some cloud variation
        double cloudFactor = 0.7 + (random.nextDouble() * 0.3); // 70-100% (clouds reduce light)
        
        return maxLightIntensity * sunFactor * cloudFactor;
    }

}
