package main.userStoriesControllers;

import main.data.CityInfo;
import main.data.ShowInfo;
import main.data.UserInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class DataController {
    protected int currentLoggedId;
    protected int nextShowId;
    protected HashMap<String , UserInfo> admins; // id, info
    protected HashMap<String , CityInfo> cities;
    protected HashMap<Integer, ShowInfo> shows;
    private static DataController instance = null;



    private DataController(){
        admins= new HashMap<>();
        cities = new HashMap<>();
        currentLoggedId = -1;
        nextShowId = 1;

    }

    public static DataController getInstance() {
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
        UserInfo info = new UserInfo(user, pass,city);
        admins.put(user, info );
    }


    /**
     *
     * @param showInfo
     * @return the id of the created show
     * @throws
     */
    public int addNewShow(  ShowInfo showInfo){

        shows.put(showInfo.id , showInfo);
        return -1;
    }

    private boolean validateShowCreation(ShowInfo info){
        return true;
    }



    public int getCurrentLoggedId() {
        return currentLoggedId;
    }



}
