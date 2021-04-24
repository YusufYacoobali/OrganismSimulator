import java.util.List;
import java.util.Iterator;
import java.util.Random;
/**
 * Abstract class Predator - represents shared methods and attributes of a predator. 
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public abstract class Predator extends Animal
{
    private static final Random rand = Randomizer.getRandom();

    private int foodLevel;   
    /**
     * Creates an animal that is a predator and sends all but one parameters to the superclass
     * Here the foodLevel is assigned depending on the predator species own individuality.
     */
    public Predator(boolean randomAge, Field field, Location location, int foodLevel)
    {
        super(randomAge, field, location); 
        this.foodLevel = rand.nextInt(foodLevel); 
    } 

    //a predator eats the @param food at @param where the food was found.
    protected abstract Location eatFood(Object food, Location where);
    
     /**
     * This is how all animals who are predators act
     * They age, get hungry and hunt, give birth, get infected and die
     *  @param newPredators A list to return of newly born predators
     *         time         current time
     *         weather      current weather
     */
    public void act(List<Organism> newPredators, int time, String weather){
        incrementAge();
        incrementHunger();

        if(isAlive() && canAct(time, weather)) {                    
            infect();
            if(!isInfected()){
                giveBirth(newPredators);    

                // Move towards a source of food if found.
                Location newLocation = findFood();
                if(newLocation == null) { 
                    // No food found - try to move to a free location.
                    newLocation = getField().freeAdjacentLocation(getLocation());
                }
                // See if it was possible to move.
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
            }
            else {
                uninfect();
            }
        }
    } 
    
    /**
     * Look for prey adjacent to the current location.
     * Only the first alive prey is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood(){
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object food = field.getObjectAt(where);
            eatFood(food, where);
        }
        return null;
    }

    /**
     * Makes this predator hungry, which could result in its death.
     */
    protected void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Increments the foodLevel of the animal 
     * @param FOOD_VALUE food value of prey
     */
    protected void updateFoodLevel(int FOOD_VALUE){       
        foodLevel += FOOD_VALUE;
    }
}