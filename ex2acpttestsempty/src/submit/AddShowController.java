package submit;

import main.data.OrderInfo;
import main.data.ShowInfo;

import java.util.HashMap;
import java.util.List;



public class AddShowController {
    private int nextShowId;
    HashMap<Integer, Show> shows ;// id, info
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
        Show showInfo = shows.get ( showID );
        if(showInfo == null)
            return;
        Show.State[] seats = showInfo.getSeats ();
        if(to > seats.length)
            return;
        if(from < 1 || to < 1)
            return;
        if (to < from){
            return;
        }
        for(int i = from; i < to; i ++){
            if (seats[i] == Show.State.free)
                seats[i] = Show.State.VIP;
        }
        showInfo.setSeats ( seats );
    }

    /**
     *
     * @param showInfo
     * @return the id of the created show
     * @throws
     */
    public int addNewShow(ShowInfo showInfo)  {
        Show show = new Show(showInfo);
        show.id = nextShowId;;
        shows.put(nextShowId,show);
        show.initialSeats ( DataController.getInstance ().cities.get (show.getRelatedShowInfo().city).getHalls ().get ( showInfo.hall ) );
        nextShowId++;
        return show.id;


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
        return shows.get(showId).getRelatedShowInfo();
    }

    public Show getCoverShow(int showId) {
        return shows.get(showId);

    }
}
