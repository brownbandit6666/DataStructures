/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */
import java.util.HashMap;

public class Sanctuary {
    
    HashMap<String, Integer> sanctuary = new HashMap<>();
    private final int maxAnimals;
    private final int maxSpecies;

    public Sanctuary(int maxAnimals, int maxSpecies){
        if(maxAnimals <= 0 || maxSpecies <= 0 || maxSpecies > maxAnimals){
            throw new IllegalArgumentException();
        }
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    public int countForSpecies(String species){
        if(species == null){
            throw new IllegalArgumentException();
        }
        if(!sanctuary.containsKey(species)){
            return 0;
        }
        return sanctuary.get(species);
    }

    public int getTotalAnimals(){
        int totalAnimals = 0;
        for(int species: sanctuary.values()){
            totalAnimals += species;
        }
        return totalAnimals;
    }

    public int getTotalSpecies(){
        return sanctuary.size();
    }

    public int getMaxAnimals(){
        return maxAnimals;
    }

    public int getMaxSpecies(){
        return maxSpecies;
    }

    public int rescue(String species, int num){
        if(num <= 0 || species == null){
            throw new IllegalArgumentException();
        }
        if(maxSpecies == getTotalSpecies() && 
        !sanctuary.containsKey(species)){
            return num;
        }
        if(maxAnimals == getTotalAnimals()){
            return num;
        }
        int currentAnimals = 0;
        if(sanctuary.containsKey(species)){
            currentAnimals = sanctuary.get(species);
        }

        int allowedAnimals = maxAnimals - getTotalAnimals();
                
        if(getTotalAnimals() + num > maxAnimals){
            sanctuary.put(species, currentAnimals + allowedAnimals);
            return Math.abs(num - (allowedAnimals));
        }
        else{
            sanctuary.put(species, currentAnimals + num);
            return 0;
        }
    }

    public void release(String species, int num){
        if(num <= 0 || num > countForSpecies(species)){
            throw new IllegalArgumentException();
        }
        int animalsAfter = countForSpecies(species) - num;
        if(animalsAfter == 0){
            sanctuary.remove(species);
        }
        else{
            sanctuary.put(species, animalsAfter);
        }
    }

}
