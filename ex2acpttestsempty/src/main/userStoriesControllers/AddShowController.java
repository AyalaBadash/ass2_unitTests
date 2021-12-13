package main.userStoriesControllers;

import main.data.OrderInfo;
import main.data.ShowInfo;
import main.data.UserInfo;

import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;

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
    public void reserveMemberChairs(int showID, int from, int to)  {
        ShowInfo showInfo = shows.get ( showID );
        ShowInfo.State[] seats = showInfo.getSeats ();
        if(to > seats.length + 1)
            return;
        if(from < 1 || to < 1)
            return;
        if (to < from){
            return;
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
    public int addNewShow(String user, String pass,  ShowInfo showInfo)  {
        UserInfo userInfo  = DataController.getInstance ().admins.get(user);
        showInfo.id = nextShowId;;
        shows.put(nextShowId,showInfo);
        showInfo.initialSeats ( DataController.getInstance ().cities.get (showInfo.city).getHalls ().get ( showInfo.hall ) );
        nextShowId++;
        return showInfo.id;


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


    public ShowInfo getShow(int showId) {
        return shows.get(showId);
    }

}
