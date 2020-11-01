import oop.ex3.searchengine.Hotel;
import java.util.Comparator;
import java.lang.Math;

/**
 * The compare class that campers between hotels distance to a given coordinate.
 */
public class CompareByProximity implements Comparator<Hotel>  {
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;


    private double latitudeToCheck;
    private double longitudeToCheck;

    public CompareByProximity(double latitude, double longitude){
        latitudeToCheck = latitude;
        longitudeToCheck = longitude;
    }

    /**
     * his method compares between two hotels distance from a given coordinate
     * @param hotel1 - the first hotel to compare
     * @param hotel2 - the second hotel to compare
     * @return -1 if the distance of the first hotel is lower then the second hotel, o if there equal or 1 if
     * the distance of the first hotel is higher then the second hotel.
     */
    public int compare(Hotel hotel1, Hotel hotel2){
        double distHotel1 =Math.hypot(Math.abs(hotel1.getLatitude()-latitudeToCheck), Math.abs(hotel1.getLongitude()-longitudeToCheck));
        double distHotel2 =Math.hypot(Math.abs(hotel2.getLatitude()-latitudeToCheck), Math.abs(hotel2.getLongitude()-longitudeToCheck));
        if(distHotel1 < distHotel2) return MINUS_ONE;
        if(distHotel1 > distHotel2) return ONE;
        else return Integer.compare(hotel2.getNumPOI(), hotel1.getNumPOI());
    }
}
