import oop.ex3.searchengine.Hotel;
import java.util.Comparator;

/**
 *  The compare class that campers between hotel star rating.
 */
public class CompareByRating implements Comparator<Hotel> {
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;

    /**
     * This method compares between two hotels star rating
     * @param hotel1- the first hotel to compare
     * @param hotel2 - the second hotel to compare
     * @return 1 if the rating of the first hotel is lower then the second hotel, o if there equal or -1 if
     * the rating of the first hotel is higher then the second hotel.
     */
    public int compare(Hotel hotel1, Hotel hotel2)
    {
        if(hotel1.getStarRating()> hotel2.getStarRating()) return MINUS_ONE;
        if(hotel1.getStarRating()< hotel2.getStarRating()) return ONE;
        else return hotel1.getPropertyName().compareTo(hotel2.getPropertyName());
    }
}
