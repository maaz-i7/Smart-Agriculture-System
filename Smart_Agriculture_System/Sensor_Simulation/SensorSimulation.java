package Smart_Agriculture_System.Sensor_Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SensorSimulation {
    // list of all sensors
    private List<Sensor> sensors;
    
    // timer for periodic updates
    private Timer timer;
    
    // whether simulation is running
    private boolean isRunning;
    
    // seconds between sensor readings
    private int updateIntervalSeconds;
    
    // whether header has been printed
    private boolean headerPrinted;
    
    // file writer for logging data
    private PrintWriter logWriter;
    
    // create new sensor simulation
    public SensorSimulation() {
        sensors = new ArrayList<>();
        timer = new Timer();
        isRunning = false;
        updateIntervalSeconds = 10;
        headerPrinted = false;
        
        try {
            String filename = "logs/sensor_data.txt";
            logWriter = new PrintWriter(new FileWriter(filename, false));
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
            logWriter = null;
        }
    }
    
    // create all four sensor instances
    public void initializeSensors() {
        TemperatureSensor tempSensor = new TemperatureSensor("TEMP_001");
        HumiditySensor humiditySensor = new HumiditySensor("HUM_001");
        SoilMoistureSensor soilSensor = new SoilMoistureSensor("SOIL_001");
        LightIntensitySensor lightSensor = new LightIntensitySensor("LIGHT_001");
        
        sensors.add(tempSensor);
        sensors.add(humiditySensor);
        sensors.add(soilSensor);
        sensors.add(lightSensor);
    }

    // set all soil sensors to max
    public void setSoilMoistureMax() {
        for (Sensor sensor : sensors) {
            if (sensor instanceof SoilMoistureSensor) {
                ((SoilMoistureSensor) sensor).setSoilMoistureMax();
            }
        }
    }

    // set all light sensors to min
    public void setLightIntensityMin() {
        for (Sensor sensor : sensors) {
            if (sensor instanceof LightIntensitySensor) {
                ((LightIntensitySensor) sensor).setLightIntensityMin();
            }
        }
    }

    // set all temp sensors to min
    public void setTemperatureMin() {
        for (Sensor sensor : sensors) {
            if (sensor instanceof TemperatureSensor) {
                ((TemperatureSensor) sensor).setTemperatureMin();
            }
        }
    }

    // set all temp sensors to max
    public void setTemperatureMax() {
        for (Sensor sensor : sensors) {
            if (sensor instanceof TemperatureSensor) {
                ((TemperatureSensor) sensor).setTemperatureMax();
            }
        }
    }

    // set all humidity sensors to min
    public void setHumidityMin() {
        for (Sensor sensor : sensors) {
            if (sensor instanceof HumiditySensor) {
                ((HumiditySensor) sensor).setHumidityMin();
            }
        }
    }
    
    // start the sensor simulation timer
    public void startSimulation() {
        if (isRunning) {
            return;
        }
        
        isRunning = true;
        
        if (!headerPrinted) {
            String header = "Timestamp | Soil_Moisture(%) | Humidity(%) | Temperature(°C) | Light(%)";
            System.out.println(header);
            
            if (logWriter != null) {
                logWriter.println(header);
                logWriter.flush();
            }
            
            headerPrinted = true;
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateAllSensors();
            }
        }, 0, updateIntervalSeconds * 1000); 
    }
    
    // poll all sensors and log data
    private void updateAllSensors() {
        SensorData tempReading = null;
        SensorData humidityReading = null;
        SensorData soilReading = null;
        SensorData lightReading = null;
        String timestamp = "";
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        
        for (Sensor sensor : sensors) {
            if (sensor.isActive()) {
                SensorData reading = sensor.getCurrentReading();
                if (reading != null) {
                    timestamp = reading.timestamp.format(timeFormatter);
                    
                    if (sensor instanceof TemperatureSensor) {
                        tempReading = reading;
                    } else if (sensor instanceof HumiditySensor) {
                        humidityReading = reading;
                    } else if (sensor instanceof SoilMoistureSensor) {
                        soilReading = reading;
                    } else if (sensor instanceof LightIntensitySensor) {
                        lightReading = reading;
                    }
                }
            }
        }
        
        String dataLine = String.format("%s | %.2f | %.2f | %.2f | %.2f",
            timestamp,
            soilReading != null ? soilReading.value : 0.0,
            humidityReading != null ? humidityReading.value : 0.0,
            tempReading != null ? tempReading.value : 0.0,
            lightReading != null ? lightReading.value : 0.0
        );
        
        System.out.println(dataLine);
        
        if (logWriter != null) {
            logWriter.println(dataLine);
            logWriter.flush(); 
        }
        
        if (tempReading != null) checkTemperatureAlert(tempReading);
        if (soilReading != null) checkSoilAlert(soilReading);
        if (humidityReading != null) checkHumidityAlert(humidityReading);
        if (lightReading != null) checkLightAlert(lightReading);
    }
    
    // check if temp is too high
    private void checkTemperatureAlert(SensorData reading) {
        if (reading.value > 55) {
            System.out.println("ALERT: High temperature detected!");
        }
    }
    
    // check if soil is too dry
    private void checkSoilAlert(SensorData reading) {
        if (reading.value < 60) {
            System.out.println("ALERT: Soil moisture very low - Irrigation needed!");
        }
    }
    
    // check if humidity is too high
    private void checkHumidityAlert(SensorData reading) {
        if (reading.value > 80) {
            System.out.println("ALERT: Very high humidity detected!");
        }
    }
    
    // check if light is too bright
    private void checkLightAlert(SensorData reading) {
        if (reading.value > 60000) {
            System.out.println("ALERT: High light conditions!");
        }
    }
    
    // stop the simulation timer
    public void stopSimulation() {
        if (!isRunning) {
            return;
        }
        
        timer.cancel();
        timer = new Timer(); 
        isRunning = false;
        
        if (logWriter != null) {
            logWriter.close();
        }
    }
    
    // main method to run simulation
    public static void main(String[] args) {
        SensorSimulation simulation = new SensorSimulation();
        
        simulation.initializeSensors();
        simulation.startSimulation();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (simulation.logWriter != null) {
                simulation.logWriter.close();
            }
        }));
    }
}
