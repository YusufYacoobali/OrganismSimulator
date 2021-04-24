import java.util.List;
import java.util.Random;
/**
 * Abstract class Organism - represents shared methods and attributes of organisms
 * such as if they are alive, how old they are and what gender they are
 * This class also contains lots of abstract methods that the subclasses will use
 * All methods are listed abstract first then non-abstract, both alphabetically. 
 *
 * @author Yusuf Yacoobali and Joyce Chong
 * @version V1
 */
public abstract class Organism
{
    private static final Random rand = Randomizer.getRandom();

    private int age;
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    //false = female, male = true
    private boolean gender;   
    // The animal's position in the field.
    private Location location;   
    /**
     * Create a new organism at location in field.
     * give it an age + gender
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Organism(boolean randomAge, Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        this.gender = rand.nextBoolean(); 
        if(randomAge) {
            age = rand.nextInt(getMaxAge());
        } else {
            age = 0;
        }
    }

    // all abstract methods here
    protected abstract void act(List<Organism> newOrganism, int time, String weather);

    //checks if an organism can act during @param time and @param weather.
    protected abstract boolean canAct(int time, String weather);

    //checks if an organim is of the same species as @param organism.
    protected abstract boolean checkSpecies(Object organism);

    protected abstract int getBreedAge();

    protected abstract double getBreedingRate();

    protected abstract int getMaxAge();

    protected abstract int getMaxBirths();

    //produces new organisms
    protected abstract Organism newOrganism(Field field, Location loc);

    /**
     * @return A random number (within Max_Birth range) of babies if the organism can breed.
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingRate()) {
            births = rand.nextInt(getMaxBirths()) + 1;
        }
        return births;
    }     

    /**
     * Check whether the organism is old enough to reproduce and is a female.
     * @return True if the female organism is old enough to reproduce.
     */
    protected boolean canBreed(){
        return getAge() >= getBreedAge() && this.getGender() == false;     
    }

    /**
     * Checks whether the animal has any of its species of the opposite sex in its adjacent fields.
     * @return true If the animal has any of its species of the opposite sex in its adjacent fields.
     */
    protected boolean checkMate(){
        List<Location> adjacentLocations = getField().adjacentLocations(getLocation());
        for(Location adjacent : adjacentLocations ){
            Object neighbour = getField().getObjectAt(adjacent);
            //check if neighbour is of the same species
            if(checkSpecies(neighbour)){
                Organism organism = (Organism) neighbour;
                //check if of different gender
                return !(getGender() == organism.getGender()); 
            }
        }
        return false;
    }

    /**
     * @return Age of the organism
     */
    protected int getAge(){
        return age;
    }    

    /**
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }

    /**
     * @return boolean organisms gender
     * false = female, male = true
     */
    protected boolean getGender()
    {   
        return gender;
    }

    /**
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }

    /**
     * The females can only give birth with one male individual of the same species at a time. 
     * New births will be made into free adjacent locations.
     * @param newOrganisms  A list to return newly born organisms.
     */
    public void giveBirth(List<Organism> newOrganisms){
        if(canBreed() && checkMate()){
            // New Organisms are born into adjacent locations.
            // Get a list of adjacent free locations.
            Field field = getField();
            List<Location> free = field.getFreeAdjacentLocations(getLocation());
            int births = breed();
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                newOrganisms.add(newOrganism(field, loc));
                break;
            }
        }
    }

    /**
     * Check whether the organism is alive or not.
     * @return true if the organism is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Increments the age of the organism 
     * If it exceeds the species max life span then it dies
     */
    protected void incrementAge(){
        age++;
        if (age > getMaxAge()){
            setDead();
        }
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
}