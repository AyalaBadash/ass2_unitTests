package submit;

import main.data.OrderInfo;
import main.data.ShowInfo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public int newOrder(OrderInfo order, Show curShow)  {

        if (curShow != null){
            if(!validateOrder(order, curShow))
                return -1;
            List<Integer> reserved;
            if(order.memberId > 0)
                reserved = curShow.getReservedSeats ();
            else
                reserved = curShow.getReservedSeatsWithVIP ();
            for ( Integer seat: order.chairsIds ) {
                if(reserved.contains ( seat ))
                    return -1;
            }
            for ( Integer seat: order.chairsIds ) {
                curShow.resevrveTempSeat ( seat );
            }
            return nextOrderId++;
        }
        return -1;
    }

    private boolean validateOrder(OrderInfo order, Show show) {
        if(LocalDate.now ().isAfter ( Instant.ofEpochMilli(show.getRelatedShowInfo().lastOrderDate).atZone( ZoneId.systemDefault()).toLocalDate()))
            return false;
        if(order.chairsIds == null || order.chairsIds.length == 0 || order.name == null || order.phone == null)
            return false;
        if(order.phone.length() < 9 || order.phone.length() > 10)
            return false;
        for(int i = 0; i < order.name.length (); i++){
            if(Integer.getInteger ("" + order.name.charAt ( i )) != null)
                return false;
        }
        return true;
    }


}
