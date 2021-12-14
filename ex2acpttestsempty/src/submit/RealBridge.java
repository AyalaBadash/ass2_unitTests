package submit;

import main.data.OrderInfo;
import main.data.ShowInfo;
import main.userStoriesControllers.AddShowController;
import main.userStoriesControllers.DataController;
import main.userStoriesControllers.OrderSeatsController;
import test.bridge.Bridge;

import java.util.List;

public class RealBridge implements Bridge {
    private AddShowController addShowController ;
    private DataController dataController;
    private OrderSeatsController orderSeatsController;


    public RealBridge(){
        addShowController =  AddShowController.getInstance() ;
        dataController =  DataController.getInstance();
        orderSeatsController = OrderSeatsController.getInstance();

    }



    /**
     * Adds new city
     *
     * @param city city name
     */
    public void addCity(String city){
        dataController.addCity(city);
    }

    /**
     * Adds new hall
     *
     * @param city city name
     * @param hall hall name
     * @param sits number of sits
     */
    public void addHall(String city, String hall, int sits)  {
        dataController.addHall(city, hall, sits);
    }

    /**
     * Adds new admin user
     *
     * @param city city where the user is allowed to be an admin
     * @param user user name
     * @param pass user password
     */
    public void addAdmin(String city, String user, String pass){
        dataController.addAdmin(city, user, pass);
    }

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
    public int addNewShow(String user, String pass, ShowInfo showInfo)  {
        if ( dataController.validateAdminUser(user, pass) && dataController.validateShowCreation(showInfo, user)) {
            return addShowController.addNewShow(showInfo);
        }
        else
        {
            return -1;
        }

    }

    /**
     * Reserves chairs for Pais members only
     *
     * @param showID show id (as return from addNewShow)
     * @param from   minimum chair id
     * @param to     maximum chair id
     */
    public void reserveMemberChairs(int showID, int from, int to)  {
        addShowController.reserveMemberChairs(showID, from, to);
    }

    /**
     * Adds new order
     *
     * @param order order information
     * @return If succeed return an unique reservation id. Otherwise return -1.
     */
    public int newOrder(OrderInfo order) {
        int orderId =  orderSeatsController.newOrder(order , addShowController.getShow(order.showId));
        if (orderId>0){
            int showId = order.showId;
            addShowController.getShow(showId).addOrder(order);

        }
        return orderId;
    }

    /**
     * Gets waiting orders
     *
     * @param id show id
     * @return If succeed returns the list of waiting orders. Otherwise return empty
     *         list.
     */
    public List<OrderInfo> getWaitings(int id){
        return addShowController.getWaitings(id);
    }
}
