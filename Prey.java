import java.util.List;
/**
 * Abstract class Prey - write a description of the class here
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public abstract class Prey extends Animal
{    
    /**
     * Creates an animal that is classified as prey and sends all parameters to the superclass
     */
    public Prey(boolean randomAge, Field field, Location location)
    {
        super(randomAge,field, location);  
    } 
    
    /**
     * This is how all animals who are prey act
     * They age, give birth, get infected and die
     */
    public void act(List<Organism> newPrey, int time, String weather){
        incrementAge();
        if(isAlive() && canAct(time, weather)) {
            infect();
            if(!isInfected()){
                giveBirth(newPrey);  
                // Try to move into a free location.
                Location newLocation = getField().freeAdjacentLocation(getLocation());
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
            } 
            else{
                uninfect();
            }
        }
    }
}