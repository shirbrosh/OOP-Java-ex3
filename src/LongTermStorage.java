import oop.ex3.spaceship.*;

/**
 * The LongTermStorage object class.
 */
public class LongTermStorage extends MainStorage {
    /**
     * This method adds n items of the given type to the long-term storage unit. If the action is successful,
     * this method will return 0. If n Items cannot be added to the ong-term storage at this time, no Items will be
     * added, the method will return -1.
     *
     * @param item - the item to add to the locker
     * @param n - the amount to be added
     * @return 0 if n Items were added, -1 if nothing was added
     */
    public int addItem(Item item, int n) {
        if (super.addItem(item, n) == ZERO) {
            if (unitMap.containsKey(item.getType())) {
                unitMap.put(item.getType(), unitMap.get(item.getType()) + n);
            } else unitMap.put(item.getType(), n);
            availableCapacity -= item.getVolume() * n;
            return ZERO;
        } else return NO_ITEMS_ADDED;
    }

    /**
     * This method resets the long-term storage's inventory
     */
    public void resetInventory() {
        availableCapacity = CAPACITY;
        unitMap.clear();
    }
}




