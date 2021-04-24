import java.util.Random;
import java.util.List;
/**
 * class Panda - represents the sahred attributes and methods of a panda
 * Pandas eat bamboo only.
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public class Panda extends Predator
{
    private static final int BAMBOO_FOOD_VALUE = 80;
    private static final int BREEDING_AGE = 1;
    private static final double BREEDING_PROBABILITY = 0.65;
    private static final int MAX_AGE = 150;
    private static final int MAX_BIRTHS = 28;  
    
    private static int InitialFoodLevel = 600; 
    private static Conditions badWeather = Conditions.SNOW;
  
    /**
     * Creates a panda object and sends all parameters to the superclass
     */
    public Panda(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location, InitialFoodLevel);
    }
    
    /**
     * Pandas can only act when the time is more than 12 and it is not snowing.
     * Checks whether the organism is allowed to act during @param time and @param weather
     * @return True if time is more than 12 and it is not snowing(not bad weather).
     * 
     */
    public boolean canAct(int time, String weather){
        return (time > 12) && !weather.equals(badWeather.toString());   //right weather is true when weather is not badWeather
    }
    
    /** 
     * Checks if the organism is a panda.
     * @param organism Organism you want to check.
     * @return True if organism in parameter is a panda, false if otherwise.
     */
    public boolean checkSpecies(Object organism){
        return (organism instanceof Panda);
    }
    
    /**
     * If the adjacent block has the animals food then the food is eaten
     * The foodLevel of the animal is eaten and the organism that is food is set to dead
     * The location of the food is returned
     */
    public Location eatFood(Object food, Location where){
        if(food instanceof Bamboo) {                 
            Bamboo bamboo = (Bamboo) food;                           
            if(bamboo.isAlive()) { 
                bamboo.setDead();
                this.updateFoodLevel(BAMBOO_FOOD_VALUE);    
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
     * Creates and returns a newly born panda at @param field and @param loc.
     */
    public Organism newOrganism(Field field, Location loc){
        Organism young = new Panda(false, field, loc);
        return young;
    }
}