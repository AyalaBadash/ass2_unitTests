package main.userStoriesControllers;

import main.data.OrderInfo;
import main.data.ShowInfo;

import java.util.List;

public class OrderSeatsController {
    private static OrderSeatsController instance = null;
    private int nextOrderId;

    private OrderSeatsController(){
        nextOrderId = 1;
    }

    public static OrderSeatsController getInstance() {
        if(instance == null)
            instance = new OrderSeatsController ();
        return instance;
    }

    /**
     * Adds new order
     *
     * @param order order information
     * @return If succeed return an unique reservation id. Otherwise return -1.
     */
    public int newOrder(OrderInfo order) throws Exception {
        int showId = order.showId;
        ShowInfo curShow = DataController.getInstance ().shows.get (showId);
        List<Integer> reserved;
        if(order.memberId > 0)
            reserved = curShow.getReservedSeatsWithVIP ();
        else
            reserved = curShow.getReservedSeats ();
        for ( Integer seat: order.chairsIds ) {
            if(reserved.contains ( seat ))
                return -1;
        }
        for ( Integer seat: order.chairsIds ) {
            curShow.resevrveTempSeat ( seat );
        }
        return nextOrderId++;
    }



}
