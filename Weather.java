import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Controls everything to do with the weather in the simulation from changing the weather to resetting 
 * All methods are arranged alphabetically.
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Weather
{
    private boolean rain;
    private boolean fog;
    private boolean snow;
    private boolean cloudy;
    private HashMap<Conditions,Boolean> conditions; 
    private Conditions weatherEnum;
    private static final Random rand = Randomizer.getRandom();
    /**
     * Sets the weather conditions up with the help of the enum Conditions class
     */
    public Weather()
    {
        createWeather();
    }
    
    /**
     * This method changes the weather by chance which is a little closer to reality
     */
    public void changeWeather(){               
        int changeProbability;

        for (HashMap.Entry<Conditions,Boolean> entry : conditions.entrySet()) {
            Conditions key = entry.getKey();
            boolean value = entry.getValue();                
            changeProbability = rand.nextInt(50);
            if (rand.nextInt(101) < changeProbability){
                value = !value;
                conditions.replace(key,value);                    
            }
        }
    }
    
    /**
     * Used to initialise the entirety of weather used in the simulator
     * Creates a hashmap whichs stores all the weather conditions which are initalised.
     */
    private void createWeather(){
        conditions = new HashMap<>();
        rain = false;
        fog = false;
        snow = false;
        cloudy = false;
        conditions.put(weatherEnum.RAIN, rain);
        conditions.put(weatherEnum.HEAVY_FOG, fog);
        conditions.put(weatherEnum.SNOW, snow);
        conditions.put(weatherEnum.CLOUDY, cloudy);
    }

    /**
     * Returns the current weather condition
     * There may be multiple conditions which are true 
     * but only one is selected, hashmap has no order so it is randomly selected
     */
    public String getWeather(){        
        String weather = "";
        for (HashMap.Entry<Conditions,Boolean> entry : conditions.entrySet()) {     
            String key = entry.getKey().toString();
            boolean value = entry.getValue();
            if(value){
                weather = weather + key;
                break;
            }
        }
        if (!weather.equals("")){
            return weather;
        } else {
            return weatherEnum.SUNNY.toString();
        }
    }
    
    /**
     *Change the weather every 6 hours in the simulator
     */
    public void incrementWeather(int time){
        if (time % 6 == 0){
            changeWeather();
        }
    }
    
    /**
     * Resets all weather conditions to false in the hashmap
     * @return the simulation back to sunny weather
     */
    public String reset(){
        conditions.replaceAll((String,Boolean) -> false);
        return weatherEnum.SUNNY.toString();
    }
}       