import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * The tester for the Locker object class.
 */
public class LockerTest {
    private final static int ZERO = 0;
    private final static int ONE = 1;
    private final static int NO_ITEMS_ADDED = -1;
    private final static int CONTRADICTING =-2;


    /**
     * The Locker object to test.
     */
    Locker myLocker;
    /**
     * The Item objects i will use to test the Locker object.
     */
    Item item1;
    Item item2;
    Item item3;
    Item badItem;

    public LockerTest(){
        myLocker = new Locker(100);
        item1 = ItemFactory.createSingleItem("spores engine"); //vol:10
        item2 = ItemFactory.createSingleItem("football"); //vol:4
        item3 = ItemFactory.createSingleItem("baseball bat"); //vol:2
        badItem = new Item("shirt", 15);

    }

    /**
     * A test for the AddItem method from Locker class.
     */
    @Test
    public void testAddItem() {
        // the parameter that will save the return value from the AddItem method for each test.
        int isAdded;

        //test1:
        isAdded = myLocker.addItem(item1, 2);
        assertEquals("These items should be added to the locker and yet they are not", ZERO, isAdded);

        //test2:
        isAdded = myLocker.addItem(item2, 13);
        assertEquals("These items should be added to the locker and storage and yet they are not", ONE, isAdded);

        //test3:
        isAdded = myLocker.addItem(item2, 10);
        assertEquals("These items should be added to the locker and storage and yet they are not", ONE, isAdded);

        //test4:
        isAdded = myLocker.addItem(item1, 100);
        System.out.println(myLocker.getInventory());
        assertEquals("These items should NOT be added to the locker and storage and yet they are added", NO_ITEMS_ADDED, isAdded);

        //test5:
        isAdded = myLocker.addItem(item3, 1);
        System.out.println(myLocker.getInventory());
        assertEquals("These items should NOT be added to the locker and storage and yet they are added", CONTRADICTING, isAdded);
    }

    /**
     * A test for the RemoveItem method from Locker class.
     */
    @Test
    public void testRemoveItem(){
        // the parameter that will save the numbers of items of type item in the map.
        int numItems;
        myLocker.addItem(item1, 2);
        myLocker.addItem(item2, 13);

        //test1:
        myLocker.removeItem(item1,-11);
        numItems = myLocker.getInventory().get(item1.getType());
        assertEquals("should not remove items and yet items were removed",2, numItems);

        //test2:
        myLocker.removeItem(item1,1);
        numItems = myLocker.getInventory().get(item1.getType());
        assertEquals("should not remove items and yet items were removed",1, numItems);

        //test3:
        myLocker.removeItem(item2,14);
        numItems = myLocker.getInventory().get(item2.getType());
        assertEquals("should not remove items and yet items were removed",5, numItems);

        //test4:
        myLocker.removeItem(item3,1);
        assertEquals("this item is not in the locker and yet your removing it",-1, myLocker.removeItem(item3,1));

    }
    /**
     * A test for the GetItemCount method from Locker class.
     */
    @Test
    public void testGetItemCount(){
        // the parameter that will save the return value from the getItemCount method for each test.
        int count;

        //test1:
        count = myLocker.getItemCount(item2.getType());
        assertEquals("should count 0 football items, and instead count" + count, 0, count);

        //test2:
        myLocker.addItem(item2, 4);
        count = myLocker.getItemCount(item2.getType());
        assertEquals("should count 4 football items, and instead count" + count, 4, count);

        //test3:
        myLocker.addItem(item2, 10);
        count = myLocker.getItemCount(item2.getType());
        assertEquals("should count 5 football items, and instead count " + count, 5, count);
    }

    /**
     * A test for the GetCapacity method from Locker class.
     */
    @Test
    public void testGetCapacity(){
        assertEquals("The capacity should be 100",100,myLocker.getCapacity());
    }

    /**
     * A test for the GetAvailableCapacity method from Locker class.
     */
    @Test
    public void testGetAvailableCapacity(){
        //test1:
        assertEquals("The capacity at the beginning should be 100",+ 100,myLocker.getAvailableCapacity());

        //test2:
        myLocker.addItem(item1, 4);
        assertEquals("The capacity should be 60",60,myLocker.getAvailableCapacity());

        //test3:
        myLocker.addItem(item1, 2);
        assertEquals("The capacity should be 50",80,myLocker.getAvailableCapacity());
    }
}




