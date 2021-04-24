import java.util.Random;
import java.util.List;
/**
 * class Bamboo - represents the shared attributes and methods of a bamboo.
 * All methods are listed abstract first then non-abstract, both alphabetically. 

 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Bamboo extends Plant
{
    private static final int BREEDING_AGE = 1;
    private static final int MAX_AGE = 150;
    private static final double BREEDING_PROBABILITY = 0.72;
    private static final int MAX_BIRTHS = 5;    
    private static final Random rand = Randomizer.getRandom();
    private static Conditions badWeather = Conditions.RAIN;

    /**
     * Creates a bamboo plant object and sends all parameters to the superclass
     */
    public Bamboo(boolean randomAge, Field field, Location location)
    {
        super(randomAge,field, location);
    }
    
    /**
     * Bamboos can only act when the time is more than 6 and it is not raining.
     * Checks whether the organism is allowed to act during @param time and @param weather
     * @return True if time is more than 6 and the weather is not raining(not bad weather).
     */
    public boolean canAct(int time, String weather){
        return (time > 6) && !weather.equals(badWeather.toString());   //bamboo only grows when the sun is out
    }

    /** 
     * Check if the organism is a bamboo.
     * @param organism Organism you want to check.
     * @return True if organism in parameter is a bamboo, false if otherwise.
     */
    public boolean checkSpecies(Object organism){
        return (organism instanceof Bamboo);
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
     * Creates and returns a newly born bamboo at @param field and @param loc.
     */
    public Organism newOrganism(Field field, Location loc){
        Organism young = new Bamboo(false, field, loc);
        return young;
    }    
}