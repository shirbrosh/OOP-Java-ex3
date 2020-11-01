import oop.ex3.searchengine.Hotel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * The tester for the BoopingSite object class.
 */
public class BoopingSiteTest {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;

    /**
     * the BoopingSite objects to test
     */
    private BoopingSite mySite1;
    private BoopingSite mySite2;
    private BoopingSite mySite3;

    public BoopingSiteTest(){
        mySite1 = new BoopingSite("hotels_tst1.txt");
        mySite2 = new BoopingSite("hotels_tst2.txt");
        mySite3 = new BoopingSite("hotels_dataset.txt");

    }

    /**
     * This method tests if the received list from the getHotelsInCityByRating method in the BoopingSite class
     * is indeed sorted according to the demands.
     * @param theListToCheck - the received from the getHotelsInCityByRating method.
     * @return true if the list is sorted properly, false otherwise.
     */
    private boolean getHotelsInCityByRatingTest_helper(Hotel[] theListToCheck){
        for(int i=0; i<theListToCheck.length-1;i++) {
            if(theListToCheck[i].getStarRating() < theListToCheck[i+1].getStarRating())
                return false;
            if(theListToCheck[i].getStarRating() == theListToCheck[i+1].getStarRating()){
                if(theListToCheck[i].getPropertyName().compareTo(theListToCheck[i+1].getPropertyName()) > 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * A test for the getHotelsInCityByRating method from BoopingSite class.
     */
    @Test
    public void getHotelsInCityByRatingTest() {
        // the parameter that will save the list of hotels to test.
        Hotel[] theListToCheck;
        //test1:
        theListToCheck = mySite3.getHotelsInCityByRating("Village Simsa");
        for (int i = 0; i < theListToCheck.length; i++)
            assertEquals("the city should be Village Simsa", "Village Simsa", theListToCheck[i].getCity());
        assertEquals("There is a problem with the sort",true,getHotelsInCityByRatingTest_helper(theListToCheck));

        //test2:
        theListToCheck = mySite1.getHotelsInCityByRating("manali");
        for (int i = 0; i < theListToCheck.length; i++)
            assertEquals("the city should be manali", "manali", theListToCheck[i].getCity());
        assertEquals("There is a problem with the sort",true,getHotelsInCityByRatingTest_helper(theListToCheck));

        //test3:
        theListToCheck = mySite1.getHotelsInCityByRating("noCity");
        assertEquals("There is no hotels in this city and yet your hotel array is not empty",0,theListToCheck.length);
    }

    /**
     * This method tests if the received list from the getHotelsByProximity/ getHotelsInCityByProximity
     * method in the BoopingSite class is indeed sorted according to the demands.
     * @param theListToCheck - the received from the getHotelsByProximity/ getHotelsInCityByProximity method
     * @param latitude - the latitude to check the distance from
     * @param longitude -  the longitude to check the distance from
     * @return true if the list is sorted properly, false otherwise.
     */
    private int getHotelsByProximity_helper(Hotel[] theListToCheck, double latitude, double longitude){
        if(theListToCheck.length ==ZERO) return MINUS_ONE;
        for(int i=0; i<theListToCheck.length-1;i++) {
            double distHotel1 =Math.hypot(Math.abs(theListToCheck[i].getLatitude()-latitude), Math.abs(theListToCheck[i].getLongitude()-longitude));
            double distHotel2 =Math.hypot(Math.abs(theListToCheck[i+1].getLatitude()-latitude), Math.abs(theListToCheck[i+1].getLongitude()-longitude));
            if(distHotel1 > distHotel2) return ZERO;
            if(distHotel1==distHotel2){
                if(theListToCheck[i].getNumPOI()<(theListToCheck[i+1].getNumPOI()))
                    return ZERO;
            }
        }
        return ONE;
    }
    /**
     * A test for the getHotelsByProximity method from BoopingSite class.
     */
    @Test
    public void getHotelsByProximity(){
        // the parameter that will save the list of hotels to test.
        Hotel[] theListToCheck;

        //test1:
        theListToCheck = mySite1.getHotelsByProximity(35.2236026,70.1858995);
        assertEquals("There is a problem with the sort",ONE,getHotelsByProximity_helper(theListToCheck,35.2236026,70.1858995 ));

        //test2:
        theListToCheck = mySite1.getHotelsByProximity(-90.2236026,70.1858995);
        assertEquals("the coordinate is out of limits and yet your hotel Array is not empty",MINUS_ONE,getHotelsByProximity_helper(theListToCheck,-90.2236026,70.1858995 ));

        //test3:
        theListToCheck = mySite2.getHotelsByProximity(10.2236026,55.1858995);
        assertEquals("this hotel data set is empty and your Array is not",MINUS_ONE,getHotelsByProximity_helper(theListToCheck,10.2236026,55.1858995 ));
    }
    /**
     * A test for the getHotelsInCityByProximity method from BoopingSite class.
     */
    @Test
    public void getHotelsInCityByProximityTest(){
        // the parameter that will save the list of hotels to test.
        Hotel[] theListToCheck;

        //test1:
        theListToCheck = mySite1.getHotelsInCityByProximity("manali",35.2236026,70.1858995);
        for (int i = 0; i < theListToCheck.length; i++)
            assertEquals("the city should be manali", "manali", theListToCheck[i].getCity());
        assertEquals("There is a problem with the sort",ONE,getHotelsByProximity_helper(theListToCheck,35.2236026,70.1858995 ));

        //test2:
        theListToCheck = mySite3.getHotelsInCityByProximity("goa",77.2236026,10.1858995);
        for (int i = 0; i < theListToCheck.length; i++)
            assertEquals("the city should be goa", "goa", theListToCheck[i].getCity());
        assertEquals("There is a problem with the sort",ONE,getHotelsByProximity_helper(theListToCheck,77.2236026,10.1858995 ));

        //test3:
        theListToCheck = mySite1.getHotelsInCityByRating("noCity");
        assertEquals("There is no hotels in this city and yet your hotel array is not empty",0,theListToCheck.length);

        //test4:
        theListToCheck = mySite3.getHotelsByProximity(-90.2236026,70.1858995);
        assertEquals("the coordinate is out of limits and yet your hotel Array is not empty",MINUS_ONE,getHotelsByProximity_helper(theListToCheck,-90.2236026,70.1858995 ));
    }
}

