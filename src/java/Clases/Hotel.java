package Clases;

import dbcp.ConnectionManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hotel {

    private String _hotelId;
    private String _hotelName;
    private City _location;
    private int _noOfDeluxRooms;
    private int _noOfEXERooms;
    private double _deluxRoomFare_PerDay;
    private double _eXERoomFarePerDay;
    private double _hotelTax;

    public Hotel(String _hotelId, String _hotelName, City _location, int _noOfDeluxRooms, int _noOfEXERooms, double _deluxRoomFare_PerDay, double _eXERoomFarePerDay, double _hotelTax) {
        this._hotelId = _hotelId;
        this._hotelName = _hotelName;
        this._location = _location;
        this._noOfDeluxRooms = _noOfDeluxRooms;
        this._noOfEXERooms = _noOfEXERooms;
        this._deluxRoomFare_PerDay = _deluxRoomFare_PerDay;
        this._eXERoomFarePerDay = _eXERoomFarePerDay;
        this._hotelTax = _hotelTax;
    }

    public String getHotelId() {
        return this._hotelId;
    }

    public void setHotelId(String aHotelId) {
        this._hotelId = aHotelId;
    }

    public String getHotelName() {
        return this._hotelName;
    }

    public void setHotelName(String aHotelName) {
        this._hotelName = aHotelName;
    }

    public City getLocation() {
        return this._location;
    }

    public void setLocation(City aLocation) {
        this._location = aLocation;
    }

    public int getNoOfDeluxRooms() {
        return this._noOfDeluxRooms;
    }

    public void setNoOfDeluxRooms(int aNoOfDeluxRooms) {
        this._noOfDeluxRooms = aNoOfDeluxRooms;
    }

    public int getNoOfEXERooms() {
        return this._noOfEXERooms;
    }

    public void setNoOfEXERooms(int aNoOfEXERooms) {
        this._noOfEXERooms = aNoOfEXERooms;
    }

    public double getDeluxRoomFare_PerDay() {
        return this._deluxRoomFare_PerDay;
    }

    public void setDeluxRoomFare_PerDay(double aDeluxRoomFare_PerDay) {
        this._deluxRoomFare_PerDay = aDeluxRoomFare_PerDay;
    }

    public double getEXERoomFarePerDay() {
        return this._eXERoomFarePerDay;
    }

    public void setEXERoomFarePerDay(double aEXERoomFarePerDay) {
        this._eXERoomFarePerDay = aEXERoomFarePerDay;
    }

    public double getHotelTax() {
        return this._hotelTax;
    }

    public void setHotelTax(double aHotelTax) {
        this._hotelTax = aHotelTax;
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _hotelId The hotel's ID
     * @throws SQLException
     * @return h An object created of a the variables that correspond to each
     * new Hotel
     */
    public static Hotel getHotel(String _hotelId) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("Hotel", "_hotelId= " + _hotelId);
        if (rs.next()) {
            Hotel h = new Hotel(rs.getString("_hotelId"), rs.getString("_hotelName"), City.getCity(rs.getString("_cityCode")), rs.getInt("_noOfDeluxeRooms"),
                    rs.getInt("_numOfEXERooms"), rs.getDouble("_deluxRoomsFare_PerDay"), rs.getDouble("_eXERoomFarePerDay"), rs.getDouble("_hotelTax"));
            return h;
        } else {
            return null;
        }
    }

    /**
     * Method that creates an object according to its defined WHERE clause and
     * received variables
     *
     * @param _location The location or city of each Hotel
     * @throws SQLException
     * @return h An array of Hotel objects that contain the info of each created
     * Hotel
     */
    public static Hotel[] getHotels(City _location) throws SQLException {
        ResultSet rs = ConnectionManager.selectAllColumns("City", "_locatione= " + _location);
        if (rs.next()) {
            ArrayList hotelArrayList = new ArrayList();
            Hotel h[];
            do {
                hotelArrayList.add(new Hotel(rs.getString("_hotelId"), rs.getString("_hotelName"), City.getCity(rs.getString("_cityCode")), rs.getInt("_noOfDeluxeRooms"),
                        rs.getInt("_numOfEXERooms"), rs.getDouble("_deluxRoomsFare_PerDay"), rs.getDouble("_eXERoomFarePerDay"), rs.getDouble("_hotelTax")));
            } while (rs.next());
            h = (Hotel[]) hotelArrayList.toArray();
            return h;
        } else {
            return null;
        }
    }
}