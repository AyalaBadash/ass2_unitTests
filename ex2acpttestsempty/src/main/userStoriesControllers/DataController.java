package main.userStoriesControllers;

import main.data.CityInfo;
import main.data.ShowInfo;
import main.data.UserInfo;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidKeyException;
import java.util.HashMap;

public class DataController {
    protected String currentLoggedId;
    protected HashMap<String , UserInfo> admins; // id, info
    protected HashMap<String , CityInfo> cities;
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




    public void addHall(String city, String hall, int sits)  {
        if (cities.containsKey(city)){
            cities.get(city).getHalls().put(hall,sits);
        }
        else
        {
            return;
        }
    }

    public void addAdmin(String city, String user, String pass){
        if (admins.containsKey(user)){
            return;
        }
        if (pass == null ){
            return;
        }
        UserInfo info = new UserInfo(user, pass,city);
        admins.put(user, info );
    }



    public boolean validateShowCreation(ShowInfo show, String userId ){
        
        UserInfo user = admins.get(userId);
        
        if (user!= null && cities.containsKey( show.city)  &&
                user.hasCity(show.city) &&
                cities.get(show.city).getHalls().containsKey(show.hall) &&
                show.lastOrderDate < show.showDate &&
                show.lastOrderDate >0 &&
                show.ticketCost >0
        )
        {
            if ( !show.hastime){
                return true;
            }
            else {
                return show.showTime != null;
            }
        }
        else{
            return false;
        }
    }

    public boolean validateAdminUser(String user, String pass) {
        UserInfo cur = admins.get(user);
        if (cur != null){
            return cur.getPassword().equals(pass);
        }
        return false;
    }
}
