package main.userStoriesControllers;

import main.data.CityInfo;
import main.data.ShowInfo;
import main.data.UserInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class DataController {
    protected String currentLoggedId;
    protected int nextShowId;
    protected HashMap<String , UserInfo> admins; // id, info
    protected HashMap<String , CityInfo> cities;
    protected HashMap<Integer, ShowInfo> shows;
    private static DataController instance = null;



    private DataController(){
        admins= new HashMap<>();
        cities = new HashMap<>();
        currentLoggedId = null;
        nextShowId = 1;

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
     *
     * @param showInfo
     * @return the id of the created show
     * @throws
     */
    public int addNewShow(String user, String pass,  ShowInfo showInfo) throws InvalidKeyException {
        UserInfo userInfo  = admins.get(user);
        if (validateShowCreation(showInfo, userInfo)){
            showInfo.id = nextShowId;;
            shows.put(nextShowId,showInfo);
            showInfo.initialSeats ( cities.get (showInfo.city).getHalls ().get ( showInfo.hall ) );
            nextShowId++;
            return showInfo.id;
        }
        else{
            return -1;
        }
    }

    private boolean validateShowCreation(ShowInfo show, UserInfo user ){
        if (cities.containsKey( show.city)  &&
            user.hasCity(show.city) &&
            cities.get(show.city).getHalls().containsKey(show.hall) &&
            show.lastOrderDate < show.showDate &&
            show.lastOrderDate >0 &&
            show.ticketCost >0
        )
        {
            return show.hastime || show.showTime != null;
        }
        else{
            return false;
        }
    }







}
