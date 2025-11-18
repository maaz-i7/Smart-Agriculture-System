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
    private List<Sensor> sensors;
    private Timer timer;
    private boolean isRunning;
    private int updateIntervalSeconds;
    private boolean headerPrinted;
    private PrintWriter logWriter;
    
    public SensorSimulation() {
        sensors = new ArrayList<>();
        timer = new Timer();
        isRunning = false;
        updateIntervalSeconds = 10;
        headerPrinted = false;
        
        try {
            String filename = "sensor_log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
            logWriter = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
            logWriter = null;
        }
    }
    
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
    
    private void checkTemperatureAlert(SensorData reading) {
        if (reading.value > 35) {
            System.out.println("ALERT: High temperature detected!");
        } else if (reading.value < 5) {
            System.out.println("ALERT: Low temperature detected!");
        }
    }
    
    private void checkSoilAlert(SensorData reading) {
        if (reading.value < 20) {
            System.out.println("ALERT: Soil moisture very low - Irrigation needed!");
        }
    }
    
    private void checkHumidityAlert(SensorData reading) {
        if (reading.value > 80) {
            System.out.println("ALERT: Very high humidity detected!");
        } else if (reading.value < 25) {
            System.out.println("ALERT: Very low humidity detected!");
        }
    }
    
    private void checkLightAlert(SensorData reading) {
        if (reading.value < 10000) {
            System.out.println("ALERT: Low light conditions!");
        }
    }
    
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
