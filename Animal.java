import java.util.List;
import java.util.Random;
/**
 * An abstract class representing shared characteristics of animals.
 * All methods are listed abstract first then non-abstract, both alphabetically. 

 * @author David J. Barnes, Michael Kölling, Yusuf Yacoobali and Joyce Chong
 * @version V3
 */
public abstract class Animal extends Organism
{    
    private static final Random rand = Randomizer.getRandom();

    private boolean infected;
    /**
     * Creates an animal and assigns it an infection field
     * Sends the parameters up to the superclass: Organism
     */
    public Animal(boolean randomAge, Field field, Location location)
    {
        super(randomAge, field, location);  
        this.infected = false;
    } 
    
    /**
     * A organism can infect nearby animals by chance if it is infected 
     */
    protected void infect()             
    {
        int infectOthers = rand.nextInt(20);    //random chance to infect nearby animals.
        Field field = getField();
        List<Location> adjacentLocations = getField().adjacentLocations(getLocation());
        for(Location adjacent : adjacentLocations){    
            Object neighbour = field.getObjectAt(adjacent);
            if (neighbour instanceof Animal && rand.nextInt(101) < infectOthers){ //random chance to infect nearby animals.
                Animal animal = (Animal) neighbour;
                animal.infected = true;
            }              
        }
    }
    
    /**
     * Check if the animal is infected or not.
     * @return True if is infected; False if otherwise.
     */
    protected boolean isInfected()
    {
        return infected;
    }
    
    /**
     * Uninfects the animal by chance 
     * which uses the real life scenario: immune system fights off infection
     */
    protected void uninfect()
    {   
        //random chance of recovery
        int recovery = rand.nextInt(20); 
        if(rand.nextInt(80) < recovery){
            infected = false;
        }
    }
   
}