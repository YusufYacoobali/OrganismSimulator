import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author David J. Barnes, Michael Kölling, Yusuf Yacoobali and Joyce Chong
 * @version V3
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 150;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 100; 
    
    // The probabilities that an animal of a particular species will be created in any given grid position.
    private static final double FISH_CREATION_PROBABILITY = 0.08; 
    private static final double OWL_CREATION_PROBABILITY = 0.02;
    private static final double PANDA_CREATION_PROBABILITY = 0.02;
    private static final double BEAR_CREATION_PROBABILITY = 0.02;
    private static final double BAMBOO_CREATION_PROBABILITY = 0.07;

   
    // List of animals in the field.
    private List<Organism> organisms;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    //current weather
    private Weather weather;
    //current time and day
    private Time time;
    
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        organisms = new ArrayList<>();
        field = new Field(depth, width);
        
        //time and weather variables initialised 
        weather = new Weather();
        time = new Time();

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Fish.class, Color.BLUE);
        view.setColor(Owl.class, Color.MAGENTA);
        view.setColor(Panda.class, Color.CYAN);
        view.setColor(Bear.class, Color.RED);
        view.setColor(Bamboo.class, Color.GREEN);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            delay(30);   // uncomment this to run more slowly
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;
        //time is incremented each step
        time.incrementTime(weather);
        // Provide space for newborn animals.
        List<Organism> newOrganisms = new ArrayList<>();        
        // Let all rabbits act.
        for(Iterator<Organism> it = organisms.iterator(); it.hasNext(); ) {
            Organism organism = it.next();
            //act method now requires knowledge of current time and weather conditions
            organism.act(newOrganisms, time.getTime(), weather.getWeather());            
            if(! organism.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        organisms.addAll(newOrganisms);

        view.showStatus(step, field, time.getTime(), time.getDay(), weather.getWeather());     
    }
    
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        time.reset();
        organisms.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field, time.getTime(), time.getDay(), weather.reset());
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= OWL_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Owl owl = new Owl(true, field, location);
                    organisms.add(owl);
                }
                else if(rand.nextDouble() <= BEAR_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bear bear = new Bear(true, field, location);
                    organisms.add(bear);
                }
                else if(rand.nextDouble() <= FISH_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fish fish = new Fish(true, field, location);
                    organisms.add(fish);
                }
                else if(rand.nextDouble() <= PANDA_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Panda panda = new Panda(true, field, location);
                    organisms.add(panda);
                }
                else if(rand.nextDouble() <= BAMBOO_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bamboo bamboo = new Bamboo(true, field, location);
                    organisms.add(bamboo);
                }
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}