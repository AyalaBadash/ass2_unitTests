package main.userStoriesControllers;

import main.data.ShowInfo;

import java.util.HashMap;

public class AddShowController {
    int lastShowId;
    HashMap<Integer, ShowInfo> shows ;// id, info
    private static  AddShowController instance = null;


    public static AddShowController getInstance() {
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
        int[] seats = showInfo.getSeats ();
        if(to > seats.length + 1)
            throw new Exception ( "maximum chair id is bigger then number of chairs in hall" );
        if(from < 1 || to < 1)
            throw new Exception ( "chair id is negative" );
        if (to < from){
            throw new Exception ( "maximum chair id is smaller then minimum chair id" );
        }
        for(int i = from; i < to; i ++){
            if (seats[i] == 0)
                seats[i] = 2;
        }
        showInfo.setSeats ( seats );
    }
}
