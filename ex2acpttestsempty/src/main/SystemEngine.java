package main;

import jdk.jshell.spi.ExecutionControl;
import main.data.CityInfo;
import main.data.OrderInfo;
import main.data.ShowInfo;
import main.data.UserInfo;
import main.userStoriesControllers.AddShowController;
import main.userStoriesControllers.DataController;
import main.userStoriesControllers.OrderSeatsController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SystemEngine {
    private AddShowController addShowController ;
    private DataController dataController;
    private OrderSeatsController orderSeatsController;
    private static SystemEngine instance = null;


    private SystemEngine(){
        addShowController =  AddShowController.getInstance() ;
        dataController =  DataController.getInstance();
        orderSeatsController = OrderSeatsController.getInstance();

    }

    public static SystemEngine getInstance() {
        if (instance ==null){
            instance = new SystemEngine();
        }
        return instance;
    }

    /**
     * Adds new city
     *
     * @param city city name
     */
    public void addCity(String city){
        throw new UnsupportedOperationException();
    }

    /**
     * Adds new hall
     *
     * @param city city name
     * @param hall hall name
     * @param sits number of sits
     */
    public void addHall(String city, String hall, int sits){throw new UnsupportedOperationException();}

    /**
     * Adds new admin user
     *
     * @param city city where the user is allowed to be an admin
     * @param user user name
     * @param pass user password
     */
    public void addAdmin(String city, String user, String pass){throw new UnsupportedOperationException();}

    /**
     * Adds new show
     *
     * @param user     username
     * @param pass     password
     * @param showInfo contains show information (used to reduce the amount of
     *                 parameters)
     * @return If succeed returns unique show id (a positive number). Otherwise
     *         return -1.
     */
    public int addNewShow(String user, String pass, ShowInfo showInfo){throw new UnsupportedOperationException();}

    /**
     * Reserves chairs for Pais members only
     *
     * @param showID show id (as return from addNewShow)
     * @param from   minimum chair id
     * @param to     maximum chair id
     */
    public void reserveMemberChairs(int showID, int from, int to){throw new UnsupportedOperationException();}

    /**
     * Adds new order
     *
     * @param order order information
     * @return If succeed return an unique reservation id. Otherwise return -1.
     */
    public int newOrder(OrderInfo order){throw new UnsupportedOperationException();}

    /**
     * Gets waiting orders
     *
     * @param id show id
     * @return If succeed returns the list of waiting orders. Otherwise return empty
     *         list.
     */
    public List<OrderInfo> getWaitings(int id){throw new UnsupportedOperationException();}
}
