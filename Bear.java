import java.util.Random;
import java.util.List;
/**
 * class Bear - represents the shared attributes and methods of a bear.
 * Bears eat fish only.
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 * 
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Bear extends Predator
{
    private static final int BREEDING_AGE = 4;
    private static final double BREEDING_PROBABILITY = 0.74;
    private static final int FISH_FOOD_VALUE = 120;
    private static final int MAX_AGE = 90;
    private static final int MAX_BIRTHS = 25;  

    private static int InitialFoodLevel = 800;   //when initialised this is the maximum foodlevel possible
    private static Conditions badWeather = Conditions.HEAVY_FOG;
    /**
     * Creates a bear object and sends all parameters to the superclass
     */
    public Bear(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, InitialFoodLevel);
    }

    /**
     * Bears can only act when the time is more than 12 and there is no heavy fog.
     * Checks whether the organism is allowed to act during @param time and @param weather
     * @return True if time is more than 12 and the weather is not heavy fog (not bad weather).
     */
    public boolean canAct(int time, String weather){
        return (time > 12) && !weather.equals(badWeather.toString());   //right weather is true when weather is not badWeather
    }

    /** 
     * Checks if the organism is of this class.
     * @param organism Organism you want to check.
     * @return True if organism in parameter is a bear, false if otherwise.
     */
    public boolean checkSpecies(Object organism){
        return (organism instanceof Bear);
    }

    /**
     * If the adjacent block has the animals food then the food is eaten
     * The foodLevel of the animal is eaten and the organism that is food is set to dead
     * The location of the food is returned
     */
    public Location eatFood(Object food, Location where){
        if(food instanceof Fish) {                 
            Fish fish = (Fish) food;                          
            if(fish.isAlive()) { 
                fish.setDead();
                this.updateFoodLevel(FISH_FOOD_VALUE);   
                return where;
            }
        }
        return null;
    }

    /**
     * @return the minimum age required for this species to reproduce
     */
    public int getBreedAge(){
        return BREEDING_AGE;
    }

    /**
     * @return the probability of breeding for this species
     */
    public double getBreedingRate(){
        return BREEDING_PROBABILITY;
    }

    /**
     * @return the maximum age this species can be
     */
    public int getMaxAge(){
        return MAX_AGE;
    }

    /**
     * @return the maximum number of births this species can have
     */
    public int getMaxBirths(){
        return MAX_BIRTHS;
    }

    /**
     * Creates and returns a newly born bear at @param field and @param loc.
     */
    public Organism newOrganism(Field field, Location loc){
        Organism young = new Bear(false, field, loc);
        return young;
    }
}