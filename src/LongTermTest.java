import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The tester for the LongTermStorage object class.
 */
public class LongTermTest {
    private final static int ZERO = 0;
    private final static int NO_ITEMS_ADDED = -1;
    /**
     * The LongTermStorage object to test.
     */
    LongTermStorage myLongTermStorage;
    /**
     * The Item objects i will use to test the LongTermStorage object.
     */
    Item item1;
    Item item2;
    Item badItem;

    public LongTermTest(){
        myLongTermStorage = new LongTermStorage();
        item1 = ItemFactory.createSingleItem("spores engine"); //vol:10
        item2 = ItemFactory.createSingleItem("football"); //vol:4
        badItem = new Item("shirt", 15);

    }

    /**
     * A test for the AddItem method from LongTermStorage class.
     */
    @Test
    public void testAddItem() {
        // the parameter that will save the return value from the AddItem method for each test.
        int isAdded;

        //test1:
        isAdded = myLongTermStorage.addItem(item2, 3);
        assertEquals("These items should be added to the storage and yet they are not", ZERO, isAdded);

        //test2:
        isAdded = myLongTermStorage.addItem(item2, 22);
        assertEquals("These items should be added to the storage, to the already existing key and yet " +
                "they are not", ZERO, isAdded);

        //test3:
        isAdded = myLongTermStorage.addItem(item1, 90);
        assertEquals("These items should be added to the storage and yet they are not", ZERO, isAdded);

        //test4:
        isAdded = myLongTermStorage.addItem(item1, 0);
        assertEquals("should add 0 items", ZERO, isAdded);

        //test5:
        isAdded = myLongTermStorage.addItem(item1, 1);
        assertEquals("not enough capacity to add these items and yet they are added", NO_ITEMS_ADDED, isAdded);

        //test6:
        isAdded = myLongTermStorage.addItem(item1, -3);
        assertEquals("cant add negative amounts of items", NO_ITEMS_ADDED, isAdded);

        //test7:
        isAdded = myLongTermStorage.addItem(badItem, 12);
        assertEquals("cant add negative amounts of items", NO_ITEMS_ADDED, isAdded);
    }

    /**
     * A test for the ResetInventory method from LongTermStorage class.
     */
    @Test
    public void testResetInventory(){
        //test1:
        myLongTermStorage.addItem(item2, 3);
        myLongTermStorage.resetInventory();
        assertTrue(myLongTermStorage.getInventory().isEmpty());
    }

    /**
     * A test for the GetItemCount method from LongTermStorage class.
     */
    @Test
    public void testGetItemCount(){
        // the parameter that will save the return value from the getItemCount method for each test.
        int count;

        //test1:
        count = myLongTermStorage.getItemCount(item2.getType());
        assertEquals("should count 0 football items, and instead count" + count, 0, count);

        //test2:
        myLongTermStorage.addItem(item2, 4);
        count = myLongTermStorage.getItemCount(item2.getType());
        assertEquals("should count 4 football items, and instead count" + count, 4, count);

        //test3:
        myLongTermStorage.addItem(item2, 5);
        count = myLongTermStorage.getItemCount(item2.getType());
        assertEquals("should count 9 football items, and instead count " + count, 9, count);

        //test4:
        myLongTermStorage.addItem(item2, 20);
        count = myLongTermStorage.getItemCount(item2.getType());
        assertEquals("should count 29 football items, and instead count " + count, 29, count);
    }

    /**
     * A test for the GetAvailableCapacity method from LongTermStorage class.
     */
    @Test
    public void testGetAvailableCapacity(){
        //test1:
        assertEquals("The capacity at the beginning should be 1000",1000,myLongTermStorage.getAvailableCapacity());

        //test2:
        myLongTermStorage.addItem(item1, 50);
        assertEquals("The capacity should be 500",500,myLongTermStorage.getAvailableCapacity());
    }

    /**
     * A test for the GetCapacity method from LongTermStorage class.
     */
    @Test
    public void testGetCapacity(){
        assertEquals("The capacity should be 1000",1000,myLongTermStorage.getCapacity());
    }
}
