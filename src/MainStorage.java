import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * The father class for the Locker and LongTermStorage classes.
 */
public class MainStorage {
    protected final static int CAPACITY = 1000;
    protected final static int ZERO = 0;
    protected final static int NO_ITEMS_ADDED = -1;
    protected final static String NO_ITEMS_ADDED_MSG1 = "Error: Your request cannot be completed at this time. " +
            "Problem: no room for ";
    protected final static String NO_ITEMS_ADDED_MSG2 = " Items of type ";

    /**
     * The total capacity unit.
     */
    protected int capacity;
    /**
     * The available capacity unit.
     */
    protected int availableCapacity;
    /**
     * The map that holds the stored items.
     */
    Map<String, Integer> unitMap;
    /**
     * The Array that holds all the items.
     */
    Item [] ItemArray;

    public MainStorage(int capacityToLocker){
        availableCapacity = capacityToLocker;
        capacity = capacityToLocker;
        unitMap = new HashMap<>();
        ItemArray= ItemFactory.createAllLegalItems();
    }

    public MainStorage(){
        availableCapacity = CAPACITY;
        capacity = CAPACITY;
        unitMap = new HashMap<>();
        ItemArray= ItemFactory.createAllLegalItems();

    }
    /**
     * This method adds n items of the given type to a storage unit. If the action is successful,
     * this method will return 0. If n Items cannot be added to the storage unit at this time, no Items will be
     * added, the method will return -1.
     *
     * @param item - the item to add to the locker
     * @param n - the amount to be added
     * @return 0 if n Items were added, -1 if nothing was added
     */
    public int addItem(Item item, int n) {
        if (n >= 0 && checkLegalItem(item)) {
            int capacityNeeded = item.getVolume() * n;
            if (availableCapacity >= capacityNeeded) return ZERO;
            else {
                System.out.println(NO_ITEMS_ADDED_MSG1 + n + NO_ITEMS_ADDED_MSG2 + item.getType());
                return NO_ITEMS_ADDED;
            }
        }
        else {
            System.out.println(NO_ITEMS_ADDED_MSG1 + n + NO_ITEMS_ADDED_MSG2 + item.getType());
            return NO_ITEMS_ADDED;
        }
    }

    /**
     * checks if the received item is legal
     * @param item - the item to add to the locker
     * @return true if the item is legal and false otherwise
     */
    public boolean checkLegalItem(Item item){
        for(int i=0; i < ItemArray.length; i++){
            if(item.getType()== ItemArray[i].getType() && item.getVolume()== ItemArray[i].getVolume() ) return true;
        }
        return false;
    }

    /**
     * This method returns the number of Items of type type the long-term storage contains
     * @param type -  the item type to count
     * @return the number of Items of type type the long-term storage contains
     */
    public int getItemCount(String type){
        if(unitMap.get(type)==null) return ZERO;
        else return unitMap.get(type);
    }

    /**
     * This method returns a map of all the Items contained in the long-term storage unit, and their
     * respective quantities.
     * @return a map of all the Items contained in the long-term storage unit, and their
     *    respective quantities.
     */
    public Map<String, Integer> getInventory(){
        return unitMap;
    }

    /**
     * @return the total capacity.
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * @return the available capacity
     */
    public int getAvailableCapacity(){
        return availableCapacity;
    }

}
