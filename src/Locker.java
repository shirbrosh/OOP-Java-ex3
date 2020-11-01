import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

/**
 * The locker object class
 */
public class Locker extends MainStorage {
    private final static int ONE =1;
    private final static Double FIFTY_PERCENT =0.5;
    private final static Double TWENTY_PERCENT =0.2;
    private final static int CONTRADICTING =-2;
    private final static String SUCCESS_MSG = "Warning: Action successful, but has caused items to be moved to " +
            "storage";
    private final static String NO_ITEMS_ADDED_NEW_N1= "Error: Your request cannot be completed at this time. " +
            "Problem: no room for ";
    private final static String NO_ITEMS_ADDED_NEW_N2= " Items of type ";
    private final static String NO_ITEMS_REMOVED= "Error: Your request cannot be completed at this time. " +
            "Problem: the locker does not contain ";
    private final static String NO_ITEMS_REMOVED_NEGATIVE= "Error: Your request cannot be completed at this time. " +
            "Problem: cannot remove a negative number of items of type ";
    private final static String CONTRADICTING_MSG1= "Error: Your request cannot be completed at this time. Problem:" +
            "the locker cannot contain items of type ";
    private final static String CONTRADICTING_MSG2=", as it contains a contradicting items.";
    private Item BASEBALL_BAT = ItemFactory.createSingleItem("baseball bat");
    private Item FOOTBALL = ItemFactory.createSingleItem("football");
    private final static String GENERAL_ERROR_MSG = "Error: Your request cannot be completed at this time.";


    /**
     * The LongTermStorage unit this locker will use.
     */
    static LongTermStorage LTStorage;

    public Locker(int capacityToLocker){
        super(capacityToLocker);
        LTStorage = new LongTermStorage();
    }

    /**
     * A method that helps the addItem method by calculating how many items should enter the locker and how
     * many should enter the storage
     * @param item - the item to add to the locker
     * @param n - the amount to be added
     * @param alreadyInMap - true if there is already item  of this kind in the locker or false otherwise.
     * @return 1 if that items were added or -1 otherwise
     */
    public int checkNewN(Item item, int n, boolean alreadyInMap){
        int newNLocker, newNStorage;
        newNLocker = (int)(TWENTY_PERCENT *capacity) / (item.getVolume());
        System.out.println(unitMap.get(item.getType()));
        if(alreadyInMap) newNStorage = (n+ unitMap.get(item.getType()))- newNLocker;
        else newNStorage = n-newNLocker;
        if(LTStorage.addItem(item, newNStorage) == ZERO) {
            if(alreadyInMap) {
                availableCapacity = (availableCapacity +(unitMap.get(item.getType())*item.getVolume()))- (item.getVolume() * newNLocker);
            }
            else availableCapacity -= item.getVolume() * newNLocker;
            unitMap.put(item.getType(), newNLocker);
            System.out.println(SUCCESS_MSG);
            return ONE;
        }
        else{
            System.out.println(NO_ITEMS_ADDED_NEW_N1 + (newNStorage) + NO_ITEMS_ADDED_NEW_N2 + item.getType());
            return NO_ITEMS_ADDED;
        }
    }

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
        if(isContradicting(item)){
            System.out.println(CONTRADICTING_MSG1+ item.getType()+CONTRADICTING_MSG2);
            return CONTRADICTING;
        }
        if (super.addItem(item, n) == ZERO) {
            if (unitMap.containsKey(item.getType())) {
                if ((n+ unitMap.get(item.getType()))* item.getVolume() > capacity * FIFTY_PERCENT) {
                    return checkNewN(item,n, true);
                }
                //if less then 50%
                else  {
                    unitMap.put(item.getType(),unitMap.get(item.getType()) + n);
                    availableCapacity -= item.getVolume() * n;
                    return ZERO;
                }
            }
            //item not in map:
            else{
                if ((item.getVolume() * n) > capacity * 0.5){
                    return checkNewN(item,n, false);
                }
                //if less then 50
                else {
                    unitMap.put(item.getType(), n);
                    availableCapacity -= item.getVolume() * n;
                    return ZERO;
                }
            }
        }
        //no place in locker:
        else return NO_ITEMS_ADDED;
    }

    /**
     * This method checks if a given item has a contradicting item in the locker
     * @param item - the item to add to the locker
     * @return True if there is and false if there is not.
     */
    public boolean isContradicting(Item item){
        if(item.getType() == BASEBALL_BAT.getType()) {
            if (unitMap.containsKey(FOOTBALL.getType())) return true;
            else return false;
        }
        if (item.getType() ==FOOTBALL.getType()){
            if (unitMap.containsKey(BASEBALL_BAT.getType())) return true;
            else return false;
        }
        return false;
    }


    /**
     * This method removes n Items of the type type from the locker.
     * @param item - the item to add to the locker
     * @param n - the amount to be added
     * @return 0 if successful or -1 otherwise
     */
    public int removeItem(Item item, int n){
        if(unitMap.containsKey(item.getType())) {
            //if n negative number:
            if (n < 0) {
                System.out.println(NO_ITEMS_REMOVED_NEGATIVE + item.getType());
                return NO_ITEMS_ADDED;
            }
            //if not negative:
            else {
                if (unitMap.get(item.getType()) > n || unitMap.get(item.getType()) == n) {
                    if(unitMap.get(item.getType()) == n) unitMap.remove(item.getType());
                    else unitMap.put(item.getType(), unitMap.get(item.getType()) - n);
                    availableCapacity += n*item.getVolume();
                    return ZERO;
                }
                // if less then n items in the locker
                else {
                    System.out.println(NO_ITEMS_REMOVED + n + NO_ITEMS_ADDED_NEW_N2 + item.getType());
                    return NO_ITEMS_ADDED;
                }
            }
        }
        else {
            System.out.println(GENERAL_ERROR_MSG);
            return NO_ITEMS_ADDED;
        }
    }
}
