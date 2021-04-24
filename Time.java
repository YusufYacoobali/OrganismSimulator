
/**
 * Controls and manages everything time related in the simulator
 * It increments the time
 * Time and day can also be reset
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Time
{
    private int time;
    private int day;

    /**
     * Constructor for objects of class Time
     */
    public Time()
    {
        time = 0;
        day = 0;
    }
    
    /**
     * Returns the current time for the system
     */
    public int getTime(){
        return time;
    }
    
    /**
     * Returns the current day for the system
     */
    public int getDay(){
        return day;
    }
    
    /**
     * Increments the time by an hour
     * If 24 hours have passed then day is incremented
     * Weather is also incremented
     */
    public void incrementTime(Weather weather){      
        if (time < 23){
            time++;
        } else {
            day++;
            time = 0;
        }
        
        weather.incrementWeather(time);
    }
    
    /**
     * Resets the time and day variable
     */
    public void reset(){
        time = 0;
        day = 0;
    }
}
