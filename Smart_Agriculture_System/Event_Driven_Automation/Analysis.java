package Smart_Agriculture_System.Event_Driven_Automation;

public class Analysis {
    public static void process(String line) throws Exception {
         String[] parts = line.split("\\|");
        double currSoil = Double.parseDouble(parts[1].trim());
        double currHumidity = Double.parseDouble(parts[2].trim());
        double currTemp = Double.parseDouble(parts[3].trim());
        double currLight = Double.parseDouble(parts[4].trim());
        Checks.checkSoil(currSoil);
        Checks.checkShade(currTemp,currLight);
        //Checks.checkHumidity(currHumidity); Not to be done automatically?
      }
}
