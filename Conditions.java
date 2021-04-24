
/**
 * Enumeration class Conditions - documents the five types of weather shown in the simulation.
 
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public enum Conditions
{
    RAIN("Rain"), CLOUDY("Cloudy"), HEAVY_FOG("Heavy Fog"), SNOW("Snow"), SUNNY("Sunny");
    
    private String weatherString;
    
    /**
     * Initialise with the corresponding weather condition string.
     * @param weatherString The associated weather condition string.
     */
    Conditions(String weatherString)
    {
        this.weatherString = weatherString;
    }
    
    /**
     * @return The weather condition as a string.
     */
    public String toString()
    {
        return weatherString;
    }
}

