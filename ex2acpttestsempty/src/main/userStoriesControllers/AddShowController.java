package main.userStoriesControllers;

import main.data.ShowInfo;
import main.data.UserInfo;

import java.security.InvalidKeyException;
import java.util.HashMap;

import static main.data.ShowInfo.State;

public class AddShowController {
    private int nextShowId;
    HashMap<Integer, ShowInfo> shows ;// id, info
    private static  AddShowController instance = null;

    private AddShowController(){
        nextShowId = 1;
        shows = new HashMap<> (  );
    }


    public static AddShowController getInstance() {
        if(instance == null)
            instance = new AddShowController ();
        return instance;
    }

    /**
     * Reserves chairs for Pais members only
     *
     * @param showID show id (as return from addNewShow)
     * @param from   minimum chair id
     * @param to     maximum chair id
     */
    public void reserveMemberChairs(int showID, int from, int to) throws Exception {
        ShowInfo showInfo = shows.get ( showID );
        ShowInfo.State[] seats = showInfo.getSeats ();
        if(to > seats.length + 1)
            throw new Exception ( "maximum chair id is bigger then number of chairs in hall" );
        if(from < 1 || to < 1)
            throw new Exception ( "chair id is negative" );
        if (to < from){
            throw new Exception ( "maximum chair id is smaller then minimum chair id" );
        }
        for(int i = from; i < to; i ++){
            if (seats[i] == State.free)
                seats[i] = State.VIP;
        }
        showInfo.setSeats ( seats );
    }

    /**
     *
     * @param showInfo
     * @return the id of the created show
     * @throws
     */
    public int addNewShow(String user, String pass,  ShowInfo showInfo) throws InvalidKeyException {
        UserInfo userInfo  = DataController.getInstance ().admins.get(user);
        if (validateShowCreation(showInfo, userInfo)){
            showInfo.id = nextShowId;;
            shows.put(nextShowId,showInfo);
            showInfo.initialSeats ( DataController.getInstance ().cities.get (showInfo.city).getHalls ().get ( showInfo.hall ) );
            nextShowId++;
            return showInfo.id;
        }
        else{
            return -1;
        }
    }

    private boolean validateShowCreation(ShowInfo show, UserInfo user ){
        if (DataController.getInstance ().cities.containsKey( show.city)  &&
                user.hasCity(show.city) &&
                DataController.getInstance ().cities.get(show.city).getHalls().containsKey(show.hall) &&
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
