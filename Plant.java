import java.util.List;
/**
 * Abstract class Plant - respresents plant entities
 * Plants do not move therefore when they reproduce which results in a hotspot of greenery.
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public abstract class Plant extends Organism
{
    /**
     * Creates a plant object and sends all parameters to the superclass
     */
    public Plant(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location);  
    } 

    /**
     * This is how all plant organisms act.
     * They age, give birth with nearby plants, do not move, and can die.
     * @param newPlants A list to return of newly born plants
     *        time      current time
     *        weather   current weather
     */
    public void act(List<Organism> newPlants, int time, String weather){
        incrementAge();
        if(isAlive() && canAct(time, weather)) {
            giveBirth(newPlants);            
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if (newLocation == null){
                setDead();
            }
        }
    }
}