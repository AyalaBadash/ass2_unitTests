package main.userStoriesControllers;

import main.data.CityInfo;
import main.data.OrderInfo;
import main.data.ShowInfo;
import main.data.UserInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;

public class DataController {
    protected String currentLoggedId;
    protected HashMap<String , UserInfo> admins; // id, info
    protected HashMap<String , CityInfo> cities;
    protected HashMap<Integer, ShowInfo> shows;
    private static DataController instance = null;



    private DataController(){
        admins= new HashMap<>();
        cities = new HashMap<>();
        currentLoggedId = null;

    }

    public static DataController getInstance() {
        if(instance == null)
            instance = new DataController ();
        return instance;
    }

    public void addCity(String city){
        CityInfo info =  new CityInfo(city);
        cities.put(city, info);
    }




    public void addHall(String city, String hall, int sits) throws InvalidKeyException {
        if (cities.containsKey(city)){
            cities.get(city).getHalls().put(hall,sits);
        }
        else
        {
            throw new InvalidKeyException("coudln't add this hall because no such city " + city);
        }
    }

    public void addAdmin(String city, String user, String pass){
        if (admins.containsKey(user)){
            throw new KeyAlreadyExistsException(" user: " + user + " is already exist in the system");
        }
        if (pass == null ){
            throw new IllegalArgumentException(" password must be filled");
        }
        UserInfo info = new UserInfo(user, pass,city);
        admins.put(user, info );
    }

    /**
     * Gets waiting orders
     *
     * @param id show id
     * @return If succeed returns the list of waiting orders. Otherwise return empty
     *         list.
     */
    public List<OrderInfo> getWaitings(int id){
        return shows.get ( id ).getWaitings();
    }

}
