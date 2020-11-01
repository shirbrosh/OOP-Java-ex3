import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;
import java.util.*;
import java.util.Collections;

/**
 * The BoopingSite object class
 */
public class BoopingSite {
    private static final int NINETY = 90;
    private static final int HUNDRED_EIGHTY = 180;
    private static final Hotel[] EMPTY_ARRAY = new Hotel[0];
    /**
     * The name of the data set file
     */
    String dataSetName;
    /**
     * The Hotel object Array
     */
    Hotel[] hotelArray;

    public BoopingSite(String name) {
        dataSetName = name;
        hotelArray = HotelDataset.getHotels(dataSetName);
    }

    /**
     * This method creates a List of all Hotel objects from the data set that are in the city city.
     *
     * @param city- all hotels in the return list should be in this city.
     * @return A List of all Hotel objects from the data set that are in the city city.
     */
    public ArrayList<Hotel> listByCity(String city) {
        ArrayList<Hotel> hotelByCityList = new ArrayList<>();
        for (Hotel hotel : hotelArray) {
            if (hotel.getCity().equals(city)) hotelByCityList.add(hotel);
        }
        return hotelByCityList;
    }

    /**
     * This method returns an Array of hotels located in the given city, sorted from the highest star-rating
     * to the lowest. hotels that have the same rating will be organized according to the alphabet order of
     * the hotels name.
     *
     * @param city- all hotels in the return Array should be in this city.
     * @return an Array of hotels located in the given city, sorted from the highest star-rating to the
     * lowest. hotels that have the same rating will be organized according to the alphabet order of the
     * hotels name.
     */
    public Hotel[] getHotelsInCityByRating(String city) {
        ArrayList<Hotel> hotelByCityList = listByCity(city);
        Collections.sort(hotelByCityList, new CompareByRating());
        Hotel [] getHotelsInCityByRatingArray = new Hotel[hotelByCityList.size()];
        hotelByCityList.toArray(getHotelsInCityByRatingArray);
        return getHotelsInCityByRatingArray;
    }


    /**
     * This method returns an Array of hotels sorted according to their distance from the given geographic
     * location, in ascending order. hotels that are at the same distance from the given location will be
     * organized according to the number of points-of-interest (in decreasing order).
     *
     * @param latitude - the latitude to check the distance from
     * @param longitude-  the longitude to check the distance from
     * @return n Array of hotels sorted according to their distance from the given geographic
     * location, in ascending order. hotels that are at the same distance from the given location will be
     * organized according to the number of points-of-interest (in decreasing order).
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude) {
        if ((latitude < -NINETY || latitude > NINETY) || (longitude < -HUNDRED_EIGHTY || longitude > HUNDRED_EIGHTY))
            return EMPTY_ARRAY;
        ArrayList<Hotel> hotelList = new ArrayList<Hotel>(Arrays.asList(hotelArray));
        Collections.sort(hotelList, new CompareByProximity(latitude, longitude));
        Hotel [] getHotelsByProximityArray = new Hotel[hotelList.size()];
        hotelList.toArray(getHotelsByProximityArray);
        return getHotelsByProximityArray;
    }

    /**
     * This method returns an Array of hotels sorted according to their distance from the given geographic
     * location, in ascending order. hotels that are at the same distance from the given location will be
     * organized according to the number of points-of-interest (in decreasing order).
     *
     * @param latitude - the latitude to check the distance from
     * @param longitude- the longitude to check the distance from
     * @return n Array of hotels sorted according to their distance from the given geographic
     * location, in ascending order. hotels that are at the same distance from the given location will be
     * organized according to the number of points-of-interest (in decreasing order).
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude) {
        if ((latitude < -NINETY || latitude > NINETY) || (longitude < -HUNDRED_EIGHTY || longitude > HUNDRED_EIGHTY))
            return EMPTY_ARRAY;
        ArrayList<Hotel> hotelByCityList =  listByCity(city);
        Collections.sort(hotelByCityList, new CompareByProximity(latitude, longitude));
        Hotel [] getHotelsByProximityArray = new Hotel[hotelByCityList.size()];
        hotelByCityList.toArray(getHotelsByProximityArray);
        return getHotelsByProximityArray;
    }
}
