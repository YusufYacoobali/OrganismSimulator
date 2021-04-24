import java.util.Random;
import java.util.List;
/**
 * class Fish - represents the shared attributes and methods of a fish.
 * Fish objects are mainly used to demonstrate the food competition between owl and bear.
 * Code does not show the fish eating and assumes that fishes are able to move on land.
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Fish extends Prey
{
    private static final int BREEDING_AGE = 1;
    private static final double BREEDING_PROBABILITY = 0.90;
    private static final int MAX_AGE = 80;
    private static final int MAX_BIRTHS = 45;    
    private static final Random rand = Randomizer.getRandom();
    
    private static Conditions badWeather = Conditions.SNOW;

    /**
     * Creates a fish object and sends all parameters to the superclass
     */
    public Fish(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location);
    }
    
    /**
     * Fish can only act when the time is more than 5 and it is not snowing.
     * Checks whether the organism is allowed to act during @param time and @param weather
     * @return True if time is more than 5 and the weather is not snowing(not bad weather).
     */
    public boolean canAct(int time, String weather){
        return (time > 5) && !weather.equals(badWeather.toString());   
    }
    
    /** 
     * Checks if the organism is a fish.
     * @param organism Organism you want to check.
     * @return True if organism in parameter is a fish, false if otherwise.
     */
    public boolean checkSpecies(Object organism){
        return (organism instanceof Fish);
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
     * Creates and returns a newly born fish at @param field and @param loc.
     */
    public Organism newOrganism(Field field, Location loc){
        Organism young = new Fish(false, field, loc);
        return young;
    }
}